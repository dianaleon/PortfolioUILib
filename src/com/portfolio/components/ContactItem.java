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

public class ContactItem extends LinearLayout {

	private TextView textView;
	private Typeface tf;
	private String direccionCiudad;
	public ContactItem(Context context) {
		super(context);
		tf = Typeface.createFromAsset(getContext().getAssets(),
				"fonts/Raleway-Regular.ttf");
		((Activity)context).getLayoutInflater().inflate(R.layout.contact_item, this);
		init();
	}

	public ContactItem(Context context, AttributeSet attrs) {
		
		super(context, attrs);
	}

	private void init() {
		direccionCiudad = "";
		textView = (TextView) findViewById(R.id.contentText);
		textView.setHeight(30);
	}
	
	public void fill(String content, String textColor, String startColor, String endColor, String orientation) {
	 
		textView.setTypeface(tf);
		textView.setText(content);
		
		UIUtils.setTextColor(textView, textColor);
		UIUtils.setGradient(textView, startColor, endColor, orientation);
	}

	public void fill(final String content, String textColor,
			String startColor, String endColor,
			String orientation, final String type,
			 final String direccionCiudad,
			 final String nombreCiudad,
			 final String cpCiudad) {
		fill(content, textColor, startColor, endColor, orientation);
		textView.setHeight(40);
		textView.setTypeface(tf);
		 
		textView.setOnClickListener(new OnClickListener() {
				
			@Override
			public void onClick(View v) {
				if (type.equalsIgnoreCase(IContactPage.movil) || type.equalsIgnoreCase(IContactPage.telefono)) {
			        String number = "tel:" + content.trim();
				    //Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(number));
				    Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(number));
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
				if (type.equalsIgnoreCase(IContactPage.address)) {
					 
					Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + direccionCiudad +"+" + nombreCiudad + "+" + cpCiudad);
					Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
					mapIntent.setPackage("com.google.android.apps.maps");
					v.getContext().startActivity(mapIntent);
				}
				
			}
		});
	}
}