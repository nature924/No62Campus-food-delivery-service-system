package com.dao;

import com.entity.WaimaiEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WaimaiView;

/**
 * 外卖 Dao 接口
 *
 * @author 
 */
public interface WaimaiDao extends BaseMapper<WaimaiEntity> {

   List<WaimaiView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
