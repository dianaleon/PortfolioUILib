package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.portfolio.R;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.ITextPage;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;
import com.portfolio.util.UIUtils;

public class TextActivity extends Activity {

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

		UIUtils.setHeader(this);

		// caragr info
		// ImageView imgView = (ImageView) findViewById(R.id.imageView1);

		// Cargar fuentes titulo pagina //texto
		// Typeface font4 = Typeface.createFromAsset(getAssets(),
		// "fonts/CopperplateGothicStd 31AB.otf");
		TextView textViewTextPage = (TextView) findViewById(R.id.text_page_item);
		// textViewTittlePage.setTypeface(font4);

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
//				textViewTittlePage.setText(text.getTitle());
				textViewTextPage.setText(text.getDescription());
				break;

			case IPageObject.type_image:
				IImageObject img = (IImageObject) object;
				// titulo
//				textViewTittlePage.setText(img.getTitle());
				// texto
				textViewTextPage.setText(img.getDescription());
				break;
			}
		}

		// cargar el layout

		// imagen
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);

		TextView textView = (TextView) findViewById(R.id.text_page_item);
		// textView.setText(content);

		// String text = bundle.getString("text");
		// TextView textView = (TextView) findViewById(R.id.text);
		// textView.setText(text);

		// MENU
		UIUtils.setMenu(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
}