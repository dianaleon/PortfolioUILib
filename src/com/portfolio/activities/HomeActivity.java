package com.portfolio.activities;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.portfolio.R;
import com.portfolio.components.menu;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.utils.UIUtils;

public class HomeActivity extends Activity {

	private Button buttonMenu;
	ImageView imgView;
	ViewFlipper flipper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// la vista de la home. Layout:image (json pos 3)
		setContentView(R.layout.activity_home_layout);

		// cargar info
		ITheme iTheme = PortfolioModel.getInstance(this).getTheme();

		// Setear el titulo en la pagina
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		IMenu menu = portfolioModel.getPorfolioMenu();

		// gradiente header
		LinearLayout bgHeader = (LinearLayout) findViewById(R.id.layout_header);
		String startColor = menu.getBackground().getStartColor();
		String endColor = menu.getBackground().getEndColor();
		UIUtils.setGradient(bgHeader, startColor, endColor, String.valueOf(menu.getBackground().getAngle()));
		
		// cargar el layout con el contenido del json
		TextView tittleView = (TextView) findViewById(R.id.tittle_app);
		TextView subTittleView = (TextView) findViewById(R.id.sub_tittle_app);
		tittleView.setText(menu.getTitle());
		subTittleView.setText(menu.getSubtitle());

		// Image to set as the home page
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_content);
		final ImageView imageView = (ImageView) linearLayout
				.findViewById(R.id.imageView1);
		// Fill Content
		PortfolioModel.getInstance(this).getMedia(new IMediaListener() {
			@Override
			public void onImageReady(Bitmap bitmap) {
				imageView.setImageBitmap(bitmap);
			}
		}, iTheme.getHomeImage());

		// FUENTES
		Typeface font1 = Typeface.createFromAsset(getAssets(),
				"fonts/CopperplateGothicStd 31BC.otf");
		TextView customTittle = (TextView) findViewById(R.id.tittle_app);
		customTittle.setTypeface(font1);
		customTittle.setTextSize(22);

		TextView customSubtittle = (TextView) findViewById(R.id.sub_tittle_app);
		customTittle.setTextSize(20);
		customSubtittle.setTypeface(font1);
		customSubtittle.setTextSize(14);
		customSubtittle.setTextScaleX(1);
		// customTittle.setText(SUBTITULO);

		// MENU
		final menu menuLayout = (menu) findViewById(R.id.layout_menu);
		LinearLayout bgFooter = (LinearLayout) findViewById(R.id.layout_footer);
		String colorStartMenu = menu.getBackground().getStartColor();
		String colorEndMenu = menu.getBackground().getEndColor();
		int cStartMenu = Color.parseColor(colorStartMenu);
		int cEndMenu = Color.parseColor(colorEndMenu);
		GradientDrawable gdMenu = new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
						cStartMenu, cEndMenu });
		bgFooter.setBackgroundDrawable(gdMenu);

		menuLayout.init();
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		UIUtils.setGradient(flipper, iTheme.getBackground().getStartColor(), iTheme.getBackground().getEndColor(), String.valueOf(iTheme.getBackground().getAngle()));
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