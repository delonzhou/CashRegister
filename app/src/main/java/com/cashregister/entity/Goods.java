package com.cashregister.entity;
/**
 * @author kevin
 * @version 创建时间：2016年3月3日 下午2:42:57
 * 商品
 */
public class Goods {
	/**
	 * 数量
	 */
	private int amount;
	/**
	 * 单价
	 */
	private float price;
	/**
	 * 货物名称
	 */
	private String name;
	/**
	 * 数量单位
	 */
	private String quantityUnit;
	/**
	 * 货品折扣情况 0：无任何折扣，1：买二赠一 2：95折 3：买二赠一和95折同时成立（这种情况，只享受买二赠一）
	 */
	private int discount;

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getQuantityUnit() {
		return quantityUnit;
	}
	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	@Override
	public String toString() {
		return "Goods [amount=" + amount + ", price=" + price + ", name="
				+ name + ", quantityUnit=" + quantityUnit + ", discount="
				+ discount + "]";
	}




}
