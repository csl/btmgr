package com.socialphone;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.widget.SimpleAdapter;

public class MenuHandler {
	/**
	 * ?ÑÈ??úÂ?Adapter
	 * 
	 * @param menuNameArray
	 *            ?çÁß∞
	 * @param imageResourceArray
	 *            ?æÁ?
	 * @return SimpleAdapter
	 */
	public static  SimpleAdapter getMenuAdapter(Context context, String[] menuNameArray, int[] imageResourceArray) {
		ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
		for (int i = 0; i < menuNameArray.length; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", imageResourceArray[i]);
			map.put("itemText", menuNameArray[i]);
			data.add(map);
		}
		SimpleAdapter simperAdapter = new SimpleAdapter(context, data, R.layout.item_menu, new String[] { "itemImage",
				"itemText" }, new int[] { R.id.item_image, R.id.item_text });
		return simperAdapter;
	}
}
