package com.example.deadreckoning;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button buttonClient; 
	private Button buttonServer;
	private Button buttonHelp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		
		// First content view is the splash screen
		setContentView(R.layout.activity_main);
		
		
		
		
		buttonClient = (Button)findViewById(R.id.button1);
		buttonServer = (Button)findViewById(R.id.button2);
		buttonHelp = (Button)findViewById(R.id.button3);
		
		//fasdf
		buttonClient.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(MainActivity.this, MainActivity.class));
			}
		});
		
		buttonServer.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(MainActivity.this, MainActivity.class));
			}
		});
		
		buttonHelp.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				startActivity(new Intent(MainActivity.this, HelpActivity.class));
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
