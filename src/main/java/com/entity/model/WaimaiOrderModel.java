package com.entity.model;

import com.entity.WaimaiOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 外卖订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WaimaiOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String waimaiOrderUuidNumber;


    /**
     * 收货地址
     */
    private Integer addressId;


    /**
     * 外卖
     */
    private Integer waimaiId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买数量
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double waimaiOrderTruePrice;


    /**
     * 送餐人姓名
     */
    private String waimaiOrderCourierName;


    /**
     * 送餐人电话
     */
    private String waimaiOrderCourierNumber;


    /**
     * 订单类型
     */
    private Integer waimaiOrderTypes;


    /**
     * 支付类型
     */
    private Integer waimaiOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getWaimaiOrderUuidNumber() {
        return waimaiOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setWaimaiOrderUuidNumber(String waimaiOrderUuidNumber) {
        this.waimaiOrderUuidNumber = waimaiOrderUuidNumber;
    }
    /**
	 * 获取：收货地址
	 */
    public Integer getAddressId() {
        return addressId;
    }


    /**
	 * 设置：收货地址
	 */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
	 * 获取：外卖
	 */
    public Integer getWaimaiId() {
        return waimaiId;
    }


    /**
	 * 设置：外卖
	 */
    public void setWaimaiId(Integer waimaiId) {
        this.waimaiId = waimaiId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：购买数量
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getWaimaiOrderTruePrice() {
        return waimaiOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setWaimaiOrderTruePrice(Double waimaiOrderTruePrice) {
        this.waimaiOrderTruePrice = waimaiOrderTruePrice;
    }
    /**
	 * 获取：送餐人姓名
	 */
    public String getWaimaiOrderCourierName() {
        return waimaiOrderCourierName;
    }


    /**
	 * 设置：送餐人姓名
	 */
    public void setWaimaiOrderCourierName(String waimaiOrderCourierName) {
        this.waimaiOrderCourierName = waimaiOrderCourierName;
    }
    /**
	 * 获取：送餐人电话
	 */
    public String getWaimaiOrderCourierNumber() {
        return waimaiOrderCourierNumber;
    }


    /**
	 * 设置：送餐人电话
	 */
    public void setWaimaiOrderCourierNumber(String waimaiOrderCourierNumber) {
        this.waimaiOrderCourierNumber = waimaiOrderCourierNumber;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getWaimaiOrderTypes() {
        return waimaiOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setWaimaiOrderTypes(Integer waimaiOrderTypes) {
        this.waimaiOrderTypes = waimaiOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getWaimaiOrderPaymentTypes() {
        return waimaiOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setWaimaiOrderPaymentTypes(Integer waimaiOrderPaymentTypes) {
        this.waimaiOrderPaymentTypes = waimaiOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
