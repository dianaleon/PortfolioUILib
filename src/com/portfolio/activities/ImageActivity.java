package com.portfolio.activities;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
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
import com.portfolio.model.interfaces.IPhotosGridPage;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.utils.UIUtils;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ImageActivity extends Activity {

	private Button buttonMenu;
	ImageView imgView;
	ViewFlipper flipper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// la vista de la home. Layout:image (json pos 3)
		setContentView(R.layout.activity_image_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");
		// levanto la pagina de esa posicion
		// la interfaz que se llama redes sociales,....
		IPhotosGridPage imagePage = (IPhotosGridPage) PortfolioModel
				.getInstance(this).getPageInfo(position);

		// cargar info
		List<IPageObject> objetos = imagePage.getObjects();

		// Setear el titulo en la pagina
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		IMenu menu = portfolioModel.getPorfolioMenu();
		menu.getBackground();
		// Find views
		TextView textViewTittle = (TextView) findViewById(R.id.tittle_app);
		TextView textViewSubTittle = (TextView) findViewById(R.id.sub_tittle_app);
		// Set title and subtitle from json
		textViewTittle.setText(menu.getTitle());
		textViewSubTittle.setText(menu.getSubtitle());

		// gradiente header
		LinearLayout bgHeader = (LinearLayout) findViewById(R.id.layout_header);
		String colorStart = menu.getBackground().getStartColor();
		String colorEnd = menu.getBackground().getEndColor();
		int cStart = Color.parseColor(colorStart);
		int cEnd = Color.parseColor(colorEnd);
		GradientDrawable gd = new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM, new int[] { cStart,
						cEnd });
		bgHeader.setBackgroundDrawable(gd);

		// Image to set as the home page
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_content);
		UIUtils.setGradient(linearLayout, imagePage.getType().getBackground()
				.getStartColor(), imagePage.getType().getBackground()
				.getEndColor(),
				String.valueOf(imagePage.getType().getBackground().getAngle()));

		final ImageView imageView = (ImageView) linearLayout
				.findViewById(R.id.imageView1);
		// Fill Content
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			switch (object.getType()) {

			case IPageObject.type_image:
				final IImageObject img = (IImageObject) object;
				PortfolioModel.getInstance(this).getMedia(new IMediaListener() {

					@Override
					public void onImageReady(Bitmap bitmap) {
						imageView.setImageBitmap(bitmap);
					}

				}, img.getContent_img());
				break;
			}
		}

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