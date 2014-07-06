package com.portfolio.activities;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.portfolio.R;
import com.portfolio.model.PortfolioModel;
import com.portfolio.model.interfaces.INetworkPage;
import com.portfolio.model.interfaces.component.INetworkObject;
import com.portfolio.model.interfaces.component.IPageObject;
import com.portfolio.util.UIUtils;

public class NetworkActivity extends Activity {

	String addressfb = null;
	String addresstwitter = null;
	String addressgplus = null;
	String addressinstagram = null;
	String addresspinterest = null;
	String addresslinkedin = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_network_layout);
		Bundle bundle = this.getIntent().getExtras();
		int position = bundle.getInt("position");

		// levanto la pagina de esa posicion
		// la interfaz que se llama redes sociales,....
		INetworkPage netPage = (INetworkPage) PortfolioModel.getInstance(this)
				.getPageInfo(position);

		// caragr info
		List<IPageObject> objetos = netPage.getObjects();

		UIUtils.setHeader(this);

		TableLayout tableLayout = (TableLayout) findViewById(R.id.layout_content);
		UIUtils.setGradient(tableLayout, netPage.getType().getBackground().getStartColor(), netPage.getType().getBackground().getEndColor(), String.valueOf(netPage.getType().getBackground().getAngle()));

		// recorro los objetos del json
		for (int index = 0; index < objetos.size(); index++) {

			IPageObject object = objetos.get(index);
			String content = object.getContent();
			Button but = new Button(this);
			but.setTextColor(Color.TRANSPARENT);

			switch (object.getType()) {

			case IPageObject.type_network:
				INetworkObject social = (INetworkObject) object;
				String type = social.getSubtype();
				if (type != null) {
					if (type.equalsIgnoreCase(INetworkPage.facebook)) {
						addressfb = content;
						but.setBackgroundResource(R.drawable.fb);

						// but.setTag(pos);
						but.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Uri uri = Uri.parse(addressfb);
								Intent intent = new Intent(Intent.ACTION_VIEW,
										uri);
								startActivity(intent);
							}
						});
					}

					if (type.equalsIgnoreCase(INetworkPage.twitter)) {

						addresstwitter = content;
						but.setBackgroundResource(R.drawable.twitter);

						// but.setTag(pos);
						but.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Uri uri = Uri.parse(addresstwitter);
								Intent intent = new Intent(Intent.ACTION_VIEW,
										uri);
								startActivity(intent);
							}
						});
					}

					if (type.equalsIgnoreCase(INetworkPage.gplus)) {

						addressgplus = content;
						but.setBackgroundResource(R.drawable.gplus);

						// but.setTag(pos);
						but.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Uri uri = Uri.parse(addressgplus);
								Intent intent = new Intent(Intent.ACTION_VIEW,
										uri);
								startActivity(intent);
							}
						});
					}
					if (type.equalsIgnoreCase(INetworkPage.instagram)) {

						addressinstagram = content;
						but.setBackgroundResource(R.drawable.instagram);

						// but.setTag(pos);
						but.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Uri uri = Uri.parse(addressinstagram);
								Intent intent = new Intent(Intent.ACTION_VIEW,
										uri);
								startActivity(intent);
							}
						});
					}
					if (type.equalsIgnoreCase(INetworkPage.pinterest)) {

						addresspinterest = content;
						but.setBackgroundResource(R.drawable.pinterest);

						// but.setTag(pos);
						but.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Uri uri = Uri.parse(addresspinterest);
								Intent intent = new Intent(Intent.ACTION_VIEW,
										uri);
								startActivity(intent);
							}
						});
					}
					if (type.equalsIgnoreCase(INetworkPage.linkedin)) {

						addresslinkedin = content;
						but.setBackgroundResource(R.drawable.lin);

						// but.setTag(pos);
						but.setOnClickListener(new OnClickListener() {
							public void onClick(View v) {
								Uri uri = Uri.parse(addresslinkedin);
								Intent intent = new Intent(Intent.ACTION_VIEW,
										uri);
								startActivity(intent);
							}
						});
					}
				}

			}

			if (index < 3) {
				TableRow board = (TableRow) findViewById(R.id.tableRow1);
				board.addView(but, new TableRow.LayoutParams(
						TableRow.LayoutParams.WRAP_CONTENT,
						TableRow.LayoutParams.WRAP_CONTENT, 1f));
			} else {
				TableRow board2 = (TableRow) findViewById(R.id.tableRow2);
				board2.addView(but, new TableRow.LayoutParams(
						TableRow.LayoutParams.WRAP_CONTENT,
						TableRow.LayoutParams.WRAP_CONTENT, 1f));
			}
		}

		// MENU
		UIUtils.setMenu(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
	}

}
