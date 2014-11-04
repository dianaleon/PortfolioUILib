package com.portfolio.components;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.portfolio.R;
import com.portfolio.activities.AccordionPhotoActivity;
import com.portfolio.activities.AccordionTextActivity;
import com.portfolio.activities.CatalogoActivity;
import com.portfolio.activities.ContactActivity;
import com.portfolio.activities.ImageActivity;
import com.portfolio.activities.NetworkActivity;
import com.portfolio.activities.PhotoGridActivity;
import com.portfolio.activities.PhotoTextActivity;
import com.portfolio.activities.PhotoTextGridListActivity;
import com.portfolio.activities.TextTextGridListActivity;
import com.portfolio.activities.VideoActivity;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.entities.component.BackgroundObject;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.IPage;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.util.MenuBuilder;
import com.portfolio.util.UIUtils;

public class menu extends LinearLayout {
	
	private Context context;

//	public menu(Context context) {
//		super(context);
//		this.context = context;
//		init();
//	}

	public menu(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
	}
	
	public void init(MenuBuilder menuBuilder){
		menuBuilder.build(this);
	}

//	public void init() {
//		Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
//				"fonts/CopperGothicStd29AB.otf");
//		final PortfolioModel portfolioModel = PortfolioModel
//				.getInstance(getContext());
//		List<String> titles = (List<String>) portfolioModel.getPagesTitles();
//		List<Integer> posicion = (List<Integer>) portfolioModel
//				.getPagesPositions();
//		IMenu menu = PortfolioModel.getInstance(getContext()).getPorfolioMenu();
//		ITheme theme = PortfolioModel.getInstance(getContext()).getTheme();
//		//IPage pages = PortfolioModel.getInstance(getContext()).getPageInfo(0).get;
//		
//		
//
//		for (int index = 0; index < posicion.size(); index++) {
//			int pos = posicion.get(index);
//		
//			Button but = new Button(getContext());
//			UIUtils.setTextColor(but, menu.getText_color());
//			IPage page = PortfolioModel.getInstance(getContext()).getPageInfo(pos);
//			BackgroundObject item = page.getType().getBackground();
//			UIUtils.setGradient(but, item.getStartColor(), item.getEndColor(), String.valueOf(item.getAngle()));
//
//			but.setHeight(104);
//			but.setTypeface(tf);
//			but.setText(page.getTitle());
//			but.setTag(pos);
//			but.setOnClickListener(new OnClickListener() {
//
//				@Override
//				public void onClick(View v) {
//					int pos = (Integer) v.getTag();
//					IPage page = portfolioModel.getPageInfo(pos);
//					String layout = page.getLayout();
//					if (layout.equalsIgnoreCase("image")) {
//						Intent intent4 = new Intent(getContext(),
//								ImageActivity.class);
//						intent4.putExtra("position", pos);
//						getContext().startActivity(intent4);
//					}
//					if (layout.equalsIgnoreCase("redesSociales")) {
//						Intent intent3 = new Intent(getContext(),
//								NetworkActivity.class);
//						intent3.putExtra("position", pos);
//						getContext().startActivity(intent3);
//
//					}
//					if (layout.equalsIgnoreCase("contacto")) {
//						Intent intent6 = new Intent(getContext(),
//								ContactActivity.class);
//						intent6.putExtra("position", pos);
//						getContext().startActivity(intent6);
//					}
//
//					if (layout.equalsIgnoreCase("photo_text_gridlist")) {
//						Intent intent = new Intent(getContext(),
//								PhotoTextGridListActivity.class);
//						intent.putExtra("position", pos);
//						getContext().startActivity(intent);
//					}
//					if (layout.equalsIgnoreCase("photo_grid")) {
//						Intent intent = new Intent(getContext(),
//								PhotoGridActivity.class);
//						intent.putExtra("position", pos);
//						getContext().startActivity(intent);
//					}
//
//					if (layout.equalsIgnoreCase("catalogo")) {
//						Intent intentCat = new Intent(getContext(),
//								CatalogoActivity.class);
//						intentCat.putExtra("position", pos);
//						getContext().startActivity(intentCat);
//					}
//
//					if (layout.equalsIgnoreCase("curriculum")) {
//						// TODO
//					}
//					if (layout.equalsIgnoreCase("text_subtopic")) {
//						// TODO
//					}
//
//					if (layout.equalsIgnoreCase("photo_gallery")) {
//						// TODO
//					}
//					if (layout.equalsIgnoreCase("video")) {
//						Intent intent = new Intent(getContext(),
//								VideoActivity.class);
//						intent.putExtra("position", pos);
//						getContext().startActivity(intent);
//					}
//
//					if (layout.equalsIgnoreCase("photo_text")) {
//						Intent intent5 = new Intent(getContext(),
//								PhotoTextActivity.class);
//						intent5.putExtra("position", pos);
//						getContext().startActivity(intent5);
//					}
//					if (layout.equalsIgnoreCase("text_text_gridlist")) {
//						Intent intent6 = new Intent(getContext(),
//								TextTextGridListActivity.class);
//						intent6.putExtra("position", pos);
//						getContext().startActivity(intent6);
//					}
//					
//					if (layout.equalsIgnoreCase("accordion_image_list")) {
//						Intent intent7 = new Intent(getContext(),
//								AccordionPhotoActivity.class);
//						intent7.putExtra("position", pos);
//						getContext().startActivity(intent7);
//					}
//					
//					if (layout.equalsIgnoreCase("accordion_text_list")) {
//						Intent intent8 = new Intent(getContext(),
//								AccordionTextActivity.class);
//						intent8.putExtra("position", pos);
//						getContext().startActivity(intent8);
//					}
//					
//					
//
//				}
//			});
//			final LinearLayout linear = (LinearLayout) findViewById(R.id.layout);
//			//linear.setBackgroundColor(Color.parseColor(menu.getText_color()));
//			
//			final menu thisMenuInstance = this;
//		    UIUtils.setGradient(this, menu.getBackground());
//		    
//		  if ((theme.getHomeImage() != null) && (!theme.getHomeImage().equalsIgnoreCase("")))
//		
//			PortfolioModel.getInstance(context).getMedia(new IMediaListener() {
//				@Override
//				public void onImageReady(Bitmap bitmap) {
//					Drawable drawable = new BitmapDrawable(getResources(), bitmap);
//					//linear.setBackgroundColor(Color.TRANSPARENT);
//					thisMenuInstance.setBackgroundDrawable(drawable);
//					
//				}
//
//			}, theme.getHomeImage());
//			
//		
//			LayoutParams params = new LayoutParams(
//			        LayoutParams.MATCH_PARENT,      
//			        LayoutParams.WRAP_CONTENT
//			);
//			if (index == (titles.size() - 1)) {
//				params.setMargins(0, 0, 0, 0);
//			} else {
//				params.setMargins(0, 0, 0, 0);				
//			}
//			but.setLayoutParams(params);
//			linear.addView(but);
//		}
//
//	}
}