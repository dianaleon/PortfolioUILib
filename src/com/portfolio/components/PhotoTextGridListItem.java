package com.portfolio.components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.portfolio.R;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.utils.UIUtils;

public class PhotoTextGridListItem extends TableRow {

	private ImageView imageView;
	private TextView titleView;
	private TextView contentView;

	public PhotoTextGridListItem(Context context) {
		super(context);
		((Activity)context).getLayoutInflater().inflate(R.layout.photo_text_grid_list_item, this);
		init();
	}

	public PhotoTextGridListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/CopperGothicStd29AB.otf");
		imageView = (ImageView) findViewById(R.id.imageView1);
		titleView = (TextView) findViewById(R.id.tittle_item_list);
		contentView = (TextView) findViewById(R.id.text_page_item);
	}
	
	public void fill(String contentImage, String title, String content, String textColor, String startColor, String endColor, String orientation) {
		titleView.setText(title);
		contentView.setText(content);
		UIUtils.setTextColor(contentView, textColor);
		UIUtils.setGradient(this, startColor, endColor, orientation);

		PortfolioModel.getInstance(getContext()).getMedia(new IMediaListener() {
			
			@Override
			public void onImageReady(Bitmap bitmap) {
				imageView.setImageBitmap(bitmap);
			}
			
		}, contentImage);

	}
}