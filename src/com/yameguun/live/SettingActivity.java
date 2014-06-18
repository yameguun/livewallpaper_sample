package com.yameguun.live;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.yameguun.live.dialog.ColorPickerDialog;

public class SettingActivity extends Activity {

	// カラーピッカーダイアログ
	private ColorPickerDialog mColorPickerDialog;

	private SharedPreferences sharedPref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

		setContentView(R.layout.setting_layout);

		mColorPickerDialog = new ColorPickerDialog(this,
				new ColorPickerDialog.OnColorChangedListener() {
					@Override
					public void colorChanged(int color) {
						Editor editor = sharedPref.edit();
						editor.putInt("bColor", color);
						editor.commit();
					}
				}, Color.BLACK);

		// 背景色の設定
		LinearLayout setcolor = (LinearLayout) findViewById(R.id.setcolor);
		setcolor.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mColorPickerDialog.show(); // カラーピッカーダイアログを表示する
			}
		});

	}

}
