package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.portfolio.R;
import com.portfolio.components.PhotoTextGridListItem;
import com.portfolio.components.menu;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.ICatalogoPage;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;
import com.portfolio.utils.UIUtils;

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
	
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		IMenu menu = portfolioModel.getPorfolioMenu();
		menu.getBackground();

		// Header fuentes
		TextView textViewTittle = (TextView) findViewById(R.id.tittle_app);
		TextView textViewSubTittle = (TextView) findViewById(R.id.sub_tittle_app);
		textViewTittle.setText(menu.getTitle());
		textViewSubTittle.setText(menu.getSubtitle());
		// FUENTES
		// Cargar el titulo y el subtitulo
		Typeface font1 = Typeface.createFromAsset(getAssets(),
				"fonts/CopperplateGothicStd 31AB.otf");
		TextView customTittle = (TextView) findViewById(R.id.tittle_app);
		customTittle.setTypeface(font1);
		TextView customSubtittle = (TextView) findViewById(R.id.sub_tittle_app);
		customSubtittle.setTypeface(font1);

		final ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper);

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
			}
		}

		// MENU
		final menu menuLayout = (menu) findViewById(R.id.layout_menu);
		menuLayout.init();
		Button buttonMenu = (Button) findViewById(R.id.buttonMenu);
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
}
