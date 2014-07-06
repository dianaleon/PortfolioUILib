package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;

import com.portfolio.R;
import com.portfolio.components.menu;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.model.interfaces.IVideoPage;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.IVideoObject;
import com.portfolio.util.UIUtils;

public class VideoActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_video);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");

		// levanto la pagina de esa posicion
		// la interfaz que se llama contact, que tiene una lista de url + nombre
		// + etc
		IVideoPage videoPage = (IVideoPage) PortfolioModel.getInstance(
				this).getPageInfo(position);

		UIUtils.setHeader(this);

		// cargar el layout
		List<IPageObject> objetos = videoPage.getObjects();

		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			String title = object.getTitle();
			String subtitle = object.getSubtitle();
			String content = object.getContent();

			switch (object.getType()) {

			case IPageObject.type_video:
				IVideoObject video = (IVideoObject) object;
				break;
			}
		}

		// MENU
		final menu menuLayout = (menu) findViewById(R.id.layout_menu);
		menuLayout.init();
	}
}