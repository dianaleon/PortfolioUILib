package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.portfolio.R;
import com.portfolio.components.PhotoTextGridListItem;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.ICatalogoPage;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;
import com.portfolio.util.UIUtils;

public class CatalogoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// la vista de la pagina que es una lista de imagenes y textos.
		// Layout:catalgo (json)
		setContentView(R.layout.activity_catalogo_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");

		// levanto la pagina de esa posicion
		// la interfaz que se llama contact, que tiene una lista de url + nombre
		// + etc
		ICatalogoPage catalogoPage = (ICatalogoPage) PortfolioModel
				.getInstance(this).getPageInfo(position);

		// cargar info
		UIUtils.setHeader(this);
	
		// cargar el layout
		List<IPageObject> objetos = catalogoPage.getObjects();
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.list_of_rows);
		ScrollView scrollView = (ScrollView) findViewById(R.id.layout_content);
		UIUtils.setGradient(
				scrollView,
				catalogoPage.getType().getBackground().getStartColor(),
				catalogoPage.getType().getBackground().getEndColor(),
				String.valueOf(catalogoPage.getType().getBackground()
						.getAngle()));
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			switch (object.getType()) {
				case IPageObject.type_text:
					ITextObject textObject = (ITextObject) object;
					PhotoTextGridListItem item = new PhotoTextGridListItem(this);
					item.fill(textObject.getContent_img(), textObject.getTitle(),
							textObject.getContent(), textObject.getTextColor(),
							textObject.getStartColorBackground(),
							textObject.getEndColorBackground(),
							textObject.getGradientOrientatio());
					linearLayout.addView(item);
					break;
				case IPageObject.type_image:
					IImageObject imageObject = (IImageObject) object;
					PhotoTextGridListItem itemImage = new PhotoTextGridListItem(this);
					itemImage.fill(imageObject.getContent_img(), imageObject.getTitle(),
							imageObject.getContent(), imageObject.getTextColor(),
							imageObject.getStartColorBackground(),
							imageObject.getEndColorBackground(),
							imageObject.getGradientOrientatio());
					linearLayout.addView(itemImage);
					break;
			}
		}

		// MENU
		UIUtils.setMenu(this);
	}
}