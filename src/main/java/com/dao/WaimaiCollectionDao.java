package com.dao;

import com.entity.WaimaiCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WaimaiCollectionView;

/**
 * 外卖收藏 Dao 接口
 *
 * @author 
 */
public interface WaimaiCollectionDao extends BaseMapper<WaimaiCollectionEntity> {

   List<WaimaiCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
