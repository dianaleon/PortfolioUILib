package com.portfolio.activities;

import java.util.List;

import android.app.ActionBar.LayoutParams;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import com.portfolio.util.UIUtils;

import com.portfolio.R;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.INetworkPage;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.util.UIUtils;

public class NetworkActivity extends BaseActivity {

	String addressfb = null;
	String addresstwitter = null;
	String addressgplus = null;
	String addressinstagram = null;
	String addresspinterest = null;
	String addresslinkedin = null;
	final int ICON_SIZE = 100;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_network_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");
		loadHeader(page);
		loadFooter();

		// levanto la pagina de esa posicion
		// la interfaz que se llama redes sociales,....
		INetworkPage netPage = (INetworkPage) PortfolioModel.getInstance(this)
				.getPageInfo(position);

		// caragr info
		List<IPageObject> objetos = netPage.getObjects();

		// UIUtils.setHeader(this);

		final TableLayout tableLayout = (TableLayout) findViewById(R.id.layout_content);
		// UIUtils.setGradient(tableLayout,
		// netPage.getType().getBackground().getStartColor(),
		// netPage.getType().getBackground().getEndColor(),
		// String.valueOf(netPage.getType().getBackground().getAngle()));

		// recorro los objetos del json
		for (int index = 0; index < objetos.size(); index++) {

			IPageObject object = objetos.get(index);
			String content = object.getContent();
			final ImageButton but = new ImageButton(this);
			but.setAdjustViewBounds(true);
			but.setScaleType(ImageView.ScaleType.CENTER_CROP);
			but.setBackgroundColor(Color.TRANSPARENT);

			// Generico
			final String address = content;
			PortfolioModel.getInstance(this).getMedia(new IMediaListener() {
				@Override
				public void onImageReady(Bitmap bitmap) {
					but.setImageBitmap(bitmap);
				}

			}, object.getContent_img());

			// but.setTag(pos);
			but.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Uri uri = Uri.parse(address);
					Intent intent = new Intent(Intent.ACTION_VIEW, uri);
					startActivity(intent);
				}
			});

			// switch (object.getType()) {
			//
			// case IPageObject.type_network:
			// INetworkObject social = (INetworkObject) object;
			// String type = social.getSubtype();
			// if (type != null) {
			// if (type.equalsIgnoreCase(INetworkPage.facebook)) {
			// addressfb = content;
			// but.setImageResource(R.drawable.fb);
			//
			//
			// // but.setTag(pos);
			// but.setOnClickListener(new OnClickListener() {
			// public void onClick(View v) {
			// Uri uri = Uri.parse(addressfb);
			// Intent intent = new Intent(Intent.ACTION_VIEW,
			// uri);
			// startActivity(intent);
			// }
			// });
			// }
			//
			// if (type.equalsIgnoreCase(INetworkPage.twitter)) {
			//
			// addresstwitter = content;
			// but.setImageResource(R.drawable.twitter);
			//
			// // but.setTag(pos);
			// but.setOnClickListener(new OnClickListener() {
			// public void onClick(View v) {
			// Uri uri = Uri.parse(addresstwitter);
			// Intent intent = new Intent(Intent.ACTION_VIEW,
			// uri);
			// startActivity(intent);
			// }
			// });
			// }
			//
			// if (type.equalsIgnoreCase(INetworkPage.gplus)) {
			//
			// addressgplus = content;
			// but.setImageResource(R.drawable.gplus);
			//
			// // but.setTag(pos);
			// but.setOnClickListener(new OnClickListener() {
			// public void onClick(View v) {
			// Uri uri = Uri.parse(addressgplus);
			// Intent intent = new Intent(Intent.ACTION_VIEW,
			// uri);
			// startActivity(intent);
			// }
			// });
			// }
			// if (type.equalsIgnoreCase(INetworkPage.instagram)) {
			//
			// addressinstagram = content;
			// but.setImageResource(R.drawable.instagram);
			// // but.setTag(pos);
			// but.setOnClickListener(new OnClickListener() {
			// public void onClick(View v) {
			// Uri uri = Uri.parse(addressinstagram);
			// Intent intent = new Intent(Intent.ACTION_VIEW,
			// uri);
			// startActivity(intent);
			// }
			// });
			// }
			// if (type.equalsIgnoreCase(INetworkPage.pinterest)) {
			//
			// addresspinterest = content;
			// but.setImageResource(R.drawable.pinterest);
			// // but.setTag(pos);
			// but.setOnClickListener(new OnClickListener() {
			// public void onClick(View v) {
			// Uri uri = Uri.parse(addresspinterest);
			// Intent intent = new Intent(Intent.ACTION_VIEW,
			// uri);
			// startActivity(intent);
			// }
			// });
			// }
			// if (type.equalsIgnoreCase(INetworkPage.linkedin)) {
			//
			// addresslinkedin = content;
			// but.setImageResource(R.drawable.lin);
			// // but.setTag(pos);
			// but.setOnClickListener(new OnClickListener() {
			// public void onClick(View v) {
			// Uri uri = Uri.parse(addresslinkedin);
			// Intent intent = new Intent(Intent.ACTION_VIEW,
			// uri);
			// startActivity(intent);
			// }
			// });
			// }
			// }
			//
			// }
			
			int tableRowParams = UIUtils.getDimension(ICON_SIZE);
			
			TableRow.LayoutParams params = new TableRow.LayoutParams(tableRowParams,tableRowParams);
			
            int margin_20 = UIUtils.getDimension(20);
            int margin_10 = UIUtils.getDimension(10);
			
			if((index%2)==0){
				params.leftMargin = Math.round(margin_20);
				params.rightMargin = Math.round(margin_10);
			}
			else
			{
				params.leftMargin = Math.round(margin_10);
				params.rightMargin = Math.round(margin_20);
			}
					

			if (index < 2) {
				TableRow board = (TableRow) findViewById(R.id.tableRow1);
				// board.addView(but, new TableRow.LayoutParams(
				// TableRow.LayoutParams.WRAP_CONTENT,
				// TableRow.LayoutParams.WRAP_CONTENT, 1f));
				board.addView(but, params);
			} else {
				if (index >= 2 && index < 4) {
					TableRow board2 = (TableRow) findViewById(R.id.tableRow2);
					// board2.addView(but, new TableRow.LayoutParams(
					// TableRow.LayoutParams.WRAP_CONTENT,
					// TableRow.LayoutParams.WRAP_CONTENT, 1f));
					board2.addView(but, params);
				} else {
					TableRow board3 = (TableRow) findViewById(R.id.tableRow3);
					// board3.addView(but, new TableRow.LayoutParams(
					// TableRow.LayoutParams.WRAP_CONTENT,
					// TableRow.LayoutParams.WRAP_CONTENT, 1f));
					board3.addView(but, params);
				}
			}
		}

		// final LinearLayout linear = (LinearLayout)
		// findViewById(R.id.layout_header_body_container);
		UIUtils.setGradient(tableLayout, netPage.getType().getBackground());

		if ((theme.getHomeImage() != null)
				&& (!theme.getHomeImage().equalsIgnoreCase("")))

			PortfolioModel.getInstance(this).getMedia(new IMediaListener() {
				@Override
				public void onImageReady(Bitmap bitmap) {
					Drawable drawable = new BitmapDrawable(getResources(),
							bitmap);
					// linear.setBackgroundColor(Color.TRANSPARENT);
					tableLayout.setBackgroundDrawable(drawable);

				}

			}, theme.getHomeImage());

		// MENU
		UIUtils.setMenu(this);
	}

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
