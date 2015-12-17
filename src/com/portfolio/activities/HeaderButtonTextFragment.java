package com.portfolio.activities;

import com.portfolio.R;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.util.UIUtils;


import android.app.ActionBar.LayoutParams;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout.Alignment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class HeaderButtonTextFragment extends HeaderFragment {

	final int ICON_HEIGHT = 30;
	final int ICON_WIDTH = 30;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View header =  inflater.inflate(
				R.layout.header_layout_button_text, container, false);

		PortfolioModel portfolioModel = PortfolioModel
				.getInstance(getActivity());
		IMenu menu = portfolioModel.getPorfolioMenu();
		ITheme theme = portfolioModel.getTheme();

		UIUtils.setGradient(header, 
				theme.getTitleBarBackground()
				.getStartColor(), theme.getTitleBarBackground().getEndColor(),
				String.valueOf(theme.getTitleBarBackground().getAngle()));

		Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/Raleway-Bold.ttf");


		final ViewFlipper flipper = (ViewFlipper) (getActivity()).findViewById(R.id.flipper);

		/*final ImageButton customButton = (ImageButton) header.findViewById(R.id.header_button);
		customButton.setBackgroundColor(Color.TRANSPARENT);

		portfolioModel.getMedia(new IMediaListener() {
			@Override
			public void onImageReady(Bitmap bitmap) {
				customButton.setImageBitmap(bitmap);
				customButton.setScaleType(ScaleType.FIT_CENTER);
				customButton.getLayoutParams().height = UIUtils.getDimension(ICON_HEIGHT);
				customButton.getLayoutParams().width = UIUtils.getDimension(ICON_WIDTH);
			}

		},theme.getUrlImages());

		customButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				flipper.setInAnimation(UIUtils.inFromRightAnimation());
				flipper.setOutAnimation(UIUtils.outToLeftAnimation());
				flipper.showNext();
				if (getActivity() instanceof BaseActivity)
					// Si es el cero es la pagina
					if (flipper.getDisplayedChild() == 1)
						((BaseActivity) getActivity()).getHeaderView().setVisibility(
								View.GONE);
					else
						((BaseActivity) getActivity()).onContentVisible();

			}
		});

*/
		TextView customSubtittle = (TextView) header
				.findViewById(R.id.header_text);
		customSubtittle.setTextSize(18);
		customSubtittle.setTypeface(font1, Typeface.BOLD);
		customSubtittle.setTextScaleX(1);
		customSubtittle.setText(page.getTitle());
		customSubtittle.setTextColor(Color.parseColor(menu.getText_color()));

		
		return header;
	}


}
