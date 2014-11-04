package com.portfolio.activities;

import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.portfolio.R;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IClient;
import com.portfolio.model.interfaces.IGeneralInfo;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.util.UIUtils;

public class HomeActivity2 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// la vista de la home. Layout:image (json pos 3)
		setContentView(R.layout.activity_home_layout2);
		

		// cargar info
		ITheme iTheme = PortfolioModel.getInstance(this).getTheme();
		IMenu iMenu = PortfolioModel.getInstance(this).getPorfolioMenu();
		IGeneralInfo iInfo = PortfolioModel.getInstance(this).getGeneralInformation();
		
		// cargar el layout con el contenido del json
		// Image to set as the home page
//		final RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.layout_content);
//		TextView textHome = (TextView) linearLayout
//				.findViewById(R.id.homeText);
	
//		textHome.setText(iInfo.getAppName());
//		UIUtils.setTextColor(textHome,iMenu.getText_color());
		
//		final ImageView imageView = (ImageView) linearLayout
//				.findViewById(R.id.imageView1);
		// Fill Content
//		PortfolioModel.getInstance(this).getMedia(new IMediaListener() {
//			@Override
//			public void onImageReady(Bitmap bitmap) {
//				Drawable d = new BitmapDrawable(getResources(),bitmap);
//				linearLayout.setBackgroundDrawable(d);
//			}
//		}, iTheme.getHomeImage());

	//	UIUtils.setHeader(this);

		// MENU
		UIUtils.setMenu(this);
	}

	@Override
	public void onBackPressed() {
		android.os.Process.killProcess(android.os.Process.myPid());
	}

}