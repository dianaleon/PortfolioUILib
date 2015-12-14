package com.portfolio.util;

import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.portfolio.R;
import com.portfolio.activities.AccordionPhotoActivity;
import com.portfolio.activities.AccordionTextActivity;
import com.portfolio.activities.CatalogoActivity;
import com.portfolio.activities.CatalogoActivity2;
import com.portfolio.activities.ContactActivity;
import com.portfolio.activities.ContactActivity2;
import com.portfolio.activities.ImageActivity;
import com.portfolio.activities.NetworkActivity;
import com.portfolio.activities.NetworkActivity2;
import com.portfolio.activities.PhotoGridActivity;
import com.portfolio.activities.PhotoTextActivity;
import com.portfolio.activities.PhotoTextGridListActivity;
import com.portfolio.activities.PhotoTextGridListActivity2;
import com.portfolio.activities.TextTextGridListActivity;
import com.portfolio.activities.VideoActivity;
import com.portfolio.components.menu;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.entities.component.BackgroundObject;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.IPage;
import com.portfolio.model.interfaces.ITheme;


//MENU CONSTRUCTOR
public class MenuBuilder1 implements MenuBuilder {

	@Override
	public void build(final menu baseMenu) {
		
		//PARA CARGAR INFO
		final PortfolioModel portfolioModel = PortfolioModel
				.getInstance(baseMenu.getContext());
		
		IMenu menu = PortfolioModel.getInstance(baseMenu.getContext())
				.getPorfolioMenu();
		ITheme theme = PortfolioModel.getInstance(baseMenu.getContext())
				.getTheme();

		
		
		Typeface tf = Typeface.createFromAsset(baseMenu.getContext()
				.getAssets(), "fonts/OpenSans-Bold.ttf");
		
		List<String> titles = (List<String>) portfolioModel.getPagesTitles();
		List<Integer> posicion = (List<Integer>) portfolioModel
				.getPagesPositions();
		
		for (int index = 0; index < posicion.size(); index++) {
			int pos = posicion.get(index);

			LayoutInflater inflater = LayoutInflater
					.from(baseMenu.getContext());
			View but = inflater.inflate(R.layout.menu1_item, null, false);

			IPage page = PortfolioModel.getInstance(baseMenu.getContext())
					.getPageInfo(pos);
			
			//El background viene del menu_item_COLOR
			//BackgroundObject item = page.getType().getBackground();
			BackgroundObject item = theme.getMenuItemBackground();
			
			
			UIUtils.setGradient(but, item.getStartColor(), item.getEndColor(),
					String.valueOf(item.getAngle()));

			TextView itemText = (TextView) but.findViewById(R.id.itemText);
			
			itemText.setText(page.getTitle().toUpperCase());
			itemText.setTypeface(tf);
			itemText.setPadding(0, 0, 0, 0);
			itemText.setTextColor(Color.parseColor(menu.getText_color()));
			but.setTag(pos);
			but.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					int pos = (Integer) v.getTag();
					IPage page = portfolioModel.getPageInfo(pos);
					String layout = page.getLayout();
					if (layout.equalsIgnoreCase("image")) {
						Intent intent4 = new Intent(baseMenu.getContext(),
								ImageActivity.class);
						intent4.putExtra("position", pos);
						baseMenu.getContext().startActivity(intent4);
					}
					if (layout.equalsIgnoreCase("redesSociales")) {
						Intent intent3 = new Intent(baseMenu.getContext(),
								NetworkActivity2.class);
						intent3.putExtra("position", pos);
						baseMenu.getContext().startActivity(intent3);

					}
					if (layout.equalsIgnoreCase("contacto")) {
						Intent intent6 = new Intent(baseMenu.getContext(),
								ContactActivity2.class);
						intent6.putExtra("position", pos);
						baseMenu.getContext().startActivity(intent6);
					}

					if (layout.equalsIgnoreCase("photo_text_gridlist")) {
						Intent intent = new Intent(baseMenu.getContext(),
								PhotoTextGridListActivity2.class);
						intent.putExtra("position", pos);
						baseMenu.getContext().startActivity(intent);
					}
					if (layout.equalsIgnoreCase("photo_grid")) {
						Intent intent = new Intent(baseMenu.getContext(),
								PhotoGridActivity.class);
						intent.putExtra("position", pos);
						baseMenu.getContext().startActivity(intent);
					}

					if (layout.equalsIgnoreCase("catalogo")) {
						Intent intentCat = new Intent(baseMenu.getContext(),
								CatalogoActivity2.class);
						intentCat.putExtra("position", pos);
						baseMenu.getContext().startActivity(intentCat);
					}

					if (layout.equalsIgnoreCase("curriculum")) {
						// TODO
					}
					if (layout.equalsIgnoreCase("text_subtopic")) {
						// TODO
					}

					if (layout.equalsIgnoreCase("photo_gallery")) {
						// TODO
					}
					if (layout.equalsIgnoreCase("video")) {
						Intent intent = new Intent(baseMenu.getContext(),
								VideoActivity.class);
						intent.putExtra("position", pos);
						baseMenu.getContext().startActivity(intent);
					}

					if (layout.equalsIgnoreCase("photo_text")) {
						Intent intent5 = new Intent(baseMenu.getContext(),
								PhotoTextActivity.class);
						intent5.putExtra("position", pos);
						baseMenu.getContext().startActivity(intent5);
					}
					if (layout.equalsIgnoreCase("text_text_gridlist")) {
						Intent intent6 = new Intent(baseMenu.getContext(),
								TextTextGridListActivity.class);
						intent6.putExtra("position", pos);
						baseMenu.getContext().startActivity(intent6);
					}

					if (layout.equalsIgnoreCase("accordion_image_list")) {
						Intent intent7 = new Intent(baseMenu.getContext(),
								AccordionPhotoActivity.class);
						intent7.putExtra("position", pos);
						baseMenu.getContext().startActivity(intent7);
					}

					if (layout.equalsIgnoreCase("accordion_text_list")) {
						Intent intent8 = new Intent(baseMenu.getContext(),
								AccordionTextActivity.class);
						intent8.putExtra("position", pos);
						baseMenu.getContext().startActivity(intent8);
					}

				}
			});
			final LinearLayout linear = (LinearLayout) baseMenu
					.findViewById(R.id.layout);

			//COLOR BORDE ITEMS MENU
			linear.setBackgroundColor(Color.parseColor(menu.getText_color()));
			//COLOR FONDO EXTRA DEL MENU
			baseMenu.setBackgroundColor(Color.parseColor(theme.getBackground()
					.getStartColor()));

			LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
					UIUtils.getDimension(40));
			if (index == (titles.size() - 1)) {
				params.setMargins(0, 2, 0, 2);
			} else {
				if (index == 0)
					params.setMargins(0, 0, 0, 0);
				else {
					params.setMargins(0, 2, 0, 0);
				}
			}

			but.setLayoutParams(params);
			linear.addView(but);
		}

	}

}
