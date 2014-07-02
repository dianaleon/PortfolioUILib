package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
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
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.utils.UIUtils;

public class PhotoGridActivity extends Activity {

	private Button buttonMenu;
	ViewFlipper flipper;
	String title = null;// title
	String content = null;// texto
	ImageView imgView = null;// content_img

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		// la vista de la home. Layout:photo_grid (json pos 4)
		setContentView(R.layout.activity_photo_grid_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");

		// levanto la pagina de esa posicion
		// la interfaz que se llama text, que tiene imagen, titulo y texto
		IPhotosGridPage page = (IPhotosGridPage) PortfolioModel.getInstance(
				this).getPageInfo(position);

		// caragr info
		ITheme iTheme = PortfolioModel.getInstance(this).getTheme();
		String url = iTheme.getUrlImages();
		final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		// levantar info para el layout
		// imagen + titulo + texto
		List<IPageObject> objetos = page.getObjects();
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_content);
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			switch (object.getType()) {

			case IPageObject.type_image:
				final IImageObject img = (IImageObject) object;
				title = page.getTitle();//img.getTitle();
				content = img.getContent();
				UIUtils.setGradient(linearLayout, img.getStartColorBackground(), img.getEndColorBackground(), String.valueOf(img.getGradientOrientatio()));
				PortfolioModel.getInstance(this).getMedia(new IMediaListener() {
					@Override
					public void onImageReady(Bitmap bitmap) {
						imageView.setImageBitmap(bitmap);
					}
				}, img.getContent_img());
				break;
			}
		}
		
		//MENU
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		IMenu menu = portfolioModel.getPorfolioMenu();
		// Find views
		TextView textViewTittle = (TextView) findViewById(R.id.tittle_app);
		TextView textViewSubTittle = (TextView) findViewById(R.id.sub_tittle_app);
		// Set title and subtitle from json
		textViewTittle.setText(menu.getTitle());
		textViewSubTittle.setText(menu.getSubtitle());

		// cargar el layout con el contenido del json
		TextView tittleView = (TextView) findViewById(R.id.tittle);
		tittleView.setText(title);

		TextView textView = (TextView) findViewById(R.id.text_page_item);
		textView.setText(content);

		// MENU
		final menu menuLayout = (menu) findViewById(R.id.layout_menu);
		menuLayout.init();
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		UIUtils.setGradient(flipper, page.getType().getBackground().getStartColor(), page.getType().getBackground().getEndColor(), String.valueOf(page.getType().getBackground().getAngle()));

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