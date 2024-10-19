package com.entity.view;

import com.entity.WaimaiCommentbackEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 外卖评价
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("waimai_commentback")
public class WaimaiCommentbackView extends WaimaiCommentbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 waimai
			/**
			* 外卖编号
			*/
			private String waimaiUuidNumber;
			/**
			* 外卖名称
			*/
			private String waimaiName;
			/**
			* 外卖照片
			*/
			private String waimaiPhoto;
			/**
			* 外卖类型
			*/
			private Integer waimaiTypes;
				/**
				* 外卖类型的值
				*/
				private String waimaiValue;
			/**
			* 外卖原价
			*/
			private Double waimaiOldMoney;
			/**
			* 现价
			*/
			private Double waimaiNewMoney;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 余额
			*/
			private Double newMoney;

	public WaimaiCommentbackView() {

	}

	public WaimaiCommentbackView(WaimaiCommentbackEntity waimaiCommentbackEntity) {
		try {
			BeanUtils.copyProperties(this, waimaiCommentbackEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



















				//级联表的get和set waimai

					/**
					* 获取： 外卖编号
					*/
					public String getWaimaiUuidNumber() {
						return waimaiUuidNumber;
					}
					/**
					* 设置： 外卖编号
					*/
					public void setWaimaiUuidNumber(String waimaiUuidNumber) {
						this.waimaiUuidNumber = waimaiUuidNumber;
					}

					/**
					* 获取： 外卖名称
					*/
					public String getWaimaiName() {
						return waimaiName;
					}
					/**
					* 设置： 外卖名称
					*/
					public void setWaimaiName(String waimaiName) {
						this.waimaiName = waimaiName;
					}

					/**
					* 获取： 外卖照片
					*/
					public String getWaimaiPhoto() {
						return waimaiPhoto;
					}
					/**
					* 设置： 外卖照片
					*/
					public void setWaimaiPhoto(String waimaiPhoto) {
						this.waimaiPhoto = waimaiPhoto;
					}

					/**
					* 获取： 外卖类型
					*/
					public Integer getWaimaiTypes() {
						return waimaiTypes;
					}
					/**
					* 设置： 外卖类型
					*/
					public void setWaimaiTypes(Integer waimaiTypes) {
						this.waimaiTypes = waimaiTypes;
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
					* 获取： 外卖原价
					*/
					public Double getWaimaiOldMoney() {
						return waimaiOldMoney;
					}
					/**
					* 设置： 外卖原价
					*/
					public void setWaimaiOldMoney(Double waimaiOldMoney) {
						this.waimaiOldMoney = waimaiOldMoney;
					}

					/**
					* 获取： 现价
					*/
					public Double getWaimaiNewMoney() {
						return waimaiNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setWaimaiNewMoney(Double waimaiNewMoney) {
						this.waimaiNewMoney = waimaiNewMoney;
					}













				//级联表的get和set yonghu

					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}

					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}

					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}

					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}



}
