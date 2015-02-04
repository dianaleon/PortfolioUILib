package com.portfolio.activities;

import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.portfolio.R;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.IPhotosGridPage;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.util.UIUtils;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class ImageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		UIUtils.setAppMetrics(getResources().getDisplayMetrics());

		// la vista de la home. Layout:image (json pos 3)
		setContentView(R.layout.activity_image_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");
		// levanto la pagina de esa posicion
		// la interfaz que se llama redes sociales,....
		IPhotosGridPage imagePage = (IPhotosGridPage) PortfolioModel
				.getInstance(this).getPageInfo(position);
		
		UIUtils.setHeader(this);

		// cargar info
		List<IPageObject> objetos = imagePage.getObjects();

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

		// MENU
		UIUtils.setMenu(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

}