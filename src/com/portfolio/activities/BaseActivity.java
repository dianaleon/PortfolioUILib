package com.portfolio.activities;

import com.portfolio.R;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.IPage;
import com.portfolio.model.interfaces.ITheme;
import com.portfolio.util.UIUtils;

import android.R.color;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public abstract class BaseActivity extends Activity {

	protected View headerView;
	protected View footerView;
	protected ITheme theme;
	protected IPage page;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		theme = PortfolioModel.getInstance(this).getTheme();
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");
		page = PortfolioModel.getInstance(this)
				.getPageInfo(position);
		
	}

	public void loadHeader(IPage page) {
		
		headerView = findViewById(R.id.layout_header_container);
		headerView.setBackgroundColor(color.transparent);
		String typePage = page.getLayout();
		 
		HeaderFragment headerFragment = UIUtils.getHeaderFragment(this,typePage);
		headerFragment.setPage(page);
		 
		// container_header.setLayoutParams(new LinearLayout.LayoutParams(
		// LinearLayout.LayoutParams.MATCH_PARENT, 0, 3.0f));

		// container_header.addView(headerView, 0);
		getFragmentManager().beginTransaction()
				.add(R.id.layout_header_container, headerFragment).commit();
		 
	}

	public void loadFooter() {
		LinearLayout container_footer = (LinearLayout) findViewById(R.id.layout_footer_container);
		footerView = getLayoutInflater().inflate(getFooterLayoutId(), null);
		footerView.setId(R.id.layout_footer);
		footerView.setLayoutParams(new ViewGroup.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT));
		container_footer.addView(footerView);
	}

	private int layoutId(String layoutName) {
		if (layoutName == null)
			return 0;
		int picId = getResources().getIdentifier(layoutName, "layout",
				getApplicationContext().getPackageName());
		return picId;
	}

	private int getHeaderLayoutId() {
		int id = layoutId(theme.getHeaderLayout());
		if (id != 0) {
			return id;
		}

		return layoutId("header_layout");
	}

	private int getFooterLayoutId() {
		int id = layoutId(theme.getFooterLayout());
		if (id != 0) {
			return id;
		}

		return layoutId("footer_layout");
	}

	public View getHeaderView() {
		return this.headerView;
	}
	
	public abstract void onContentVisible();
}
