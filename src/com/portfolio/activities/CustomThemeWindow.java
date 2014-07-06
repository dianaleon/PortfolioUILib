package com.portfolio.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.portfolio.R;

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
