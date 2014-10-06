package com.portfolio.activities;

import com.portfolio.R;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IMenu;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.util.UIUtils;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HeaderTitleSubtitleFragment extends HeaderFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout header = (LinearLayout) inflater.inflate(
				R.layout.header_layout, container, false);

		PortfolioModel portfolioModel = PortfolioModel
				.getInstance(getActivity());
		IMenu menu = portfolioModel.getPorfolioMenu();
		ITheme theme = portfolioModel.getTheme();

		// LinearLayout layoutHeader = (LinearLayout)
		// ((Activity)getActivity()).findViewById(R.id.layout_header);
		UIUtils.setGradient(header, theme.getTitleBarBackground()
				.getStartColor(), theme.getTitleBarBackground().getEndColor(),
				String.valueOf(theme.getTitleBarBackground().getAngle()));

		Typeface font1 = Typeface.createFromAsset(getActivity().getAssets(),
				"fonts/CopperplateGothicStd 31BC.otf");

		TextView customTittle = (TextView) header.findViewById(R.id.tittle_app);
		customTittle.setTypeface(font1);
		customTittle.setTextSize(25);
		customTittle.setText(menu.getTitle());

		TextView customSubtittle = (TextView) header
				.findViewById(R.id.sub_tittle_app);
		customSubtittle.setTypeface(font1);
		customSubtittle.setTextSize(20);
		customSubtittle.setTextScaleX(1);
		customSubtittle.setText(menu.getSubtitle());

		return header;
	}

}
