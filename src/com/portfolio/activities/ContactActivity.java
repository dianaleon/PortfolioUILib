package com.portfolio.activities;

import java.util.List;

import android.R.color;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.portfolio.R;
import com.portfolio.components.ContactItem;
import com.portfolio.components.ContactItem2;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IContactPage;
import com.portfolio.model.interfaces.component.IContactObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.util.UIUtils;

@SuppressLint("ResourceAsColor")
public class ContactActivity extends BaseActivity {
	private String direccionCiudad;
	private String nombreCiudad;
	private String cpCiudad;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacto_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");
		direccionCiudad = "";
		nombreCiudad = "";
		cpCiudad = "";
		loadHeader(page);
		
		loadFooter();

		// levanto la pagina de esa posicion
		// la interfaz que se llama contact, que tiene una lista de url + nombre
		// + etc
		IContactPage contactPage = (IContactPage) PortfolioModel.getInstance(
				this).getPageInfo(position);

		//UIUtils.setHeader(this);

		// cargar el layout
		final LinearLayout fullLayout = (LinearLayout) findViewById(R.id.layout_header_body_container);
		final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_content);

		List<IPageObject> objetos = contactPage.getObjects();
		
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			String content = object.getContent();
			IContactObject contact = (IContactObject) object;
			String type = contact.getSubtype();
			if (type.equalsIgnoreCase(IContactPage.address))  {
				direccionCiudad = content;
			}
			if (type.equalsIgnoreCase(IContactPage.ciudad))  {
				nombreCiudad = content;
			}
			if (type.equalsIgnoreCase(IContactPage.cp))  {
				cpCiudad = content;
			}
		}
		
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			String content = object.getContent();
			switch (object.getType()) {
			case IPageObject.type_contact:
				IContactObject contact = (IContactObject) object;
				String type = contact.getSubtype();
				if (type != null) {
					ContactItem contactItem = new ContactItem(this);
					contactItem.fill(content, contact.getTextColor(),
								contact.getStartColorBackground(),
								contact.getEndColorBackground(),
								contact.getGradientOrientatio(), type, direccionCiudad,nombreCiudad,cpCiudad);
					linearLayout.addView(contactItem);
				}
			}
		}
		
		
		 //UIUtils.setGradient(linearLayout, contactPage.getType().getBackground());
		    
		  if ((theme.getHomeImageClear() != null) && (!theme.getHomeImage().equalsIgnoreCase("")))
		
			PortfolioModel.getInstance(this).getMedia(new IMediaListener() {
				@Override
				public void onImageReady(Bitmap bitmap) {
					Drawable drawable = new BitmapDrawable(getResources(), bitmap);
					//linear.setBackgroundColor(Color.TRANSPARENT);
					fullLayout.setBackgroundDrawable(drawable);
				}

			}, theme.getHomeImageClear());

		// MENU
		UIUtils.setMenuApp2(this);
	}
	
	@Override
	public void onContentVisible() {
		headerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
}
