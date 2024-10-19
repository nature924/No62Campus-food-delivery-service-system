
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 外卖
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/waimai")
public class WaimaiController {
    private static final Logger logger = LoggerFactory.getLogger(WaimaiController.class);

    @Autowired
    private WaimaiService waimaiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("waimaiDeleteStart",1);params.put("waimaiDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = waimaiService.queryPage(params);

        //字典表数据转换
        List<WaimaiView> list =(List<WaimaiView>)page.getList();
        for(WaimaiView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WaimaiEntity waimai = waimaiService.selectById(id);
        if(waimai !=null){
            //entity转view
            WaimaiView view = new WaimaiView();
            BeanUtils.copyProperties( waimai , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody WaimaiEntity waimai, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,waimai:{}",this.getClass().getName(),waimai.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<WaimaiEntity> queryWrapper = new EntityWrapper<WaimaiEntity>()
            .eq("waimai_uuid_number", waimai.getWaimaiUuidNumber())
            .eq("waimai_name", waimai.getWaimaiName())
            .eq("waimai_types", waimai.getWaimaiTypes())
            .eq("waimai_kucun_number", waimai.getWaimaiKucunNumber())
            .eq("waimai_price", waimai.getWaimaiPrice())
            .eq("waimai_clicknum", waimai.getWaimaiClicknum())
            .eq("shangxia_types", waimai.getShangxiaTypes())
            .eq("waimai_delete", waimai.getWaimaiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WaimaiEntity waimaiEntity = waimaiService.selectOne(queryWrapper);
        if(waimaiEntity==null){
            waimai.setWaimaiClicknum(1);
            waimai.setShangxiaTypes(1);
            waimai.setWaimaiDelete(1);
            waimai.setCreateTime(new Date());
            waimaiService.insert(waimai);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WaimaiEntity waimai, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,waimai:{}",this.getClass().getName(),waimai.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<WaimaiEntity> queryWrapper = new EntityWrapper<WaimaiEntity>()
            .notIn("id",waimai.getId())
            .andNew()
            .eq("waimai_uuid_number", waimai.getWaimaiUuidNumber())
            .eq("waimai_name", waimai.getWaimaiName())
            .eq("waimai_types", waimai.getWaimaiTypes())
            .eq("waimai_kucun_number", waimai.getWaimaiKucunNumber())
            .eq("waimai_price", waimai.getWaimaiPrice())
            .eq("waimai_clicknum", waimai.getWaimaiClicknum())
            .eq("shangxia_types", waimai.getShangxiaTypes())
            .eq("waimai_delete", waimai.getWaimaiDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WaimaiEntity waimaiEntity = waimaiService.selectOne(queryWrapper);
        if("".equals(waimai.getWaimaiPhoto()) || "null".equals(waimai.getWaimaiPhoto())){
                waimai.setWaimaiPhoto(null);
        }
        if(waimaiEntity==null){
            waimaiService.updateById(waimai);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<WaimaiEntity> list = new ArrayList<>();
        for(Integer id:ids){
            WaimaiEntity waimaiEntity = new WaimaiEntity();
            waimaiEntity.setId(id);
            waimaiEntity.setWaimaiDelete(2);
            list.add(waimaiEntity);
        }
        if(list != null && list.size() >0){
            waimaiService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            List<WaimaiEntity> waimaiList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            WaimaiEntity waimaiEntity = new WaimaiEntity();
//                            waimaiEntity.setWaimaiUuidNumber(data.get(0));                    //外卖编号 要改的
//                            waimaiEntity.setWaimaiName(data.get(0));                    //外卖名称 要改的
//                            waimaiEntity.setWaimaiPhoto("");//详情和图片
//                            waimaiEntity.setWaimaiTypes(Integer.valueOf(data.get(0)));   //外卖类型 要改的
//                            waimaiEntity.setWaimaiKucunNumber(Integer.valueOf(data.get(0)));   //外卖库存 要改的
//                            waimaiEntity.setWaimaiPrice(Integer.valueOf(data.get(0)));   //购买获得积分 要改的
//                            waimaiEntity.setWaimaiOldMoney(data.get(0));                    //外卖原价 要改的
//                            waimaiEntity.setWaimaiNewMoney(data.get(0));                    //现价 要改的
//                            waimaiEntity.setWaimaiClicknum(Integer.valueOf(data.get(0)));   //点击次数 要改的
//                            waimaiEntity.setWaimaiContent("");//详情和图片
//                            waimaiEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            waimaiEntity.setWaimaiDelete(1);//逻辑删除字段
//                            waimaiEntity.setCreateTime(date);//时间
                            waimaiList.add(waimaiEntity);


                            //把要查询是否重复的字段放入map中
                                //外卖编号
                                if(seachFields.containsKey("waimaiUuidNumber")){
                                    List<String> waimaiUuidNumber = seachFields.get("waimaiUuidNumber");
                                    waimaiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> waimaiUuidNumber = new ArrayList<>();
                                    waimaiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("waimaiUuidNumber",waimaiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //外卖编号
                        List<WaimaiEntity> waimaiEntities_waimaiUuidNumber = waimaiService.selectList(new EntityWrapper<WaimaiEntity>().in("waimai_uuid_number", seachFields.get("waimaiUuidNumber")).eq("waimai_delete", 1));
                        if(waimaiEntities_waimaiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WaimaiEntity s:waimaiEntities_waimaiUuidNumber){
                                repeatFields.add(s.getWaimaiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [外卖编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        waimaiService.insertBatch(waimaiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = waimaiService.queryPage(params);

        //字典表数据转换
        List<WaimaiView> list =(List<WaimaiView>)page.getList();
        for(WaimaiView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WaimaiEntity waimai = waimaiService.selectById(id);
            if(waimai !=null){

                //点击数量加1
                waimai.setWaimaiClicknum(waimai.getWaimaiClicknum()+1);
                waimaiService.updateById(waimai);

                //entity转view
                WaimaiView view = new WaimaiView();
                BeanUtils.copyProperties( waimai , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody WaimaiEntity waimai, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,waimai:{}",this.getClass().getName(),waimai.toString());
        Wrapper<WaimaiEntity> queryWrapper = new EntityWrapper<WaimaiEntity>()
            .eq("waimai_uuid_number", waimai.getWaimaiUuidNumber())
            .eq("waimai_name", waimai.getWaimaiName())
            .eq("waimai_types", waimai.getWaimaiTypes())
            .eq("waimai_kucun_number", waimai.getWaimaiKucunNumber())
            .eq("waimai_price", waimai.getWaimaiPrice())
            .eq("waimai_clicknum", waimai.getWaimaiClicknum())
            .eq("shangxia_types", waimai.getShangxiaTypes())
            .eq("waimai_delete", waimai.getWaimaiDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WaimaiEntity waimaiEntity = waimaiService.selectOne(queryWrapper);
        if(waimaiEntity==null){
            waimai.setWaimaiDelete(1);
            waimai.setCreateTime(new Date());
        waimaiService.insert(waimai);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}
