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
		setContentView(R.layout.splash_screen);
		
		// Create a timer that switches from splash screen after 5 seconds
		// TODO switch after TTS finishes or user taps on splash screen
		new CountDownTimer(5000,1000) {
			
			@Override
			public void onFinish() {
				// Set content to the activity screen
				setContentView(R.layout.activity_main);
			}

			
			@Override
			public void onTick(long millisUntilFinished) {
				// Do nothing but act as a countdown per second
			}
			
		}.start();
		/*
		Thread splashTimer = new Thread() {
			public void run() {
				try {
					
					sleep(5000);
					setContentView(R.layout.activity_main);
				}
				catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		};
		Log.i("ss12", "before splash");
		splashTimer.run();
		Log.i("ss12", "after splash");
		*/
		
		buttonClient = (Button)findViewById(R.id.button1);
		buttonServer = (Button)findViewById(R.id.button2);
		buttonHelp = (Button)findViewById(R.id.button3);
		
		
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
