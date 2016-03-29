package com.cashregister.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.cashregister.constant.Constant;
import com.cashregister.entity.Goods;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;

/**
 * @author kevin123
 * @version 创建时间：2016年3月9日 下午5:20:23 类说明
 */
public class Util {
	/**
	 * 从asset里面获取模拟json 数据
	 *
	 * @param context
	 * @param fileName
	 * @return
	 */
	public static String getFromAssets(Context context, String fileName) {
		String result = "";
		try {
			InputStreamReader inputReader = new InputStreamReader(context
					.getResources().getAssets().open(fileName), "utf-8");
			BufferedReader bufReader = new BufferedReader(inputReader);
			String line = "";

			while ((line = bufReader.readLine()) != null) {
				result += line;
			}
			bufReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取购物清单
	 *
	 * @param json
	 * @return
	 */
	public static String getShoppingList(String json) {

		ArrayList<Goods> goodsList = new Gson().fromJson(json,
				new TypeToken<List<Goods>>() {
				}.getType());
		StringBuilder allBuilder = new StringBuilder();
		StringBuilder discountTwoForOneBuilder = new StringBuilder();
		float allPrice = 0;// 所有种类货品的总价
		float pp = 0;//
		for (int i = 0, size = goodsList.size(); i < size; i++) {
			Goods goods = goodsList.get(i);
			float totalPrice = goods.getAmount() * goods.getPrice();// 单一种类货品的总价
			pp = pp + totalPrice;
			float save2 = 0;// 95折省的钱
			switch (goods.getDiscount()) {
				case Constant.NO_DISCOUNT:// 无优惠
					allPrice = allPrice + totalPrice;
					allBuilder.append(Constant.NAME).append(goods.getName())
							.append(Constant.COMMA).append(Constant.AMOUNT)
							.append(goods.getAmount())
							.append(goods.getQuantityUnit()).append(Constant.COMMA)
							.append(Constant.PRICE)
							.append(getTwo(goods.getPrice()))
							.append(Constant.MONEY_UNIT).append(Constant.COMMA)
							.append(Constant.TOTAL).append(getTwo(totalPrice))
							.append(Constant.MONEY_UNIT)
							.append(Constant.LINE_BREAK);
					break;
				case Constant.DISCOUNT_TWO_FOR_ONE:// 买二赠一
					allPrice = handle(discountTwoForOneBuilder, allBuilder, goods,
							totalPrice, allPrice);
					break;

				case Constant.DISCOUNT_95:// 符合95折优惠条件
					float save1 = totalPrice * 0.95f;// 优惠的价钱
					save2 = totalPrice - save1;// 优惠后的总价
					allPrice = allPrice + save1;
					allBuilder.append(Constant.NAME).append(goods.getName())
							.append(Constant.AMOUNT).append(goods.getAmount())
							.append(goods.getQuantityUnit()).append(Constant.COMMA)
							.append(Constant.PRICE).append(goods.getPrice())
							.append(Constant.MONEY_UNIT).append(Constant.COMMA)
							.append(Constant.TOTAL).append(getTwo(save1))
							.append(Constant.MONEY_UNIT).append(Constant.COMMA)
							.append(Constant.SAVE_STRING).append(getTwo(save2))
							.append(Constant.MONEY_UNIT)
							.append(Constant.LINE_BREAK);

					break;
				case Constant.DISCOUNT_ALL:// 符合95折优惠条件和买二赠一
					allPrice = handle(discountTwoForOneBuilder, allBuilder, goods,
							totalPrice, allPrice);
					break;
			}
		}
		String shoppingList = Constant.TITLE + allBuilder.toString()
				+ discountTwoForOneBuilder.toString() + Constant.CUT_OF_LINE
				+ Constant.TOTAL_P + getTwo(allPrice) + Constant.MONEY_UNIT
				+ Constant.LINE_BREAK + Constant.SAVE_STRING
				+ (getTwo(pp - allPrice)) + Constant.MONEY_UNIT
				+ Constant.LINE_BREAK + Constant.END_LINE;

		return shoppingList;
	}

	/**
	 * 保留两位小数
	 *
	 * @param num
	 *            传入的数字
	 * @return 格式话后的数字
	 */
	public static String getTwo(float num) {
		return String.format("%.2f", num);
	}

	/**
	 * 处理一些公用操作
	 *
	 * @param discountTwoForOneBuilder
	 * @param allBuilder
	 * @param goods
	 * @param totalPrice
	 * @param allPrice
	 * @return 购物车所有种类货品的总价
	 */
	public static float handle(StringBuilder discountTwoForOneBuilder,
							   StringBuilder allBuilder, Goods goods, float totalPrice,
							   float allPrice) {
		int cc = goods.getAmount();
		int c = 0;
		float cp = 0;
		if (cc >= 2) {// 大于2的偶数
			c = 1;
			cp = totalPrice - 1 * goods.getPrice();
		} else {
			c = cc;
			cp = totalPrice;
		}
		allPrice = allPrice + cp;
		if (discountTwoForOneBuilder.toString().contains(
				Constant.TWO_FOR_ONE_GOODS)) {
			discountTwoForOneBuilder.append(Constant.NAME)
					.append(goods.getName()).append(Constant.COMMA)
					.append(Constant.AMOUNT).append(c)
					.append(goods.getQuantityUnit())
					.append(Constant.LINE_BREAK);
		} else {
			discountTwoForOneBuilder.append(Constant.CUT_OF_LINE)
					.append(Constant.TWO_FOR_ONE_GOODS).append(Constant.NAME)
					.append(goods.getName()).append(Constant.COMMA)
					.append(Constant.AMOUNT).append(c)
					.append(goods.getQuantityUnit())
					.append(Constant.LINE_BREAK);
		}

		allBuilder.append(Constant.NAME).append(goods.getName())
				.append(Constant.COMMA).append(Constant.AMOUNT)
				.append(goods.getAmount()).append(goods.getQuantityUnit())
				.append(Constant.COMMA).append(Constant.PRICE)
				.append(getTwo(goods.getPrice())).append(Constant.MONEY_UNIT)
				.append(Constant.COMMA).append(Constant.TOTAL)
				.append(getTwo(cp)).append(Constant.MONEY_UNIT)
				.append(Constant.LINE_BREAK);
		return allPrice;
	}
}
