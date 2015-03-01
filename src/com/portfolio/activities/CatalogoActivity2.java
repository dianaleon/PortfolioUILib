package com.portfolio.activities;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;

import com.portfolio.R;
import com.portfolio.components.PhotoTextGridListItem;
import com.portfolio.components.PhotoTextGridListItem2;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.ICatalogoPage;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;
import com.portfolio.util.UIUtils;

public class CatalogoActivity2 extends Activity {

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
		linearLayout.setBackgroundColor(Color.parseColor("#000000"));
		


		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			switch (object.getType()) {
				case IPageObject.type_text:
					ITextObject textObject = (ITextObject) object;
					PhotoTextGridListItem2 item = new PhotoTextGridListItem2(this);
					item.fill(textObject.getContent_img(), textObject.getTitle(),
							textObject.getContent(),"#000000" ,textObject.getTextColor(),
							textObject.getStartColorBackground(),
							textObject.getEndColorBackground(),
							textObject.getGradientOrientatio());
					
					linearLayout.addView(item);
					LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) item.getLayoutParams();
					params.bottomMargin = 1;
					params.topMargin = 1;
					break;
				case IPageObject.type_image:
					IImageObject imageObject = (IImageObject) object;
					PhotoTextGridListItem2 itemImage = new PhotoTextGridListItem2(this);
					itemImage.fill(imageObject.getContent_img(), imageObject.getTitle(),
							imageObject.getContent(),"#000000",imageObject.getTextColor(),
							imageObject.getStartColorBackground(),
							imageObject.getEndColorBackground(),
							imageObject.getGradientOrientatio()); 
//					TableRow.LayoutParams params = (TableRow.LayoutParams) itemImage.getLayoutParams();
//					params.bottomMargin = 2;
//					params.topMargin = 2;
					linearLayout.addView(itemImage);
					break;
			}
		}

		// MENU
		UIUtils.setMenu(this);
	}
}