package com.cashregister.constant;

/**
 * @author kevin
 * @version 创建时间：2016年3月9日 上午11:57:03 常量类
 */
public class Constant {
	public static final String NAME = "名称:";
	public static final String AMOUNT = "数量:";
	public static final String PRICE = "单价:";
	public static final String TOTAL = "小计:";
	public static final String TOTAL_P = "总计:";
	public static final String LINE_BREAK = "\r\n";
	public static final String MONEY_UNIT = "(元)";
	public static final String TITLE = "***<没钱赚商店>购物清单***\r\n";
	public static final String CUT_OF_LINE = "------------------------\r\n";
	public static final String END_LINE = "********************\r\n";
	public static final String TWO_FOR_ONE_GOODS = "买二赠一商品:\r\n";
	public static final String SAVE_STRING = "节省:";
	public static final String COMMA=",";
	/**
	 * 无折扣
	 */
	public static final int NO_DISCOUNT = 0;
	/**
	 * 买二赠一
	 */
	public static final int DISCOUNT_TWO_FOR_ONE = 1;

	/**
	 * 符合95折优惠条件
	 */
	public static final int DISCOUNT_95 = 2;
	/**
	 * 符合95折优惠和买二赠一
	 */
	public static final int DISCOUNT_ALL = 3;
}
