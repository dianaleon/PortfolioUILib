package com.portfolio.activities;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.portfolio.R;
import com.portfolio.components.ContactItem;
import com.portfolio.components.menu;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IContactPage;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.component.IContactObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.utils.UIUtils;

@SuppressLint("ResourceAsColor")
public class ContactActivity extends Activity {
	private Button buttonMenu;
	ViewFlipper flipper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacto_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");

		// levanto la pagina de esa posicion
		// la interfaz que se llama contact, que tiene una lista de url + nombre
		// + etc
		IContactPage contactPage = (IContactPage) PortfolioModel.getInstance(
				this).getPageInfo(position);

		// Cargar el titulo y el subtitulo
		Typeface font1 = Typeface.createFromAsset(getAssets(),
				"fonts/CopperplateGothicStd 31BC.otf");
		TextView customTittle = (TextView) findViewById(R.id.tittle_app);
		customTittle.setTypeface(font1);
		customTittle.setTextSize(22);

		Typeface font2 = Typeface.createFromAsset(getAssets(),
				"fonts/CopperplateGothicStd 31BC.otf");
		TextView customSubtittle = (TextView) findViewById(R.id.sub_tittle_app);
		customSubtittle.setTypeface(font2);
		customSubtittle.setTextSize(14);
		customSubtittle.setTextScaleX(1);

		// Setear el titulo en la pagina
		PortfolioModel portfolioModel = PortfolioModel.getInstance(this);
		IMenu menu = portfolioModel.getPorfolioMenu();
		menu.getTitle();
		menu.getBackground();
		TextView textViewTittle = (TextView) findViewById(R.id.tittle_app);
		TextView textViewSubTittle = (TextView) findViewById(R.id.sub_tittle_app);
		textViewTittle.setText(menu.getTitle());
		textViewSubTittle.setText(menu.getSubtitle());

		// cargar el layout
		LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_content);
		UIUtils.setGradient(linearLayout, contactPage.getType().getBackground().getStartColor(), contactPage.getType().getBackground().getEndColor(), String.valueOf(contactPage.getType().getBackground().getAngle()));

		List<IPageObject> objetos = contactPage.getObjects();
		for (int index = 0; index < objetos.size(); index++) {
			IPageObject object = objetos.get(index);
			String content = object.getContent();
			switch (object.getType()) {
			case IPageObject.type_contact:
				IContactObject contact = (IContactObject) object;
				String type = contact.getSubtype();
				if (type != null) {
					ContactItem contactItem = new ContactItem(this);
					if (type.equalsIgnoreCase(IContactPage.movil) || type.equalsIgnoreCase(IContactPage.telefono)) {
						contactItem.fill(content, contact.getTextColor(),
								contact.getStartColorBackground(),
								contact.getEndColorBackground(),
								contact.getGradientOrientatio(), true);
					} else {
						contactItem.fill(content, contact.getTextColor(),
								contact.getStartColorBackground(),
								contact.getEndColorBackground(),
								contact.getGradientOrientatio());						
					}
					linearLayout.addView(contactItem);
				}
			}
		}

		// MENU
		final menu menuLayout = (menu) findViewById(R.id.layout_menu);
		LinearLayout bgFooter = (LinearLayout) findViewById(R.id.layout_footer);
		String colorStartMenu = menu.getBackground().getStartColor();
		String colorEndMenu = menu.getBackground().getEndColor();
		int cStartMenu = Color.parseColor(colorStartMenu);
		int cEndMenu = Color.parseColor(colorEndMenu);
		GradientDrawable gdMenu = new GradientDrawable(
				GradientDrawable.Orientation.TOP_BOTTOM, new int[] {
						cStartMenu, cEndMenu });
		bgFooter.setBackgroundDrawable(gdMenu);
		menuLayout.init();
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		buttonMenu = (Button) findViewById(R.id.buttonMenu);
		buttonMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				flipper.setInAnimation(inFromRightAnimation());
				flipper.setOutAnimation(outToLeftAnimation());
				flipper.showNext();
			}
		});

	}

	private Animation inFromRightAnimation() {

		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(100);
		inFromRight.setInterpolator(new AccelerateInterpolator());

		return inFromRight;

	}

	private Animation outToLeftAnimation() {

		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(100);
		outtoLeft.setInterpolator(new AccelerateInterpolator());

		return outtoLeft;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

}
