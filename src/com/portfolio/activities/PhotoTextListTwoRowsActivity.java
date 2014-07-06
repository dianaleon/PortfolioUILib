package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.portfolio.R;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IPhotoGaleryPage;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;
import com.portfolio.util.UIUtils;

public class PhotoTextListTwoRowsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_photo_text_gridlist_2);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");

		// levanto la pagina de esa posicion
		// la interfaz que se llama contact, que tiene una lista de url + nombre
		// + etc
		IPhotoGaleryPage galeryPage = (IPhotoGaleryPage) PortfolioModel
				.getInstance(this).getPageInfo(position);

		// caragr info
		ITheme iTheme = PortfolioModel.getInstance(this).getTheme();
		String url = iTheme.getUrlImages();

		// cargar el layout
		List<IPageObject> objetos = galeryPage.getObjects();

		for (int index = 0; index < objetos.size(); index++) {

			IPageObject object = objetos.get(index);
			String title = object.getTitle();
			String subtitle = object.getSubtitle();
			String content = object.getContent();

			switch (object.getType()) {

			case IPageObject.type_image:
				IImageObject imageItem = (IImageObject) object;

			case IPageObject.type_text:
				ITextObject text = (ITextObject) object;
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