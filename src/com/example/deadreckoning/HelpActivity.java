package com.example.deadreckoning;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import Handlers.TTSHandler;

public class HelpActivity extends Activity {
	
	private Button buttonReturn; 
	private TTSHandler tts;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_help);
		
		buttonReturn = (Button)findViewById(R.id.button4);
		
		tts = new TTSHandler(this);
		
		buttonReturn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				//tts.speakPhrase(buttonReturn.getText().toString());
				startActivity(new Intent(HelpActivity.this, MainActivity.class));
			}
		});
		
		buttonReturn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) 
            {
            	tts.speakPhrase(buttonReturn.getText().toString());
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

}
