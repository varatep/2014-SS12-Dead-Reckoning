package dr.main;

import Network.Client;
import Network.Server;

import dr.main.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void connect(View view) {
		
		Client client = new Client(12345, "172.31.208.243");
		
	}
	
	public void listen(View view) {
		
		Server server = new Server(12345);
		
	}

}
