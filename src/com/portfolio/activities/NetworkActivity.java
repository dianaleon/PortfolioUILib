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
import android.widget.LinearLayout;
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
		
		LinearLayout fondo = (LinearLayout) findViewById(R.id.body);
	 
		UIUtils.setGradient(fondo, netPage.getType().getBackground());
		
		
		/*UIUtils.setGradient(tableLayout,
		netPage.getType().getBackground().getStartColor(),
		netPage.getType().getBackground().getEndColor(),
		String.valueOf(netPage.getType().getBackground().getAngle()));*/

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

			fillSocialNetworkTable(objetos.size(),but,index);
			
		}


//			int tableRowParams = UIUtils.getDimension(ICON_SIZE);
//
//			TableRow.LayoutParams params = new TableRow.LayoutParams(tableRowParams,tableRowParams);
//
//			int margin_20 = UIUtils.getDimension(20);
//			int margin_10 = UIUtils.getDimension(10);
//
//			if((index%2)==0){
//				params.leftMargin = Math.round(margin_20);
//				params.rightMargin = Math.round(margin_10);
//			}
//			else
//			{
//				params.leftMargin = Math.round(margin_10);
//				params.rightMargin = Math.round(margin_20);
//			}
//
//
//			if (index < 2) {
//				TableRow board = (TableRow) findViewById(R.id.tableRow1);
//				// board.addView(but, new TableRow.LayoutParams(
//				// TableRow.LayoutParams.WRAP_CONTENT,
//				// TableRow.LayoutParams.WRAP_CONTENT, 1f));
//				board.addView(but, params);
//			} else {
//				if (index >= 2 && index < 4) {
//					TableRow board2 = (TableRow) findViewById(R.id.tableRow2);
//					// board2.addView(but, new TableRow.LayoutParams(
//					// TableRow.LayoutParams.WRAP_CONTENT,
//					// TableRow.LayoutParams.WRAP_CONTENT, 1f));
//					board2.addView(but, params);
//				} else {
//					TableRow board3 = (TableRow) findViewById(R.id.tableRow3);
//					// board3.addView(but, new TableRow.LayoutParams(
//					// TableRow.LayoutParams.WRAP_CONTENT,
//					// TableRow.LayoutParams.WRAP_CONTENT, 1f));
//					board3.addView(but, params);
//				}
//			}
//		}

		//Fondo espacio redes sociales
		//UIUtils.setGradient(tableLayout, netPage.getType().getBackground());

		/*if ((theme.getHomeImage() != null)
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
*/
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


	private void fillSocialNetworkTable(int itemsCount,ImageButton but,int item)
	{
		//Boton de relleno
		ImageButton butFill = new ImageButton(this);
		butFill.setAdjustViewBounds(true);
		butFill.setScaleType(ImageView.ScaleType.CENTER_CROP);
		butFill.setBackgroundColor(Color.TRANSPARENT);
		butFill.setVisibility(View.INVISIBLE);

		//NormalParams
		int tableRowParams = UIUtils.getDimension(ICON_SIZE);		
		TableRow.LayoutParams params = new TableRow.LayoutParams(tableRowParams,tableRowParams);
		//SmallParams
		int tableRowSmallParams = UIUtils.getDimension(75);
		TableRow.LayoutParams paramsSmall = new TableRow.LayoutParams(tableRowSmallParams,tableRowParams);

		switch (itemsCount) {
		case 1:
			TableRow board2 = (TableRow) findViewById(R.id.tableRow2);		
			board2.addView(but, params);
			break;
		case 2:
			TableRow board3 = (TableRow) findViewById(R.id.tableRow2);
			if(item == 0){
				board3.addView(but, params);
				board3.addView(butFill, paramsSmall);
			}
			else
				board3.addView(but, params);
			break;
		case 3:
			switch (item) {
			case 0:
				TableRow board0 = (TableRow) findViewById(R.id.tableRow1);		
				board0.addView(but, params);
				break;
			case 1:
				TableRow board1 = (TableRow) findViewById(R.id.tableRow2);		
				board1.addView(but, params);
				break;
			case 2:
				TableRow board02 = (TableRow) findViewById(R.id.tableRow3);		
				board02.addView(but, params);
				break;
			}

			break;
		case 4:

			if (item < 2) {
				TableRow board0 = (TableRow) findViewById(R.id.tableRow1);
				if(item == 0){
					board0.addView(but, params);
					board0.addView(butFill, paramsSmall);
				}
				else
					board0.addView(but, params);

			} else {
				if (item >= 2 && item < 4) {
					TableRow board1 = (TableRow) findViewById(R.id.tableRow2);
					if(item == 2){
						board1.addView(but, params);
						board1.addView(butFill, paramsSmall);
					}
					else
						board1.addView(but, params);				} 
			}
			TableRow boardNonVisible = (TableRow) findViewById(R.id.tableRow3);
			boardNonVisible.setVisibility(View.GONE);

			break;
		case 5:
			if (item < 2) {
				TableRow board0 = (TableRow) findViewById(R.id.tableRow1);
				if(item == 0){
					board0.addView(but, params);
					board0.addView(butFill, params);
				}
				else
					board0.addView(but, params);

			} else {
				if (item == 2) {
					TableRow board1 = (TableRow) findViewById(R.id.tableRow2);
					board1.addView(but, params);				
				} 
				else{
					if (item >2 && item <5) {
						TableRow board002 = (TableRow) findViewById(R.id.tableRow3);
						if(item == 3){
							board002.addView(but, params);	
							board002.addView(butFill, params);
						}
						else
							board002.addView(but, params);	

					}		
				}
			}

			break;

		default:

			// Use this part of code if you need to show 2 columns an 3 rows

			setLayoutParams(params,item);

			if (item < 2) {
				TableRow board = (TableRow) findViewById(R.id.tableRow1);

				board.addView(but, params);
			} else {
				if (item >= 2 && item < 4) {
					TableRow board22 = (TableRow) findViewById(R.id.tableRow2);

					board22.addView(but, params);
				} else {
					TableRow board33 = (TableRow) findViewById(R.id.tableRow3);

					board33.addView(but, params);
				}
			}

			break;
		}
	}

	private void setLayoutParams(TableRow.LayoutParams params,int index){
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
	}

}
