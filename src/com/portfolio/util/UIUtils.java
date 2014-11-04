package com.portfolio.util;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.portfolio.R;
import com.portfolio.activities.BaseActivity;
import com.portfolio.activities.HeaderButtonTextFragment;
import com.portfolio.activities.HeaderFragment;
import com.portfolio.activities.HeaderTitleSubtitleFragment;
import com.portfolio.components.menu;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.entities.component.BackgroundObject;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.ITheme;

public class UIUtils {
	
	private static MenuBuilder menuBuilder;

	public static void setGradient(View view, BackgroundObject background) {
		UIUtils.setGradient(view, background.getStartColor(),
				background.getEndColor(), String.valueOf(background.getAngle()));
	}

	public static void setGradient(View view, String startColor,
			String endColor, String orientation) {
		if (startColor != null && endColor != null) {
			GradientDrawable g = new GradientDrawable(Orientation.TOP_BOTTOM,
					new int[] { Color.parseColor(startColor),
							Color.parseColor(endColor) });
			g.setGradientType(GradientDrawable.LINEAR_GRADIENT);
			if (orientation != null) {
				try {
					g.setGradientRadius(Float.parseFloat(orientation));
				} catch (Exception e) {

				}
			}
			view.setBackgroundDrawable(g);
		}
	}

	public static void setTextColor(TextView view, String textColor) {
		if (textColor != null && textColor.length() > 0) {
			view.setTextColor(Color.parseColor(textColor));
		}
	}

	public static HeaderFragment getHeaderFragment(Context context) {
		PortfolioModel portfolioModel = PortfolioModel.getInstance(context);
		ITheme theme = portfolioModel.getTheme();
		String layoutHeader = theme.getHeaderLayout();

		if (layoutHeader.equalsIgnoreCase("header_title_subtitle")) {
			return new HeaderTitleSubtitleFragment();
		} else if (layoutHeader.equalsIgnoreCase("header_button_title")) {
			return new HeaderButtonTextFragment();
		}
		return new HeaderTitleSubtitleFragment();
	}

	public static void setHeader(Context context) {
		// Setear el titulo en la pagina
		PortfolioModel portfolioModel = PortfolioModel.getInstance(context);
		IMenu menu = portfolioModel.getPorfolioMenu();
		ITheme theme = portfolioModel.getTheme();

		LinearLayout layoutHeader = (LinearLayout) ((Activity) context)
				.findViewById(R.id.layout_header);
		setGradient(layoutHeader,
				theme.getTitleBarBackground().getStartColor(), theme
						.getTitleBarBackground().getEndColor(),
				String.valueOf(theme.getTitleBarBackground().getAngle()));

		Typeface font1 = Typeface.createFromAsset(context.getAssets(),
				"fonts/CopperplateGothicStd 31BC.otf");

		TextView customTittle = (TextView) ((Activity) context)
				.findViewById(R.id.tittle_app);
		customTittle.setTypeface(font1);
		customTittle.setTextSize(25);
		customTittle.setText(menu.getTitle());

		TextView customSubtittle = (TextView) ((Activity) context)
				.findViewById(R.id.sub_tittle_app);
		customSubtittle.setTypeface(font1);
		customSubtittle.setTextSize(20);
		customSubtittle.setTextScaleX(1);
		customSubtittle.setText(menu.getSubtitle());
	}

	public static void setMenu(final Context context) {
		IMenu menu = PortfolioModel.getInstance(context).getPorfolioMenu();
		final menu menuLayout = (menu) ((Activity) context)
				.findViewById(R.id.layout_menu);
		menuLayout.init(UIUtils.getMenuBuilder());

		final LinearLayout bgFooter = (LinearLayout) ((Activity) context)
				.findViewById(R.id.layout_footer);
		String colorStartMenu = menu.getBackground().getStartColor();
		String colorEndMenu = menu.getBackground().getEndColor();
		setGradient(bgFooter, colorStartMenu, colorEndMenu,
				String.valueOf(menu.getBackground().getAngle()));

		final ViewFlipper flipper = (ViewFlipper) ((Activity) context)
				.findViewById(R.id.flipper);
		final ImageButton buttonMenu = (ImageButton) ((Activity) context)
				.findViewById(R.id.buttonMenu);

		PortfolioModel.getInstance(context).getMedia(new IMediaListener() {
			@Override
			public void onImageReady(Bitmap bitmap) {
				buttonMenu.setImageBitmap(bitmap);
				bgFooter.invalidate();
			}

		}, menu.getIcon());
		
		

		buttonMenu.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				flipper.setInAnimation(inFromRightAnimation());
				flipper.setOutAnimation(outToLeftAnimation());
				flipper.showNext();
				if (context instanceof BaseActivity)
					// Si es el cero es la pagina
					if (flipper.getDisplayedChild() == 1)
						((BaseActivity) context).getHeaderView().setVisibility(
								View.GONE);
					else
						((BaseActivity) context).onContentVisible();

			}
		});

	}

	public static Animation inFromRightAnimation() {

		Animation inFromRight = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, +1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		inFromRight.setDuration(100);
		inFromRight.setInterpolator(new AccelerateInterpolator());

		return inFromRight;

	}

	public static Animation outToLeftAnimation() {

		Animation outtoLeft = new TranslateAnimation(
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f);
		outtoLeft.setDuration(100);
		outtoLeft.setInterpolator(new AccelerateInterpolator());

		return outtoLeft;
	}

	public static MenuBuilder getMenuBuilder() {
		if(menuBuilder == null)
			menuBuilder = new MenuBuilder1();
		return menuBuilder;
	}

	public static void setMenuBuilder(MenuBuilder menuBuilder) {
		UIUtils.menuBuilder = menuBuilder;
	}
}
