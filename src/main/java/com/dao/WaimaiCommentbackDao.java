package com.dao;

import com.entity.WaimaiCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WaimaiCommentbackView;

/**
 * 外卖评价 Dao 接口
 *
 * @author 
 */
public interface WaimaiCommentbackDao extends BaseMapper<WaimaiCommentbackEntity> {

   List<WaimaiCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
