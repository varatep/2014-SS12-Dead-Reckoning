package com.example.deadreckoning;

import Handlers.TTSHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private TTSHandler tts;
	private TextView splashText;
	private Button invisButton;
	protected void onCreate(Bundle savedInstanceState) { 
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		Thread splashTimer = new Thread() {
			public void run() {
				try{
					sleep(1000);
					invisButton.performLongClick();
					
					//test
				} 
				catch(Exception e){
					
					e.printStackTrace();
				}
				finally{
					
					while(tts.isSpeaking()) {

					}
					tts.shutDownTTS();
					Intent mainActivity = new Intent(SplashActivity.this, MainActivity.class);
					startActivity(mainActivity);
				}
			}
		};
		
		splashText = (TextView)findViewById(R.id.splashText);
		//d
		tts = new TTSHandler(this);
		
		invisButton = (Button)findViewById(R.id.invisButton);
		
		invisButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) 
            {
            	tts.speakPhrase(splashText.getText().toString());
                return false;
            }
        });
		
		splashTimer.start();
		
		
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		/*Log.i("ss12", "shutting down sockets");
		if(MainActivity.server != null) {
			MainActivity.server.shutDown();
		}
		if(MainActivity.server != null) {
			MainActivity.server.shutDown();
		}*/
	}
}


