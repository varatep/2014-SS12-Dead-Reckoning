package dr.main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Network.Client;
import Network.Server;
import Network.WiFiDirectBroadcastReceiver;

import dr.main.R;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private final IntentFilter intentFilter = new IntentFilter();
	Channel mChannel;
	WifiP2pManager mManager;
	WiFiDirectBroadcastReceiver receiver;
	private PeerListListener myPeerListListener;
	private WifiP2pDeviceList deviceList;
	private List peers = new ArrayList();
	
	PeerListListener peerListListener = new PeerListListener() {
        @Override
        public void onPeersAvailable(WifiP2pDeviceList peerList) {

        	Log.i("ss12", "onPeersAvailable - main");
            // Out with the old, in with the new.
            peers.clear();
            peers.addAll(peerList.getDeviceList());

            // If an AdapterView is backed by this data, notify it
            // of the change.  For instance, if you have a ListView of available
            // peers, trigger an update.
            //((WiFiPeerListAdapter) getListAdapter()).notifyDataSetChanged();
            Log.i("ss12", peers.toString());
            if (peers.size() == 0) {
                Log.d("ss12", "No devices found");
                return;
            }
        }
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 //  Indicates a change in the Wi-Fi P2P status.
	    intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

	    // Indicates a change in the list of available peers.
	    intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

	    // Indicates the state of Wi-Fi P2P connectivity has changed.
	    intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

	    // Indicates this device's details have changed.
	    intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
	    
	    mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
	    mChannel = mManager.initialize(this, getMainLooper(), null);
	    
	    mManager.discoverPeers(mChannel, new WifiP2pManager.ActionListener() {
	        @Override
	        public void onSuccess() {
	            Log.i("ss12", "discover peers started");
	            
	            /*mManager.requestPeers(mChannel, myPeerListListener);
                
                myPeerListListener.onPeersAvailable(deviceList);
                Collection<WifiP2pDevice> c = deviceList.getDeviceList();
                ArrayList<WifiP2pDevice> describedList = new ArrayList<WifiP2pDevice>(c);
                
                if(describedList != null) {
                	for(int i = 0; i < describedList.size(); i++) {
                		Log.i("ss12", describedList.get(i).deviceName);
                	}
                }*/
	        }

	        @Override
	        public void onFailure(int reasonCode) {
	            Log.i("ss12", "discover peers - failed");
	        }
	    });
	    
	}
	
	@Override
	protected void onResume() {
	    super.onResume();
	    receiver = new WiFiDirectBroadcastReceiver(mManager, mChannel, this, peerListListener);
        registerReceiver(receiver, intentFilter);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		unregisterReceiver(receiver);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void connect(View view) {
		
		//Client client = new Client(2000, "192.168.45.139");
		
		WifiP2pDevice device = null;
		WifiP2pConfig config = new WifiP2pConfig();
		config.deviceAddress = device.deviceAddress;
		mManager.connect(mChannel, config, new ActionListener() {
		 
		    @Override
		    public void onSuccess() {
		        //success logic
		    }
		 
		    @Override
		    public void onFailure(int reason) {
		        //failure logic
		    }
		});
		
	}
	
	public void listen(View view) {
		
		Server server = new Server(2000);
		//String[] ips = IPFinder.getIPs();
		//for(int i = 0; i < ips.length; i++) {
		//	Log.i("ss12", ips[i]);
		//}
	}

}
