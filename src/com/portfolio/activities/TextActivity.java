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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.portfolio.R;
import com.portfolio.components.menu;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.ITextPage;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;

public class TextActivity extends Activity {

	private Button buttonMenu;
	ViewFlipper flipper;
	ImageView imgView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		// la vista que tiene imagen + titulo + texto. Layout:
		setContentView(R.layout.activity_photo_grid_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");

		// levanto la pagina de esa posicion
		// la interfaz que se llama text, que tiene imagen, titulo y texto
		ITextPage textPage = (ITextPage) PortfolioModel.getInstance(this)
				.getPageInfo(position);

		// caragr info
		ITheme iTheme = PortfolioModel.getInstance(this).getTheme();
		String url = iTheme.getUrlImages();
		// ImageView imgView = (ImageView) findViewById(R.id.imageView1);

		// Cargar fuentes
		Typeface font1 = Typeface.createFromAsset(getAssets(),
				"fonts/CopperplateGothicStd 31BC.otf");
		TextView customTittle = (TextView) findViewById(R.id.tittle_app);
		customTittle.setTypeface(font1);
		customTittle.setTextSize(22);
		// customTittle.setText(TITULO);

		Typeface font2 = Typeface.createFromAsset(getAssets(),
				"fonts/CopperplateGothicStd 31BC.otf");
		TextView customSubtittle = (TextView) findViewById(R.id.sub_tittle_app);
		customSubtittle.setTypeface(font1);
		customSubtittle.setTextSize(14);
		customSubtittle.setTextScaleX(1);
		// customTittle.setText(SUBTITULO);

		// Cargar fuentes titulo pagina
		Typeface font3 = Typeface.createFromAsset(getAssets(),
				"fonts/CopperplateGothicStd 31BC.otf");
		TextView textViewTittlePage = (TextView) findViewById(R.id.tittle);
		textViewTittlePage.setTypeface(font3);

		// Cargar fuentes titulo pagina //texto
		// Typeface font4 = Typeface.createFromAsset(getAssets(),
		// "fonts/CopperplateGothicStd 31AB.otf");
		TextView textViewTextPage = (TextView) findViewById(R.id.text_page_item);
		// textViewTittlePage.setTypeface(font4);

		// Setear el titulo en la pagina
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		IMenu menu = portfolioModel.getPorfolioMenu();
		menu.getTitle();
		menu.getBackground();
		TextView textViewTittle = (TextView) findViewById(R.id.tittle_app);
		TextView textViewSubTittle = (TextView) findViewById(R.id.sub_tittle_app);
		textViewTittle.setText(menu.getTitle());
		textViewSubTittle.setText(menu.getSubtitle());

		// imagen + titulo + texto
		List<IPageObject> objetos = textPage.getObjects();

		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			// String title = object.getTitle();
			// String subtitle = object.getSubtitle();
			// String content = object.getContent();
			// String urlFinal = url + object.getContent_img();
			// titulo

			switch (object.getType()) {

			case IPageObject.type_text:
				ITextObject text = (ITextObject) object;
				textViewTittlePage.setText(text.getTitle());
				textViewTextPage.setText(text.getDescription());
				break;

			case IPageObject.type_image:
				IImageObject img = (IImageObject) object;
				// titulo
				textViewTittlePage.setText(img.getTitle());
				// texto
				textViewTextPage.setText(img.getDescription());
				break;
			}
		}

		// cargar el layout

		// imagen
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);

		/* ME TIRA ERROR algo pongo mal aca!!!! */
		/*
		 * PortfolioModel.getMedia(new IPortfolioListener() {
		 * 
		 * 
		 * @Override public void onPortfolioReady() { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * @Override public void onImageReady(Bitmap bitmap) {
		 * imageView.setBitmap(bitmap); }
		 * 
		 * @Override public void errorGetPortfolio() { // TODO Auto-generated
		 * method stub
		 * 
		 * }
		 * 
		 * 
		 * }, urlFinal);
		 */

		TextView textView = (TextView) findViewById(R.id.text_page_item);
		// textView.setText(content);

		TextView tittleView = (TextView) findViewById(R.id.tittle);
		// tittleView.setText(title);

		/*
		 * 
		 * //cargar la imagen String nameImageUrl = bundle.getString("text");
		 * ImageView imgView = (ImageView) findViewById(R.id.imageView1);
		 * 
		 * 
		 * //cargar el texto: public String getContent(); String text =
		 * bundle.getString("text");
		 * 
		 * 
		 * //IPage -> getTitle String tittle = bundle.getString("tittle");
		 */

		// String text = bundle.getString("text");
		// TextView textView = (TextView) findViewById(R.id.text);
		// textView.setText(text);

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