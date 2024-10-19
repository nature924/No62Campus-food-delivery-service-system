package com.dao;

import com.entity.WaimaiOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WaimaiOrderView;

/**
 * 外卖订单 Dao 接口
 *
 * @author 
 */
public interface WaimaiOrderDao extends BaseMapper<WaimaiOrderEntity> {

   List<WaimaiOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
