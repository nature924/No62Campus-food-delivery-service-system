package com.entity.view;

import com.entity.WaimaiOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 外卖订单
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("waimai_order")
public class WaimaiOrderView extends WaimaiOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 订单类型的值
		*/
		private String waimaiOrderValue;
		/**
		* 支付类型的值
		*/
		private String waimaiOrderPaymentValue;



		//级联表 address
			/**
			* 收货地址 的 创建用户
			*/
			private Integer addressYonghuId;
			/**
			* 收货人
			*/
			private String addressName;
			/**
			* 电话
			*/
			private String addressPhone;
			/**
			* 地址
			*/
			private String addressDizhi;
			/**
			* 是否默认地址
			*/
			private Integer isdefaultTypes;
				/**
				* 是否默认地址的值
				*/
				private String isdefaultValue;

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

	public WaimaiOrderView() {

	}

	public WaimaiOrderView(WaimaiOrderEntity waimaiOrderEntity) {
		try {
			BeanUtils.copyProperties(this, waimaiOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 订单类型的值
			*/
			public String getWaimaiOrderValue() {
				return waimaiOrderValue;
			}
			/**
			* 设置： 订单类型的值
			*/
			public void setWaimaiOrderValue(String waimaiOrderValue) {
				this.waimaiOrderValue = waimaiOrderValue;
			}
			/**
			* 获取： 支付类型的值
			*/
			public String getWaimaiOrderPaymentValue() {
				return waimaiOrderPaymentValue;
			}
			/**
			* 设置： 支付类型的值
			*/
			public void setWaimaiOrderPaymentValue(String waimaiOrderPaymentValue) {
				this.waimaiOrderPaymentValue = waimaiOrderPaymentValue;
			}




				//级联表的get和set address

					/**
					* 获取：收货地址 的 创建用户
					*/
					public Integer getAddressYonghuId() {
						return addressYonghuId;
					}
					/**
					* 设置：收货地址 的 创建用户
					*/
					public void setAddressYonghuId(Integer addressYonghuId) {
						this.addressYonghuId = addressYonghuId;
					}


					/**
					* 获取： 收货人
					*/
					public String getAddressName() {
						return addressName;
					}
					/**
					* 设置： 收货人
					*/
					public void setAddressName(String addressName) {
						this.addressName = addressName;
					}

					/**
					* 获取： 电话
					*/
					public String getAddressPhone() {
						return addressPhone;
					}
					/**
					* 设置： 电话
					*/
					public void setAddressPhone(String addressPhone) {
						this.addressPhone = addressPhone;
					}

					/**
					* 获取： 地址
					*/
					public String getAddressDizhi() {
						return addressDizhi;
					}
					/**
					* 设置： 地址
					*/
					public void setAddressDizhi(String addressDizhi) {
						this.addressDizhi = addressDizhi;
					}

					/**
					* 获取： 是否默认地址
					*/
					public Integer getIsdefaultTypes() {
						return isdefaultTypes;
					}
					/**
					* 设置： 是否默认地址
					*/
					public void setIsdefaultTypes(Integer isdefaultTypes) {
						this.isdefaultTypes = isdefaultTypes;
					}


						/**
						* 获取： 是否默认地址的值
						*/
						public String getIsdefaultValue() {
							return isdefaultValue;
						}
						/**
						* 设置： 是否默认地址的值
						*/
						public void setIsdefaultValue(String isdefaultValue) {
							this.isdefaultValue = isdefaultValue;
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
