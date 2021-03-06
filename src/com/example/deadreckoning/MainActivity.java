package com.example.deadreckoning;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import Handlers.TTSHandler;
import Network.Client;
import Network.Server;

public class MainActivity extends Activity {

	private Button buttonClient; 
	private Button buttonServer;
	private Button buttonHelp;
	private TTSHandler tts;
	private LocationListener locationListener;
	
	public static Client client;
	public static Server server;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		
		// First content view is the splash screen
		setContentView(R.layout.activity_main);
		
		buttonClient = (Button)findViewById(R.id.button1);
		buttonServer = (Button)findViewById(R.id.button2);
		buttonHelp = (Button)findViewById(R.id.button3);
		
		tts = new TTSHandler(this);
		// Acquire a reference to the system Location Manager
		//LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		//locationListener = new LocationListener(this);
		
		// Register the listener with the Location Manager to receive location updates
		//locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, (long)0.0, (float)0.0, locationListener);
		
		buttonClient.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				tts.speakPhrase(buttonClient.getText().toString());
				tts.shutDownTTS();
				client = new Client(2346, "192.168.45.139");
				startActivity(new Intent(MainActivity.this, LocateActivity.class));
			}
		});
		
		buttonServer.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				tts.speakPhrase(buttonServer.getText().toString());
				tts.shutDownTTS();
				server = new Server(2346);
				startActivity(new Intent(MainActivity.this, LocateActivity.class));
			}
		});
		
		buttonHelp.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				tts.speakPhrase(buttonHelp.getText().toString());
				tts.shutDownTTS();
				startActivity(new Intent(MainActivity.this, HelpActivity.class));
				
			}
		});
		
		buttonClient.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) 
            {
            	tts.speakPhrase(buttonClient.getText().toString());
                return false;
            }
        });
		
		buttonServer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) 
            {
            	tts.speakPhrase(buttonServer.getText().toString());
                return false;
            }
        });
		
		buttonHelp.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) 
            {
            	tts.speakPhrase(buttonHelp.getText().toString());
                return false;
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		Log.i("ss12", "on destroy - main activity");
		tts.shutDownTTS();
		if(server != null) {
			server.shutDown();
		}
		if(client != null) {
			client.shutDown();
		}
		
	}
}
