package com.portfolio.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.portfolio.R;
import com.portfolio.R.layout;
import com.portfolio.components.ExpandablePhotoListAdapter;
import com.portfolio.listener.IMediaListener;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.INetworkPage;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.util.UIUtils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class AccordionPhotoActivity extends BaseActivity {
	
	ExpandablePhotoListAdapter listAdapter;
	ExpandableListView expListView;
	List<String> listDataHeader;
	HashMap<String, List<Bitmap>> listDataChild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_accordion_photo);
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
 
        listAdapter = new ExpandablePhotoListAdapter(this, listDataHeader, listDataChild,objetos);
 
        // setting list adapter
        expListView.setAdapter(listAdapter);
        
        //Set background Image
        UIUtils.setGradient(expListView, netPage.getType().getBackground());

		if ((theme.getHomeImage() != null)
				&& (!theme.getHomeImage().equalsIgnoreCase("")))

			PortfolioModel.getInstance(this).getMedia(new IMediaListener() {
				@Override
				public void onImageReady(Bitmap bitmap) {
					Drawable drawable = new BitmapDrawable(getResources(),
							bitmap);
					// linear.setBackgroundColor(Color.TRANSPARENT);
					expListView.setBackgroundDrawable(drawable);

				}

			}, theme.getHomeImage());
        //
		//PRUEBA DIANA
		// Listview Group expanded listener
		expListView.setOnGroupExpandListener(new OnGroupExpandListener() {
		 
		    @Override
		    public void onGroupExpand(int groupPosition) {
		    	ExpandablePhotoListAdapter customExpandAdapter = (ExpandablePhotoListAdapter)expListView.getExpandableListAdapter();
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
		listDataChild = new HashMap<String, List<Bitmap>>();

		for (int index = 0; index < objetos.size(); index++) {
			// Adding child data
			listDataHeader.add(objetos.get(index).getTitle());
		
			final List<Bitmap> groupElements = new ArrayList<Bitmap>();

			PortfolioModel.getInstance(this).getMedia(new IMediaListener() {
				@Override
				public void onImageReady(Bitmap bitmap) {
					groupElements.add(bitmap);

				}

			}, objetos.get(index).getContent_img());

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
