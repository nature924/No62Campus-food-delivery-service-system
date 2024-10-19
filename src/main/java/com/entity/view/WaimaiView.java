package com.entity.view;

import com.entity.WaimaiEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 外卖
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("waimai")
public class WaimaiView extends WaimaiEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 外卖类型的值
		*/
		private String waimaiValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



	public WaimaiView() {

	}

	public WaimaiView(WaimaiEntity waimaiEntity) {
		try {
			BeanUtils.copyProperties(this, waimaiEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 外卖类型的值
			*/
			public String getWaimaiValue() {
				return waimaiValue;
			}
			/**
			* 设置： 外卖类型的值
			*/
			public void setWaimaiValue(String waimaiValue) {
				this.waimaiValue = waimaiValue;
			}
			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}












}
