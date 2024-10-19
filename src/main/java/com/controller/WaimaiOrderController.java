
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
 * 外卖订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/waimaiOrder")
public class WaimaiOrderController {
    private static final Logger logger = LoggerFactory.getLogger(WaimaiOrderController.class);

    @Autowired
    private WaimaiOrderService waimaiOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private AddressService addressService;
    @Autowired
    private WaimaiService waimaiService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private CartService cartService;
@Autowired
private WaimaiCommentbackService waimaiCommentbackService;



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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = waimaiOrderService.queryPage(params);

        //字典表数据转换
        List<WaimaiOrderView> list =(List<WaimaiOrderView>)page.getList();
        for(WaimaiOrderView c:list){
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
        WaimaiOrderEntity waimaiOrder = waimaiOrderService.selectById(id);
        if(waimaiOrder !=null){
            //entity转view
            WaimaiOrderView view = new WaimaiOrderView();
            BeanUtils.copyProperties( waimaiOrder , view );//把实体数据重构到view中

                //级联表
                AddressEntity address = addressService.selectById(waimaiOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                    view.setAddressYonghuId(address.getYonghuId());
                }
                //级联表
                WaimaiEntity waimai = waimaiService.selectById(waimaiOrder.getWaimaiId());
                if(waimai != null){
                    BeanUtils.copyProperties( waimai , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWaimaiId(waimai.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(waimaiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R save(@RequestBody WaimaiOrderEntity waimaiOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,waimaiOrder:{}",this.getClass().getName(),waimaiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            waimaiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        waimaiOrder.setInsertTime(new Date());
        waimaiOrder.setCreateTime(new Date());
        waimaiOrderService.insert(waimaiOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody WaimaiOrderEntity waimaiOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,waimaiOrder:{}",this.getClass().getName(),waimaiOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            waimaiOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<WaimaiOrderEntity> queryWrapper = new EntityWrapper<WaimaiOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        WaimaiOrderEntity waimaiOrderEntity = waimaiOrderService.selectOne(queryWrapper);
        if(waimaiOrderEntity==null){
            waimaiOrderService.updateById(waimaiOrder);//根据id更新
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
        waimaiOrderService.deleteBatchIds(Arrays.asList(ids));
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
            List<WaimaiOrderEntity> waimaiOrderList = new ArrayList<>();//上传的东西
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
                            WaimaiOrderEntity waimaiOrderEntity = new WaimaiOrderEntity();
//                            waimaiOrderEntity.setWaimaiOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            waimaiOrderEntity.setAddressId(Integer.valueOf(data.get(0)));   //收货地址 要改的
//                            waimaiOrderEntity.setWaimaiId(Integer.valueOf(data.get(0)));   //外卖 要改的
//                            waimaiOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            waimaiOrderEntity.setBuyNumber(Integer.valueOf(data.get(0)));   //购买数量 要改的
//                            waimaiOrderEntity.setWaimaiOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            waimaiOrderEntity.setWaimaiOrderCourierName(data.get(0));                    //送餐人姓名 要改的
//                            waimaiOrderEntity.setWaimaiOrderCourierNumber(data.get(0));                    //送餐人电话 要改的
//                            waimaiOrderEntity.setWaimaiOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            waimaiOrderEntity.setWaimaiOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            waimaiOrderEntity.setInsertTime(date);//时间
//                            waimaiOrderEntity.setCreateTime(date);//时间
                            waimaiOrderList.add(waimaiOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("waimaiOrderUuidNumber")){
                                    List<String> waimaiOrderUuidNumber = seachFields.get("waimaiOrderUuidNumber");
                                    waimaiOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> waimaiOrderUuidNumber = new ArrayList<>();
                                    waimaiOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("waimaiOrderUuidNumber",waimaiOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<WaimaiOrderEntity> waimaiOrderEntities_waimaiOrderUuidNumber = waimaiOrderService.selectList(new EntityWrapper<WaimaiOrderEntity>().in("waimai_order_uuid_number", seachFields.get("waimaiOrderUuidNumber")));
                        if(waimaiOrderEntities_waimaiOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(WaimaiOrderEntity s:waimaiOrderEntities_waimaiOrderUuidNumber){
                                repeatFields.add(s.getWaimaiOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        waimaiOrderService.insertBatch(waimaiOrderList);
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
        PageUtils page = waimaiOrderService.queryPage(params);

        //字典表数据转换
        List<WaimaiOrderView> list =(List<WaimaiOrderView>)page.getList();
        for(WaimaiOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        WaimaiOrderEntity waimaiOrder = waimaiOrderService.selectById(id);
            if(waimaiOrder !=null){


                //entity转view
                WaimaiOrderView view = new WaimaiOrderView();
                BeanUtils.copyProperties( waimaiOrder , view );//把实体数据重构到view中

                //级联表
                    AddressEntity address = addressService.selectById(waimaiOrder.getAddressId());
                if(address != null){
                    BeanUtils.copyProperties( address , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setAddressId(address.getId());
                }
                //级联表
                    WaimaiEntity waimai = waimaiService.selectById(waimaiOrder.getWaimaiId());
                if(waimai != null){
                    BeanUtils.copyProperties( waimai , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setWaimaiId(waimai.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(waimaiOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody WaimaiOrderEntity waimaiOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,waimaiOrder:{}",this.getClass().getName(),waimaiOrder.toString());
            WaimaiEntity waimaiEntity = waimaiService.selectById(waimaiOrder.getWaimaiId());
            if(waimaiEntity == null){
                return R.error(511,"查不到该外卖");
            }
            // Double waimaiNewMoney = waimaiEntity.getWaimaiNewMoney();

            if(false){
            }
            else if((waimaiEntity.getWaimaiKucunNumber() -waimaiOrder.getBuyNumber())<0){
                return R.error(511,"购买数量不能大于库存数量");
            }
            else if(waimaiEntity.getWaimaiNewMoney() == null){
                return R.error(511,"外卖价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - waimaiEntity.getWaimaiNewMoney()*waimaiOrder.getBuyNumber();//余额
            buyJifen = new BigDecimal(waimaiEntity.getWaimaiPrice()).multiply(new BigDecimal(waimaiOrder.getBuyNumber())).doubleValue();//所获积分
            if(balance<0)
                return R.error(511,"余额不够支付");
            waimaiOrder.setWaimaiOrderTypes(3); //设置订单状态为已支付
            waimaiOrder.setWaimaiOrderTruePrice(waimaiEntity.getWaimaiNewMoney()*waimaiOrder.getBuyNumber()); //设置实付价格
            waimaiOrder.setYonghuId(userId); //设置订单支付人id
            waimaiOrder.setWaimaiOrderUuidNumber(String.valueOf(new Date().getTime()));
            waimaiOrder.setWaimaiOrderPaymentTypes(1);
            waimaiOrder.setInsertTime(new Date());
            waimaiOrder.setCreateTime(new Date());
                waimaiEntity.setWaimaiKucunNumber( waimaiEntity.getWaimaiKucunNumber() -waimaiOrder.getBuyNumber());
                waimaiService.updateById(waimaiEntity);
                waimaiOrderService.insert(waimaiOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
            yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);
            yonghuService.updateById(yonghuEntity);
            return R.ok();
    }
    /**
     * 添加订单
     */
    @RequestMapping("/order")
    public R add(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("order方法:,,Controller:{},,params:{}",this.getClass().getName(),params.toString());
        String waimaiOrderUuidNumber = String.valueOf(new Date().getTime());

        //获取当前登录用户的id
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        Integer addressId = Integer.valueOf(String.valueOf(params.get("addressId")));

        Integer waimaiOrderPaymentTypes = Integer.valueOf(String.valueOf(params.get("waimaiOrderPaymentTypes")));//支付类型

        String data = String.valueOf(params.get("waimais"));
        JSONArray jsonArray = JSON.parseArray(data);
        List<Map> waimais = JSON.parseObject(jsonArray.toString(), List.class);

        //获取当前登录用户的个人信息
        YonghuEntity yonghuEntity = yonghuService.selectById(userId);

        //当前订单表
        List<WaimaiOrderEntity> waimaiOrderList = new ArrayList<>();
        //商品表
        List<WaimaiEntity> waimaiList = new ArrayList<>();
        //购物车ids
        List<Integer> cartIds = new ArrayList<>();

        BigDecimal zhekou = new BigDecimal(1.0);
        // 获取折扣
        Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                .eq("dic_code", "huiyuandengji_types")
                .eq("dic_name", "会员等级类型")
                .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                ;
        DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
        if(dictionaryEntity != null ){
            zhekou = BigDecimal.valueOf(Double.valueOf(dictionaryEntity.getBeizhu()));
        }

        //循环取出需要的数据
        for (Map<String, Object> map : waimais) {
           //取值
            Integer waimaiId = Integer.valueOf(String.valueOf(map.get("waimaiId")));//商品id
            Integer buyNumber = Integer.valueOf(String.valueOf(map.get("buyNumber")));//购买数量
            WaimaiEntity waimaiEntity = waimaiService.selectById(waimaiId);//购买的商品
            String id = String.valueOf(map.get("id"));
            if(StringUtil.isNotEmpty(id))
                cartIds.add(Integer.valueOf(id));

            //判断商品的库存是否足够
            if(waimaiEntity.getWaimaiKucunNumber() < buyNumber){
                //商品库存不足直接返回
                return R.error(waimaiEntity.getWaimaiName()+"的库存不足");
            }else{
                //商品库存充足就减库存
                waimaiEntity.setWaimaiKucunNumber(waimaiEntity.getWaimaiKucunNumber() - buyNumber);
            }

            //订单信息表增加数据
            WaimaiOrderEntity waimaiOrderEntity = new WaimaiOrderEntity<>();

            //赋值订单信息
            waimaiOrderEntity.setWaimaiOrderUuidNumber(waimaiOrderUuidNumber);//订单号
            waimaiOrderEntity.setAddressId(addressId);//收货地址
            waimaiOrderEntity.setWaimaiId(waimaiId);//外卖
            waimaiOrderEntity.setYonghuId(userId);//用户
            waimaiOrderEntity.setBuyNumber(buyNumber);//购买数量 ？？？？？？
            waimaiOrderEntity.setWaimaiOrderTypes(3);//订单类型
            waimaiOrderEntity.setWaimaiOrderPaymentTypes(waimaiOrderPaymentTypes);//支付类型
            waimaiOrderEntity.setInsertTime(new Date());//订单创建时间
            waimaiOrderEntity.setCreateTime(new Date());//创建时间

            //判断是什么支付方式 1代表余额 2代表积分
            if(waimaiOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = new BigDecimal(waimaiEntity.getWaimaiNewMoney()).multiply(new BigDecimal(buyNumber)).multiply(zhekou).doubleValue();

                if(yonghuEntity.getNewMoney() - money <0 ){
                    return R.error("余额不足,请充值！！！");
                }else{
                    //计算所获得积分
                    Double buyJifen =0.0;
                        buyJifen = new BigDecimal(waimaiEntity.getWaimaiPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                    yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() - money); //设置金额
                    yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() + buyJifen); //设置总积分
                    yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + buyJifen); //设置现积分
                        if(yonghuEntity.getYonghuSumJifen()  < 10000)
                            yonghuEntity.setHuiyuandengjiTypes(1);
                        else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                            yonghuEntity.setHuiyuandengjiTypes(2);
                        else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                            yonghuEntity.setHuiyuandengjiTypes(3);


                    waimaiOrderEntity.setWaimaiOrderTruePrice(money);

                }
            }
            waimaiOrderList.add(waimaiOrderEntity);
            waimaiList.add(waimaiEntity);

        }
        waimaiOrderService.insertBatch(waimaiOrderList);
        waimaiService.updateBatchById(waimaiList);
        yonghuService.updateById(yonghuEntity);
        if(cartIds != null && cartIds.size()>0)
            cartService.deleteBatchIds(cartIds);
        return R.ok();
    }











    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

            WaimaiOrderEntity waimaiOrder = waimaiOrderService.selectById(id);
            Integer buyNumber = waimaiOrder.getBuyNumber();
            Integer waimaiOrderPaymentTypes = waimaiOrder.getWaimaiOrderPaymentTypes();
            Integer waimaiId = waimaiOrder.getWaimaiId();
            if(waimaiId == null)
                return R.error(511,"查不到该外卖");
            WaimaiEntity waimaiEntity = waimaiService.selectById(waimaiId);
            if(waimaiEntity == null)
                return R.error(511,"查不到该外卖");
            Double waimaiNewMoney = waimaiEntity.getWaimaiNewMoney();
            if(waimaiNewMoney == null)
                return R.error(511,"外卖价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;
            // 获取折扣
            Wrapper<DictionaryEntity> dictionary = new EntityWrapper<DictionaryEntity>()
                    .eq("dic_code", "huiyuandengji_types")
                    .eq("dic_name", "会员等级类型")
                    .eq("code_index", yonghuEntity.getHuiyuandengjiTypes())
                    ;
            DictionaryEntity dictionaryEntity = dictionaryService.selectOne(dictionary);
            if(dictionaryEntity != null ){
                zhekou = Double.valueOf(dictionaryEntity.getBeizhu());
            }


            //判断是什么支付方式 1代表余额 2代表积分
            if(waimaiOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = waimaiEntity.getWaimaiNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                buyJifen = new BigDecimal(waimaiEntity.getWaimaiPrice()).multiply(new BigDecimal(buyNumber)).doubleValue();
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额
                yonghuEntity.setYonghuSumJifen(yonghuEntity.getYonghuSumJifen() - buyJifen); //设置总积分
                if(yonghuEntity.getYonghuNewJifen() - buyJifen <0 )
                    return R.error("积分已经消费,无法退款！！！");
                yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() - buyJifen); //设置现积分

                if(yonghuEntity.getYonghuSumJifen()  < 10000)
                    yonghuEntity.setHuiyuandengjiTypes(1);
                else if(yonghuEntity.getYonghuSumJifen()  < 100000)
                    yonghuEntity.setHuiyuandengjiTypes(2);
                else if(yonghuEntity.getYonghuSumJifen()  < 1000000)
                    yonghuEntity.setHuiyuandengjiTypes(3);

            }

            waimaiEntity.setWaimaiKucunNumber(waimaiEntity.getWaimaiKucunNumber() + buyNumber);



            waimaiOrder.setWaimaiOrderTypes(2);//设置订单状态为退款
            waimaiOrderService.updateById(waimaiOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            waimaiService.updateById(waimaiEntity);//更新订单中外卖的信息
            return R.ok();
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id ,String waimaiOrderCourierNumber, String waimaiOrderCourierName){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        WaimaiOrderEntity  waimaiOrderEntity = new  WaimaiOrderEntity();;
        waimaiOrderEntity.setId(id);
        waimaiOrderEntity.setWaimaiOrderTypes(4);
        waimaiOrderEntity.setWaimaiOrderCourierNumber(waimaiOrderCourierNumber);
        waimaiOrderEntity.setWaimaiOrderCourierName(waimaiOrderCourierName);
        boolean b =  waimaiOrderService.updateById( waimaiOrderEntity);
        if(!b){
            return R.error("发货出错");
        }
        return R.ok();
    }









    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        WaimaiOrderEntity  waimaiOrderEntity = new  WaimaiOrderEntity();
        waimaiOrderEntity.setId(id);
        waimaiOrderEntity.setWaimaiOrderTypes(5);
        boolean b =  waimaiOrderService.updateById( waimaiOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText, Integer waimaiCommentbackPingfenNumber, HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
            WaimaiOrderEntity waimaiOrder = waimaiOrderService.selectById(id);
        if(waimaiOrder == null)
            return R.error(511,"查不到该订单");
        if(waimaiOrder.getWaimaiOrderTypes() != 5)
            return R.error(511,"您不能评价");
        Integer waimaiId = waimaiOrder.getWaimaiId();
        if(waimaiId == null)
            return R.error(511,"查不到该外卖");

        WaimaiCommentbackEntity waimaiCommentbackEntity = new WaimaiCommentbackEntity();
            waimaiCommentbackEntity.setId(id);
            waimaiCommentbackEntity.setWaimaiId(waimaiId);
            waimaiCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            waimaiCommentbackEntity.setWaimaiCommentbackText(commentbackText);
            waimaiCommentbackEntity.setInsertTime(new Date());
            waimaiCommentbackEntity.setReplyText(null);
            waimaiCommentbackEntity.setUpdateTime(null);
            waimaiCommentbackEntity.setCreateTime(new Date());
            waimaiCommentbackService.insert(waimaiCommentbackEntity);

            waimaiOrder.setWaimaiOrderTypes(1);//设置订单状态为已评价
            waimaiOrderService.updateById(waimaiOrder);//根据id更新
            return R.ok();
    }







}
