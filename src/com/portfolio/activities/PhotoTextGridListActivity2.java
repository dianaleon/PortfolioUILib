package com.portfolio.activities;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.portfolio.R;
import com.portfolio.components.PhotoTextGridListItem3;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IPhotoTxtGridListPage;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;
import com.portfolio.util.UIUtils;

public class PhotoTextGridListActivity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_photo_text_grid_list_layout2);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");
		
		// levanto la pagina de esa posicion
		// la interfaz que se llama text, que tiene imagen, titulo y texto
		IPhotoTxtGridListPage listPage = (IPhotoTxtGridListPage) PortfolioModel
				.getInstance(this).getPageInfo(position);

	
		UIUtils.setHeader(this);
		// cargar el layout
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.list_of_rows);
		
		ScrollView scrollView = (ScrollView) findViewById(R.id.layout_content);
		UIUtils.setGradient(scrollView, listPage.getType().getBackground().getStartColor(), listPage.getType().getBackground().getEndColor(), String.valueOf(listPage.getType().getBackground().getAngle()));
		//UIUtils.setGradient(scrollView,"#000000", "#000000", String.valueOf(listPage.getType().getBackground().getAngle()));

		List<IPageObject> objetos = listPage.getObjects();
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			switch (object.getType()) {
				case IPageObject.type_text:
					ITextObject textObject = (ITextObject) object;
					PhotoTextGridListItem3 item = new PhotoTextGridListItem3(this);
					item.fill(textObject.getContent_img(), textObject.getTitle(),
							textObject.getContent(), textObject.getTextColor(), textObject.getStartColorBackground(), textObject.getEndColorBackground(), textObject.getGradientOrientatio());
					LayoutParams params = (LayoutParams) linearLayout.getLayoutParams();
					params.topMargin =1;
					params.bottomMargin =1;
					linearLayout.addView(item);
					break;
				case IPageObject.type_image:
					IImageObject imageObject = (IImageObject) object;
					PhotoTextGridListItem3 itemImage = new PhotoTextGridListItem3(this);
					itemImage.fill(imageObject.getContent_img(), imageObject.getTitle(),
							imageObject.getContent(), imageObject.getTextColor(), imageObject.getStartColorBackground(), imageObject.getEndColorBackground(), imageObject.getGradientOrientatio());
					//linearLayout.addView(itemImage);
					LayoutParams params1 = (LayoutParams) linearLayout.getLayoutParams();
					params1.topMargin =1;
					params1.bottomMargin =1;
					linearLayout.addView(itemImage, params1);
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
