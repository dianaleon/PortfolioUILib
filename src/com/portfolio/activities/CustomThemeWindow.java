package com.portfolio.activities;

import com.portfolio.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class CustomThemeWindow extends Activity {
	protected TextView title;
	protected TextView subTitle;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        
        //title = (TextView) findViewById(R.id.title);
        //subTitle  = (TextView) findViewById(R.id.subTitle);
	}
}
