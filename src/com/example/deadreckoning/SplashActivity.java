package com.example.deadreckoning;

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
	
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		
		Thread splashTimer = new Thread() {
			public void run() {
				try{
					sleep(5000);
				} 
				catch(InterruptedException e){
					
					e.printStackTrace();
				}
				finally{
					Intent mainActivity = new Intent("android.intent.category.MENU");
					startActivity(mainActivity);
				}
			}
		};
		splashTimer.start();
	}
}

