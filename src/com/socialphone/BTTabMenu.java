package com.socialphone;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.PendingIntent.OnFinished;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.text.format.Time;
import android.util.Log;
import android.util.SparseBooleanArray;

public class BTTabMenu extends Activity
{

	LinearLayout layContent = null;
	Button btnPrev = null;
	Button btnToday = null;
	Button btnNext = null;
	public static final int SELECT_DATE_REQUEST = 111;
	private static int iDayCellSize = 45;// 38;
	public static String currentSelectDate;
	private GridView toolbarGrid;
	
	BTTabMenu my = this;

	int[] menu_toolbar_image_array = { R.drawable.menu_edit, R.drawable.controlbar_window,
			R.drawable.menu_add_to_bookmark, R.drawable.menu_noteve, R.drawable.menu_quit };
	
	String id="";
	
	String[] menu_toolbar_name_array = { "display", "Error", "Language", "Monitor", "Contact" };

	Time gpsLastUpdateTime = null;
	public static String City = "";
	WindowManager.LayoutParams systemLayoutParams;
	WindowManager systemWm;
	Boolean doExit = false;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
		iDayCellSize = display.getWidth() / 8;
		int height = display.getHeight();

		setContentView(R.layout.bttabmenu);

		// setContentView(generateContentView());
		//generateContentView();
        Bundle extras = getIntent().getExtras(); 
        

        if (extras != null) {
        	id = extras.getString("saddr");
            // and get whatever type user account id is
        }
        else
        	id = "5";
        
		toolbarGrid = (GridView) findViewById(R.id.GridView_toolbar);
		toolbarGrid.setSelector(R.drawable.toolbar_menu_item);
		toolbarGrid.setBackgroundResource(R.drawable.menu_bg2);
		toolbarGrid.setNumColumns(5);
		toolbarGrid.setGravity(Gravity.CENTER);
		toolbarGrid.setVerticalSpacing(10);
		toolbarGrid.setHorizontalSpacing(10);
		toolbarGrid.setAdapter(MenuHandler.getMenuAdapter(this, menu_toolbar_name_array, menu_toolbar_image_array));// 设置菜单Adapter

		toolbarGrid.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				switch (arg2) {
				case 0:
					
					String output_energy = "a," + id + ",3,0,c2,0,1,78,78,d";
					try {
						BluetoothMgr.bm.sendMessage(output_energy);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String output_voltage = "a," + id + ",3,0,c0,0,1,85,b2,d";
					try {
						BluetoothMgr.bm.sendMessage(output_voltage);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					String output_current = "a," + id + ",3,0,c1,0,1,d4,72,d";
					try {
						BluetoothMgr.bm.sendMessage(output_current);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//BluetoothMgr.bm.sendMessage(output_current);
					
					String input_energy = "a," + id + ",3,0,be,0,2,a5,ab,d";
					try {
						BluetoothMgr.bm.sendMessage(input_energy);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
					//BluetoothMgr.bm.sendMessage(input_energy);
					String input_voltage = "a," + id + ",3,0,ba,0,2,a5,ab,d";
					try {
						BluetoothMgr.bm.sendMessage(input_voltage);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}						
					//BluetoothMgr.bm.sendMessage(input_voltage);
					String input_current = "a," + id + ",3,0,bd,0,2,a5,ab,d";
					//BluetoothMgr.bm.sendMessage(input_current);
					try {
						BluetoothMgr.bm.sendMessage(input_current);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
						
					setContentView(R.layout.bttabmenu);
					toolbarGrid = (GridView) findViewById(R.id.GridView_toolbar);
					toolbarGrid.setSelector(R.drawable.toolbar_menu_item);
					toolbarGrid.setBackgroundResource(R.drawable.menu_bg2);
					toolbarGrid.setNumColumns(5);
					toolbarGrid.setGravity(Gravity.CENTER);
					toolbarGrid.setVerticalSpacing(10);
					toolbarGrid.setHorizontalSpacing(10);
					toolbarGrid.setAdapter(MenuHandler.getMenuAdapter(my, menu_toolbar_name_array, menu_toolbar_image_array));// 设置菜单Adapter
					toolbarGrid.setOnItemClickListener(this);
					break;
				case 1:
					String err_code = "a," + id + ",3,0,b6,0,4,a4,6b,d";
					try {
						BluetoothMgr.bm.sendMessage(err_code);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}							
					break;
				case 2:
					setContentView(R.layout.language);
					toolbarGrid = (GridView) findViewById(R.id.GridView_toolbar);
					toolbarGrid.setSelector(R.drawable.toolbar_menu_item);
					toolbarGrid.setBackgroundResource(R.drawable.menu_bg2);
					toolbarGrid.setNumColumns(5);
					toolbarGrid.setGravity(Gravity.CENTER);
					toolbarGrid.setVerticalSpacing(10);
					toolbarGrid.setHorizontalSpacing(10);
					toolbarGrid.setAdapter(MenuHandler.getMenuAdapter(my, menu_toolbar_name_array, menu_toolbar_image_array));// 设置菜单Adapter
					toolbarGrid.setOnItemClickListener(this);
					break;
				case 3:
					String device_id = "a," + id + ",3,0,77,0,8,f5,92,d";
					try {
						BluetoothMgr.bm.sendMessage(device_id);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}						
					String device_type = "a," + id + ",3,0,6f,0,8,75,95,d";
					try {
						BluetoothMgr.bm.sendMessage(device_type);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					output_energy = "a," + id + ",3,0,c2,0,1,78,78,d";
					try {
						BluetoothMgr.bm.sendMessage(output_energy);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					output_voltage = "a," + id + ",3,0,c0,0,1,85,b2,d";
					try {
						BluetoothMgr.bm.sendMessage(output_voltage);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					output_current = "a," + id + ",3,0,c1,0,1,d4,72,d";
					try {
						BluetoothMgr.bm.sendMessage(output_current);
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
					setContentView(R.layout.monitor);
					toolbarGrid = (GridView) findViewById(R.id.GridView_toolbar);
					toolbarGrid.setSelector(R.drawable.toolbar_menu_item);
					toolbarGrid.setBackgroundResource(R.drawable.menu_bg2);
					toolbarGrid.setNumColumns(5);
					toolbarGrid.setGravity(Gravity.CENTER);
					toolbarGrid.setVerticalSpacing(10);
					toolbarGrid.setHorizontalSpacing(10);
					toolbarGrid.setAdapter(MenuHandler.getMenuAdapter(my, menu_toolbar_name_array, menu_toolbar_image_array));// 设置菜单Adapter
					toolbarGrid.setOnItemClickListener(this);
					break;
				case 4:
					break;
				}
			}
		});
	}
		
	public long getItemId(int position) {
		return 0;
	}

	AlertDialog dialog;

	public View getView(int position, View convertView, ViewGroup parent) 
	{
			return convertView;
	}

	@Override
	protected void onDestroy() {
		WindowManager wm = (WindowManager) getApplicationContext().getSystemService(WINDOW_SERVICE);
		// wm.removeView(tvSystemMessage);
		super.onDestroy();
	}
}