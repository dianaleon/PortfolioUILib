package com.portfolio.activities;

import android.app.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.portfolio.R;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.util.UIUtils;

public class HomeActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// la vista de la home. Layout:image (json pos 3)
		setContentView(R.layout.activity_home_layout);

		// cargar info
		ITheme iTheme = PortfolioModel.getInstance(this).getTheme();
		
		// cargar el layout con el contenido del json
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

		UIUtils.setHeader(this);

		// MENU
		UIUtils.setMenu(this);
	}

	@Override
	public void onBackPressed() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}