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
import com.portfolio.model.interfaces.IContactPage;
import com.portfolio.model.interfaces.component.IContactObject;
import com.portfolio.util.UIUtils;

public class ContactItem2 extends LinearLayout {

	private TextView textView;
	
	public ContactItem2(Context context) {
		super(context);
		((Activity)context).getLayoutInflater().inflate(R.layout.contact_item, this);
		init();
	}

	public ContactItem2(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	private void init() {
		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/OpenSans-Bold.ttf");
		textView = (TextView) findViewById(R.id.contentText);
	}
	
	public void fill(String content, String textColor, String startColor, String endColor, String orientation) {
		textView.setText(content);
	    LayoutParams params = (LayoutParams) textView.getLayoutParams();
	    params.topMargin = 1;
	    params.bottomMargin = 1;
		textView.setLayoutParams(params);
		textView.setHeight(30);
		UIUtils.setTextColor(textView, textColor);
		UIUtils.setGradient(textView, startColor, endColor, orientation);
	}

	public void fill(final String content, String textColor,
			String startColor, String endColor,
			String orientation, final String type) {
		fill(content, textColor, startColor, endColor, orientation);
		textView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (type.equalsIgnoreCase(IContactPage.movil) || type.equalsIgnoreCase(IContactPage.telefono)) {
			        String number = "tel:" + content.trim();
				    Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
			        v.getContext().startActivity(callIntent);
				}
				if (type.equalsIgnoreCase(IContactPage.email)) {
					Intent testIntent = new Intent(Intent.ACTION_VIEW);  
					Uri data = Uri.parse("mailto:?subject=" + "Contacto" + "&to=" + content);  
					testIntent.setData(data);  
					v.getContext().startActivity(testIntent);

				}
				if (type.equalsIgnoreCase(IContactPage.web)) {
					Uri uri = Uri.parse("http:/" + content);
					Intent intent = new Intent(Intent.ACTION_VIEW,
							uri);
					v.getContext().startActivity(intent);
				}
			}
		});
	}
}