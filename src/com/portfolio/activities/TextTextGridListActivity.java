package com.portfolio.activities;

import java.util.List;

import com.portfolio.R;
import com.portfolio.R.layout;
import com.portfolio.components.PhotoTextGridListItem;
import com.portfolio.components.TextTextGridListItem;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IPhotoTxtGridListPage;
import com.portfolio.model.interfaces.component.IImageObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.model.interfaces.component.ITextObject;
import com.portfolio.util.UIUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class TextTextGridListActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text_text_grid_list);

		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");
		loadHeader(page);
		loadFooter();

		// levanto la pagina de esa posicion
		// la interfaz que se llama text, que tiene imagen, titulo y texto
		IPhotoTxtGridListPage listPage = (IPhotoTxtGridListPage) PortfolioModel
				.getInstance(this).getPageInfo(position);
		// cargar el layout
	
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.list_of_rows);

		ScrollView scrollView = (ScrollView) findViewById(R.id.layout_content);
		UIUtils.setGradient(scrollView, listPage.getType().getBackground()
				.getStartColor(), listPage.getType().getBackground()
				.getEndColor(),
				String.valueOf(listPage.getType().getBackground().getAngle()));

		List<IPageObject> objetos = listPage.getObjects();
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			switch (object.getType()) {
			case IPageObject.type_text:
				ITextObject textObject = (ITextObject) object;
				TextTextGridListItem item = new TextTextGridListItem(this);
				item.fill(textObject.getTitle(), textObject.getTitle(),
						textObject.getContent(), textObject.getTextColor(),
						textObject.getStartColorBackground(),
						textObject.getEndColorBackground(),
						textObject.getGradientOrientatio());
				linearLayout.addView(item);
				break;
			case IPageObject.type_image:
				IImageObject imageObject = (IImageObject) object;
				TextTextGridListItem itemImage = new TextTextGridListItem(this);
				itemImage.fill(imageObject.getTitle(), imageObject.getTitle(),
						imageObject.getContent(), imageObject.getTextColor(),
						imageObject.getStartColorBackground(),
						imageObject.getEndColorBackground(),
						imageObject.getGradientOrientatio());
				linearLayout.addView(itemImage);
				break;
			}
		}

		// MENU
		UIUtils.setMenuApp2(this);
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
