package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.portfolio.R;
import com.portfolio.components.menu;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IPhotoGaleryPage;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;

public class PhotoTextListTwoRowsActivity extends Activity {

	private Button buttonMenu;
	ViewFlipper flipper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photo_text_gridlist_2);
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
		flipper = (ViewFlipper) findViewById(R.id.flipper);

		final menu menuLayout = (menu) findViewById(R.id.layout_menu);
		menuLayout.init();

		buttonMenu = (Button) findViewById(R.id.buttonMenu);
		buttonMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				flipper.setInAnimation(inFromRightAnimation());
				flipper.setOutAnimation(outToLeftAnimation());
				flipper.showNext();
			}
		});
	}

	private Animation inFromRightAnimation() {

		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(100);
		inFromRight.setInterpolator(new AccelerateInterpolator());

		return inFromRight;

	}

	private Animation outToLeftAnimation() {

		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(100);
		outtoLeft.setInterpolator(new AccelerateInterpolator());

		return outtoLeft;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

}