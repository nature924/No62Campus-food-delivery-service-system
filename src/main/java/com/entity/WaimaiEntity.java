package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 外卖
 *
 * @author 
 * @email
 */
@TableName("waimai")
public class WaimaiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WaimaiEntity() {

	}

	public WaimaiEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 外卖编号
     */
    @TableField(value = "waimai_uuid_number")

    private String waimaiUuidNumber;


    /**
     * 外卖名称
     */
    @TableField(value = "waimai_name")

    private String waimaiName;


    /**
     * 外卖照片
     */
    @TableField(value = "waimai_photo")

    private String waimaiPhoto;


    /**
     * 外卖类型
     */
    @TableField(value = "waimai_types")

    private Integer waimaiTypes;


    /**
     * 外卖库存
     */
    @TableField(value = "waimai_kucun_number")

    private Integer waimaiKucunNumber;


    /**
     * 购买获得积分
     */
    @TableField(value = "waimai_price")

    private Integer waimaiPrice;


    /**
     * 外卖原价
     */
    @TableField(value = "waimai_old_money")

    private Double waimaiOldMoney;


    /**
     * 现价
     */
    @TableField(value = "waimai_new_money")

    private Double waimaiNewMoney;


    /**
     * 点击次数
     */
    @TableField(value = "waimai_clicknum")

    private Integer waimaiClicknum;


    /**
     * 菜品介绍
     */
    @TableField(value = "waimai_content")

    private String waimaiContent;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "waimai_delete")

    private Integer waimaiDelete;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：外卖编号
	 */
    public String getWaimaiUuidNumber() {
        return waimaiUuidNumber;
    }
    /**
	 * 获取：外卖编号
	 */

    public void setWaimaiUuidNumber(String waimaiUuidNumber) {
        this.waimaiUuidNumber = waimaiUuidNumber;
    }
    /**
	 * 设置：外卖名称
	 */
    public String getWaimaiName() {
        return waimaiName;
    }
    /**
	 * 获取：外卖名称
	 */

    public void setWaimaiName(String waimaiName) {
        this.waimaiName = waimaiName;
    }
    /**
	 * 设置：外卖照片
	 */
    public String getWaimaiPhoto() {
        return waimaiPhoto;
    }
    /**
	 * 获取：外卖照片
	 */

    public void setWaimaiPhoto(String waimaiPhoto) {
        this.waimaiPhoto = waimaiPhoto;
    }
    /**
	 * 设置：外卖类型
	 */
    public Integer getWaimaiTypes() {
        return waimaiTypes;
    }
    /**
	 * 获取：外卖类型
	 */

    public void setWaimaiTypes(Integer waimaiTypes) {
        this.waimaiTypes = waimaiTypes;
    }
    /**
	 * 设置：外卖库存
	 */
    public Integer getWaimaiKucunNumber() {
        return waimaiKucunNumber;
    }
    /**
	 * 获取：外卖库存
	 */

    public void setWaimaiKucunNumber(Integer waimaiKucunNumber) {
        this.waimaiKucunNumber = waimaiKucunNumber;
    }
    /**
	 * 设置：购买获得积分
	 */
    public Integer getWaimaiPrice() {
        return waimaiPrice;
    }
    /**
	 * 获取：购买获得积分
	 */

    public void setWaimaiPrice(Integer waimaiPrice) {
        this.waimaiPrice = waimaiPrice;
    }
    /**
	 * 设置：外卖原价
	 */
    public Double getWaimaiOldMoney() {
        return waimaiOldMoney;
    }
    /**
	 * 获取：外卖原价
	 */

    public void setWaimaiOldMoney(Double waimaiOldMoney) {
        this.waimaiOldMoney = waimaiOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getWaimaiNewMoney() {
        return waimaiNewMoney;
    }
    /**
	 * 获取：现价
	 */

    public void setWaimaiNewMoney(Double waimaiNewMoney) {
        this.waimaiNewMoney = waimaiNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getWaimaiClicknum() {
        return waimaiClicknum;
    }
    /**
	 * 获取：点击次数
	 */

    public void setWaimaiClicknum(Integer waimaiClicknum) {
        this.waimaiClicknum = waimaiClicknum;
    }
    /**
	 * 设置：菜品介绍
	 */
    public String getWaimaiContent() {
        return waimaiContent;
    }
    /**
	 * 获取：菜品介绍
	 */

    public void setWaimaiContent(String waimaiContent) {
        this.waimaiContent = waimaiContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }
    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getWaimaiDelete() {
        return waimaiDelete;
    }
    /**
	 * 获取：逻辑删除
	 */

    public void setWaimaiDelete(Integer waimaiDelete) {
        this.waimaiDelete = waimaiDelete;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Waimai{" +
            "id=" + id +
            ", waimaiUuidNumber=" + waimaiUuidNumber +
            ", waimaiName=" + waimaiName +
            ", waimaiPhoto=" + waimaiPhoto +
            ", waimaiTypes=" + waimaiTypes +
            ", waimaiKucunNumber=" + waimaiKucunNumber +
            ", waimaiPrice=" + waimaiPrice +
            ", waimaiOldMoney=" + waimaiOldMoney +
            ", waimaiNewMoney=" + waimaiNewMoney +
            ", waimaiClicknum=" + waimaiClicknum +
            ", waimaiContent=" + waimaiContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", waimaiDelete=" + waimaiDelete +
            ", createTime=" + createTime +
        "}";
    }
}
