package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.portfolio.R;
import com.portfolio.components.PhotoTextGridListItem;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IPhotoTextPage;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;
import com.portfolio.util.UIUtils;

public class PhotoTextActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_photo_text_grid_list_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");

		// levanto la pagina de esa posicion
		// la interfaz que se llama text, que tiene imagen, titulo y texto
		IPhotoTextPage listPage = (IPhotoTextPage) PortfolioModel
				.getInstance(this).getPageInfo(position);

		// Setear el titulo en la pagina
		UIUtils.setHeader(this);

		// cargar el layout
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.list_of_rows);
		
		ScrollView scrollView = (ScrollView) findViewById(R.id.layout_content);
		UIUtils.setGradient(scrollView, listPage.getType().getBackground().getStartColor(), listPage.getType().getBackground().getEndColor(), String.valueOf(listPage.getType().getBackground().getAngle()));

		List<IPageObject> objetos = listPage.getObjects();
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			switch (object.getType()) {
				case IPageObject.type_text:
					ITextObject textObject = (ITextObject) object;
					PhotoTextGridListItem item = new PhotoTextGridListItem(this);
					item.fill(textObject.getContent_img(), textObject.getTitle(),
							textObject.getContent(), textObject.getTextColor(), textObject.getStartColorBackground(), textObject.getEndColorBackground(), textObject.getGradientOrientatio());
					linearLayout.addView(item);
					break;
				case IPageObject.type_image:
					IImageObject imageObject = (IImageObject) object;
					PhotoTextGridListItem itemImage = new PhotoTextGridListItem(this);
					itemImage.fill(imageObject.getContent_img(), imageObject.getTitle(),
							imageObject.getContent(), imageObject.getTextColor(), imageObject.getStartColorBackground(), imageObject.getEndColorBackground(), imageObject.getGradientOrientatio());
					linearLayout.addView(itemImage);
					break;
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
