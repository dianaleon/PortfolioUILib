package com.portfolio.components;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.portfolio.R;
import com.portfolio.utils.UIUtils;

public class ContactItem extends LinearLayout {

	private TextView textView;
	
	public ContactItem(Context context) {
		super(context);
		((Activity)context).getLayoutInflater().inflate(R.layout.contact_item, this);
		init();
	}

	public ContactItem(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/CopperGothicStd29AB.otf");
		textView = (TextView) findViewById(R.id.contentText);
	}
	
	public void fill(String content, String textColor, String startColor, String endColor, String orientation) {
		textView.setText(content);
		UIUtils.setTextColor(textView, textColor);
		UIUtils.setGradient(textView, startColor, endColor, orientation);
	}

	public void fill(final String content, String textColor,
			String startColor, String endColor,
			String orientation, boolean phone) {
		fill(content, textColor, startColor, endColor, orientation);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		        String number = "tel:" + content.trim();
			    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
		        v.getContext().startActivity(callIntent);
			}
		});
	}
}