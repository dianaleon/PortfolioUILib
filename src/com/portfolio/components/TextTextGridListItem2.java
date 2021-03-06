package com.portfolio.components;

import com.portfolio.R;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.util.UIUtils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;



public class TextTextGridListItem2 extends TableRow {

	private TextView leftTitle;
	private TextView titleView;
	private TextView contentView;

	public TextTextGridListItem2(Context context) {
		super(context);
		((Activity)context).getLayoutInflater().inflate(R.layout.text_text_grid_list_item, this);
		init();
	}

	public TextTextGridListItem2(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/CopperGothicStd29AB.otf");
		leftTitle = (TextView) findViewById(R.id.tittle_item_list_left);
		titleView = (TextView) findViewById(R.id.tittle_item_list);
		contentView = (TextView) findViewById(R.id.text_page_item);
	}

	public void fill(String left_Title, String title, String content, String textColor, String startColor, String endColor, String orientation) {
		leftTitle.setText(left_Title);
		contentView.setText(content);
		UIUtils.setTextColor(contentView, textColor);
		UIUtils.setGradient(this, startColor, endColor, orientation);


	}
}
