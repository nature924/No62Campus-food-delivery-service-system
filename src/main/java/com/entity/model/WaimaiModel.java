package com.entity.model;

import com.entity.WaimaiEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 外卖
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WaimaiModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


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
     * 外卖库存
     */
    private Integer waimaiKucunNumber;


    /**
     * 购买获得积分
     */
    private Integer waimaiPrice;


    /**
     * 外卖原价
     */
    private Double waimaiOldMoney;


    /**
     * 现价
     */
    private Double waimaiNewMoney;


    /**
     * 点击次数
     */
    private Integer waimaiClicknum;


    /**
     * 菜品介绍
     */
    private String waimaiContent;


    /**
     * 是否上架
     */
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    private Integer waimaiDelete;


    /**
     * 创建时间  show1 show2 photoShow
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
	 * 获取：外卖编号
	 */
    public String getWaimaiUuidNumber() {
        return waimaiUuidNumber;
    }


    /**
	 * 设置：外卖编号
	 */
    public void setWaimaiUuidNumber(String waimaiUuidNumber) {
        this.waimaiUuidNumber = waimaiUuidNumber;
    }
    /**
	 * 获取：外卖名称
	 */
    public String getWaimaiName() {
        return waimaiName;
    }


    /**
	 * 设置：外卖名称
	 */
    public void setWaimaiName(String waimaiName) {
        this.waimaiName = waimaiName;
    }
    /**
	 * 获取：外卖照片
	 */
    public String getWaimaiPhoto() {
        return waimaiPhoto;
    }


    /**
	 * 设置：外卖照片
	 */
    public void setWaimaiPhoto(String waimaiPhoto) {
        this.waimaiPhoto = waimaiPhoto;
    }
    /**
	 * 获取：外卖类型
	 */
    public Integer getWaimaiTypes() {
        return waimaiTypes;
    }


    /**
	 * 设置：外卖类型
	 */
    public void setWaimaiTypes(Integer waimaiTypes) {
        this.waimaiTypes = waimaiTypes;
    }
    /**
	 * 获取：外卖库存
	 */
    public Integer getWaimaiKucunNumber() {
        return waimaiKucunNumber;
    }


    /**
	 * 设置：外卖库存
	 */
    public void setWaimaiKucunNumber(Integer waimaiKucunNumber) {
        this.waimaiKucunNumber = waimaiKucunNumber;
    }
    /**
	 * 获取：购买获得积分
	 */
    public Integer getWaimaiPrice() {
        return waimaiPrice;
    }


    /**
	 * 设置：购买获得积分
	 */
    public void setWaimaiPrice(Integer waimaiPrice) {
        this.waimaiPrice = waimaiPrice;
    }
    /**
	 * 获取：外卖原价
	 */
    public Double getWaimaiOldMoney() {
        return waimaiOldMoney;
    }


    /**
	 * 设置：外卖原价
	 */
    public void setWaimaiOldMoney(Double waimaiOldMoney) {
        this.waimaiOldMoney = waimaiOldMoney;
    }
    /**
	 * 获取：现价
	 */
    public Double getWaimaiNewMoney() {
        return waimaiNewMoney;
    }


    /**
	 * 设置：现价
	 */
    public void setWaimaiNewMoney(Double waimaiNewMoney) {
        this.waimaiNewMoney = waimaiNewMoney;
    }
    /**
	 * 获取：点击次数
	 */
    public Integer getWaimaiClicknum() {
        return waimaiClicknum;
    }


    /**
	 * 设置：点击次数
	 */
    public void setWaimaiClicknum(Integer waimaiClicknum) {
        this.waimaiClicknum = waimaiClicknum;
    }
    /**
	 * 获取：菜品介绍
	 */
    public String getWaimaiContent() {
        return waimaiContent;
    }


    /**
	 * 设置：菜品介绍
	 */
    public void setWaimaiContent(String waimaiContent) {
        this.waimaiContent = waimaiContent;
    }
    /**
	 * 获取：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 设置：是否上架
	 */
    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getWaimaiDelete() {
        return waimaiDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setWaimaiDelete(Integer waimaiDelete) {
        this.waimaiDelete = waimaiDelete;
    }
    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
