package com.example.deadreckoning;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import Handlers.TTSHandler;
import Handlers.AccessibilityHandler;
import Handlers.VibrationHandler;

public class MainActivity extends Activity {

	private Button buttonClient; 
	private Button buttonServer;
	private Button buttonHelp;
	private LocationListener locationListener;
	private String user_name = null;
	private AccessibilityHandler access;
	Vibrator vibe;
	VibrationHandler vibrationHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		// First content view is the splash screen
		setContentView(R.layout.activity_main);
		
		buttonClient = (Button)findViewById(R.id.button1);
		buttonServer = (Button)findViewById(R.id.button2);
		buttonHelp = (Button)findViewById(R.id.button3);

		access = new AccessibilityHandler(this);
        vibrationHandler = new VibrationHandler(this);
        vibrationHandler.pulseLose();
		initializeVibrator();

		buttonClient.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				access.announce(buttonClient.getText().toString());
				startActivity(new Intent(MainActivity.this, MainActivity.class));
			}
		});
		
		buttonServer.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				access.announce(buttonServer.getText().toString());
				startActivity(new Intent(MainActivity.this, MainActivity.class));
			}
		});
		
		buttonHelp.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				access.announce(buttonHelp.getText().toString());
				startActivity(new Intent(MainActivity.this, HelpActivity.class));
			}
		});
		
		buttonClient.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) 
            {
            	access.announce(buttonClient.getText().toString());
                return false;
            }
        });
		
		buttonServer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) 
            {
            	access.announce(buttonServer.getText().toString());
                return false;
            }
        });
		
		buttonHelp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) 
            {
            	access.announce(buttonHelp.getText().toString());
                return false;
            }
        });		
	}
	
    void initializeVibrator() {
        vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
