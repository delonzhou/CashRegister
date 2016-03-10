package com.cashregister.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

import com.cashregister.R;
import com.cashregister.util.Util;

public class MainActivity extends ActionBarActivity {
	private TextView txShoppingList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txShoppingList = (TextView) findViewById(R.id.tx_shopping_list);
		String shoppingList1 = Util.getShoppingList(Util.getFromAssets(this,
				"json1.json"));// 无优惠，无优惠，无优惠
		txShoppingList.setText(shoppingList1);

	}

	public void btnClick(View view) {

		switch (view.getId()) {
			case R.id.btn1:// 无优惠，无优惠，无优惠
				String shoppingList1 = Util.getShoppingList(Util.getFromAssets(
						this, "json1.json"));
				txShoppingList.setText(shoppingList1);
				break;

			case R.id.btn2:// 无优惠，买二赠一，95折
				String shoppingList2 = Util.getShoppingList(Util.getFromAssets(
						this, "json2.json"));
				txShoppingList.setText(shoppingList2);
				break;
			case R.id.btn3:// 买二赠一95折共存，买二赠一，95折
				String shoppingList3 = Util.getShoppingList(Util.getFromAssets(
						this, "json3.json"));
				txShoppingList.setText(shoppingList3);
				break;
		}

	}

}
