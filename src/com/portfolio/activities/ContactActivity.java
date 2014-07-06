package com.portfolio.activities;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.portfolio.R;
import com.portfolio.components.ContactItem;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IContactPage;
import com.portfolio.model.interfaces.component.IContactObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.util.UIUtils;

@SuppressLint("ResourceAsColor")
public class ContactActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacto_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");

		// levanto la pagina de esa posicion
		// la interfaz que se llama contact, que tiene una lista de url + nombre
		// + etc
		IContactPage contactPage = (IContactPage) PortfolioModel.getInstance(
				this).getPageInfo(position);

		UIUtils.setHeader(this);

		// cargar el layout
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_content);
		UIUtils.setGradient(linearLayout, contactPage.getType().getBackground().getStartColor(), contactPage.getType().getBackground().getEndColor(), String.valueOf(contactPage.getType().getBackground().getAngle()));

		List<IPageObject> objetos = contactPage.getObjects();
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			String content = object.getContent();
			switch (object.getType()) {
			case IPageObject.type_contact:
				IContactObject contact = (IContactObject) object;
				String type = contact.getSubtype();
				if (type != null) {
					ContactItem contactItem = new ContactItem(this);
					if (type.equalsIgnoreCase(IContactPage.movil) || type.equalsIgnoreCase(IContactPage.telefono)) {
						contactItem.fill(content, contact.getTextColor(),
								contact.getStartColorBackground(),
								contact.getEndColorBackground(),
								contact.getGradientOrientatio(), true);
					} else {
						contactItem.fill(content, contact.getTextColor(),
								contact.getStartColorBackground(),
								contact.getEndColorBackground(),
								contact.getGradientOrientatio());						
					}
					linearLayout.addView(contactItem);
				}
			}
		}

		// MENU
		UIUtils.setMenu(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
}
