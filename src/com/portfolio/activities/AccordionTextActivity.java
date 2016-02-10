package com.portfolio.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.portfolio.R;
import com.portfolio.R.layout;
import com.portfolio.components.ExpandablePhotoListAdapter;
import com.portfolio.components.ExpandableTextListAdapter;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.INetworkPage;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.util.UIUtils;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;

import android.widget.ImageView;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;
public class AccordionTextActivity extends BaseActivity {

	ExpandableTextListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<String>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accordion_text);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");
		loadHeader(page);
		loadFooter();

		// Esto es para probar levantar la pagina
		INetworkPage netPage = (INetworkPage) PortfolioModel.getInstance(this)
				.getPageInfo(position);

		// caragr info
		List<IPageObject> objetos = netPage.getObjects();

		// get the listview
		expListView = (ExpandableListView) findViewById(R.id.expandable);
		expListView.setGroupIndicator(null);

		// preparing list data
		prepareListData(objetos);

		listAdapter = new ExpandableTextListAdapter(this, listDataHeader,
				listDataChild, objetos);

		// setting list adapter
		final ImageView accordionBanner = new ImageView(this);
		expListView.addHeaderView(accordionBanner);
		expListView.setAdapter(listAdapter);

		// Set background Image
		UIUtils.setGradient(expListView, netPage.getType().getBackground());

	

		
		AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		accordionBanner.setLayoutParams(layoutParams);
		accordionBanner.setScaleType(ScaleType.CENTER_CROP);
		PortfolioModel.getInstance(this).getMedia(new IMediaListener() {
			@Override
			public void onImageReady(Bitmap bitmap) {
				accordionBanner.setImageBitmap(bitmap);
				
			}

		}, netPage.getContent());

		//PRUEBA DIANA
				// Listview Group expanded listener
				expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
				 
				    @Override
				    public void onGroupExpand(int groupPosition) {
				    	ExpandableTextListAdapter customExpandAdapter = (ExpandableTextListAdapter)expListView.getExpandableListAdapter();
				    	if (customExpandAdapter == null) {return;}
		                for (int i = 0; i < customExpandAdapter.getGroupCount(); i++) {
		                    if (i != groupPosition) {
		                    	expListView.collapseGroup(i);
		                    }
		                }
				    }
				});
				
				//PRUEBA DIANA FIN
		UIUtils.setMenuApp2(this);
	}

	private void prepareListData(List<IPageObject> objetos) {

		listDataHeader = new ArrayList<String>();
		listDataChild = new HashMap<String, List<String>>();

		for (int index = 0; index < objetos.size(); index++) {
			// Adding child data
			listDataHeader.add(objetos.get(index).getTitle());
			List<String> groupElements = new ArrayList<String>();

			groupElements.add(objetos.get(index).getContent());
			listDataChild.put(listDataHeader.get(index), groupElements); // Header,

		}

	}

	@Override
	public void onContentVisible() {
		headerView.setVisibility(View.VISIBLE);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}
}
