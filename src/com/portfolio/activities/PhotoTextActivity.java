package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.portfolio.R;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IPhotoTextPage;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.util.UIUtils;

public class PhotoTextActivity extends BaseActivity {

	private TextView biographyText;
	private TextView biographyTitleText;
	private ImageView biographyImage;
	private TextView headerTitle;
	private TextView headerSubtitle;
	private View layoutBody;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_photo_text);
		
		loadHeader(page);
		loadFooter();

		biographyImage = (ImageView) findViewById(R.id.imageView1);
		biographyText = (TextView) findViewById(R.id.content);
		biographyTitleText = (TextView) findViewById(R.id.title);
		headerTitle = (TextView) findViewById(R.id.tittle_app);
		headerSubtitle = (TextView) findViewById(R.id.sub_tittle_app);
		layoutBody = findViewById(R.id.layout_content);

		// levanto la pagina de esa posicion
		// la interfaz que se llama text, que tiene imagen, titulo y texto
		
		
		

		// cargar theme
		//ITheme theme = PortfolioModel.getInstance(this).getTheme();

		UIUtils.setGradient(layoutBody, page.getType().getBackground()
				.getStartColor(), page.getType().getBackground().getEndColor(),
				String.valueOf(page.getType().getBackground().getAngle()));

		// Setear el titulo en la pagina
	//	UIUtils.setHeader(this);

		List<IPageObject> objetos = page.getObjects();
		if (objetos != null && objetos.size() > 0) {
			IPageObject object = objetos.get(0);

			IImageObject imageObject = (IImageObject) object;
			PortfolioModel.getInstance(this).getMedia(new IMediaListener() {
				@Override
				public void onImageReady(Bitmap bitmap) {
					biographyImage.setImageBitmap(bitmap);
				}

			}, imageObject.getContent_img());

			biographyText.setText(imageObject.getContent());
			if (!imageObject.getTitle().equalsIgnoreCase("")) {
				biographyTitleText.setText(imageObject.getTitle());
			} else {
				biographyTitleText.setVisibility(View.GONE);
			}

		}

		// MENU
		UIUtils.setMenu(this);
	}

//	@Override
//	public void loadHeader() {
//		super.loadHeader();
//		headerView.setVisibility(View.GONE);
//	};

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

	@Override
	public void onContentVisible() {
		headerView.setVisibility(View.VISIBLE);	
	}
}
