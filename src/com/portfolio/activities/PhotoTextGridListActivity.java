package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
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
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.IPhotoTxtGridListPage;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;
import com.portfolio.utils.UIUtils;

public class PhotoTextGridListActivity extends Activity {

	private Button buttonMenu;
	ViewFlipper flipper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_photo_text_grid_list_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");

		// levanto la pagina de esa posicion
		// la interfaz que se llama text, que tiene imagen, titulo y texto
		IPhotoTxtGridListPage listPage = (IPhotoTxtGridListPage) PortfolioModel
				.getInstance(this).getPageInfo(position);

		Typeface font1 = Typeface.createFromAsset(getAssets(),
				"fonts/CopperplateGothicStd 31BC.otf");
		TextView customTittle = (TextView) findViewById(R.id.tittle_app);
		customTittle.setTypeface(font1);
		customTittle.setTextSize(22);
		// customTittlesetText(tittleApp);

		Typeface font2 = Typeface.createFromAsset(getAssets(),
				"fonts/CopperplateGothicStd 31BC.otf");
		TextView customSubtittle = (TextView) findViewById(R.id.sub_tittle_app);
		customSubtittle.setTypeface(font1);
		customSubtittle.setTextSize(14);
		customSubtittle.setTextScaleX(1);

		// Setear el titulo en la pagina
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		IMenu menu = portfolioModel.getPorfolioMenu();
		menu.getTitle();
		menu.getBackground();
		TextView textViewTittle = (TextView) findViewById(R.id.tittle_app);
		TextView textViewSubTittle = (TextView) findViewById(R.id.sub_tittle_app);
		textViewTittle.setText(menu.getTitle());
		textViewSubTittle.setText(menu.getSubtitle());

		// caragr info
		ITheme iTheme = PortfolioModel.getInstance(this).getTheme();
		String url = iTheme.getUrlImages();
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
			}
		}

		// MENU
		final menu menuLayout = (menu) findViewById(R.id.layout_menu);
		menuLayout.init();
		flipper = (ViewFlipper) findViewById(R.id.flipper);
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
