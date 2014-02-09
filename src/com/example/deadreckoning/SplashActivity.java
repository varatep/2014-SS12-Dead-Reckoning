package com.example.deadreckoning;

import Handlers.TTSHandler;
import Handlers.AccessibilityHandler;
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
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends Activity {
	private TTSHandler tts;
	private TextView splashText;
	private Button invisButton;
	private AccessibilityHandler access;
	protected void onCreate(Bundle savedInstanceState) { 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		access = new AccessibilityHandler(this);
		
		Thread splashTimer = new Thread() {
			public void run() {
				try{
					sleep(300);
					sleep(500);
					invisButton.performLongClick();
					
					//test
				} 
				catch(Exception e){
					
					e.printStackTrace();
				}
				finally{
					
					while(tts.isSpeaking()) {
						
					}
					Intent mainActivity = new Intent("android.intent.category.MENU");
					startActivity(mainActivity);
				}
			}
		};
		
		splashText = (TextView)findViewById(R.id.splashText);
		//d
		//tts = new TTSHandler(this);
		
		invisButton = (Button)findViewById(R.id.invisButton);
		tts = new TTSHandler (this);
		tts.shutUp();
		
		invisButton.setOnLongClickListener(new View.OnLongClickListener() { 
            @Override
            public boolean onLongClick(View v) 
            {
            	tts.speakPhrase(splashText.getText().toString());
            	tts.shutDownTTS();
                return false;
            }
        });
		
		splashTimer.start();
		
		
	}
}


