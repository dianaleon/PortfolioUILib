package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.portfolio.R;
import com.portfolio.R.color;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IPhotosGridPage;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.util.UIUtils;

public class PhotoGridActivity extends Activity {

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

		UIUtils.setHeader(this);
	
		// caragr info
		final ImageView imageView = (ImageView) findViewById(R.id.imageView1);
		// levantar info para el layout
		// imagen + titulo + texto
		List<IPageObject> objetos = page.getObjects();
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_content);
		
		String title = null;// title
		String content = null;// texto
		String textColor = null;
		String startColor = null;
		String endColor = null;
		String gradientColor = null;
		
		String bgColor = page.getType().getBackground().getStartColor();
		//DATA
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			switch (object.getType()) {

			case IPageObject.type_image:
				final IImageObject img = (IImageObject) object;
				title = img.getTitle();//img.getTitle();
				content = img.getContent();
				textColor = img.getTextColor();
				startColor = img.getStartColorBackground();
				endColor = img.getEndColorBackground();
				gradientColor = img.getGradientOrientatio();
				
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

		//ACA PONE EL COLOR AL FONDO DEL TIUTLO, que viene del json page data bg
		TextView tittleTextView = (TextView) findViewById(R.id.content);
		//UIUtils.setTextColor(tittleTextView, textColor);
		tittleTextView.setTextColor(Color.parseColor("#231b10"));
		Typeface font1 = Typeface.createFromAsset(this.getAssets(),
				"fonts/OpenSans-Bold.ttf");
		tittleTextView.setTypeface(font1);
		tittleTextView.setAllCaps(true);
		UIUtils.setGradient(tittleTextView, startColor, endColor, gradientColor);
		tittleTextView.setText(title);
		tittleTextView.setTextSize(14);
		
		//ACA PONE EL COLOR AL FONDO DEL TEXTO, que viene del json page type bg
		TextView contentTextView = (TextView) findViewById(R.id.text_page_item);
		UIUtils.setGradient(contentTextView, bgColor, bgColor, gradientColor);
		UIUtils.setTextColor(contentTextView, startColor);
		contentTextView.setTextSize(12);
		contentTextView.setText(content);

		// MENU
		UIUtils.setMenuApp2(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

}