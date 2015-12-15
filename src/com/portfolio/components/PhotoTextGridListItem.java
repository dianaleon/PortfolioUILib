package com.portfolio.components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.portfolio.R;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.util.UIUtils;

public class PhotoTextGridListItem extends TableRow {

	private ImageView imageView;
	private TextView titleView;
	private TextView contentView;
	private Typeface tf;
	private Typeface tfTitle;
	public PhotoTextGridListItem(Context context) {
		super(context);
		((Activity)context).getLayoutInflater().inflate(R.layout.photo_text_grid_list_item, this);
		init();
	}

	public PhotoTextGridListItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void init() {
	
		tfTitle = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/Raleway-Bold.ttf");
		tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/Raleway-Regular.ttf");
		imageView = (ImageView) findViewById(R.id.imageView1);
		titleView = (TextView) findViewById(R.id.tittle_item_list);
		contentView = (TextView) findViewById(R.id.text_page_item);
	}
	
	public void fill(String contentImage, String title, String content, String textColor, String startColor, String endColor, String orientation) {
		
		titleView.setText(title);
	    titleView.setTypeface(tfTitle,Typeface.BOLD);
	    UIUtils.setTextColor(titleView, textColor);
	    
	    contentView.setText(content);
	    contentView.setTypeface(tf);
	    contentView.setTextSize(40);
		UIUtils.setTextColor(contentView, textColor);
		UIUtils.setGradient(this, startColor, endColor, orientation);

		PortfolioModel.getInstance(getContext()).getMedia(new IMediaListener() {
			
			@Override
			public void onImageReady(Bitmap bitmap) {
				imageView.setScaleType(ScaleType.CENTER_CROP);
				imageView.setImageBitmap(bitmap);
			}
			
		}, contentImage);

	}
}