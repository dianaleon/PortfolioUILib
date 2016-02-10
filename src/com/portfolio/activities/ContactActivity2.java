package com.portfolio.activities;

import java.util.List;

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
public class ContactActivity2 extends Activity {
	private String direccionCiudad;
	private String nombreCiudad;
	private String cpCiudad;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacto_layout2);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");
		UIUtils.setHeader(this);
		direccionCiudad = "";
		nombreCiudad = "";
		cpCiudad = "";
		// levanto la pagina de esa posicion
		// la interfaz que se llama contact, que tiene una lista de url + nombre
		// + etc
		IContactPage contactPage = (IContactPage) PortfolioModel.getInstance(
				this).getPageInfo(position);

		//UIUtils.setHeader(this);

		// cargar el layout
		final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_content);
		final LinearLayout fondo = (LinearLayout) findViewById(R.id.body);
		fondo.setBackgroundColor(Color.parseColor("#23250f"));
		//ACA pone el borde que es marron y tiene que ser dorado. COLOR BORDE!
		UIUtils.setGradient(linearLayout, 
				contactPage.getType().getBackground().getStartColor(), 
				contactPage.getType().getBackground().getEndColor(), 
				String.valueOf(contactPage.getType().getBackground().getAngle()));

		 /*UIUtils.setGradient(linearLayout,
				//contactPage.getType().getBackground().getStartColor(), viene del bg de la pagina!
				"#AD9C67", 
				"#A49367", 
				String.valueOf(contactPage.getType().getBackground().getAngle()));
				 */
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
					ContactItem2 contactItem = new ContactItem2(this);
					
					contactItem.fill(content, 
							contact.getTextColor(),
							//aca pone el color de bg del item con el color de bg del data
							contact.getStartColorBackground(),
							contact.getEndColorBackground(),
								contact.getGradientOrientatio(), type, direccionCiudad,nombreCiudad,cpCiudad);
					linearLayout.addView(contactItem);
				}
			}
		}
		
		
		 UIUtils.setGradient(linearLayout, contactPage.getType().getBackground());
		    
			
		// MENU
		UIUtils.setMenu(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
}
