package Network;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.provider.Settings;
import android.util.Log;
import dr.main.MainActivity;
import dr.main.R;

public class WiFiDirectBroadcastReceiver extends BroadcastReceiver {

    private WifiP2pManager manager;
    private Channel channel;
    private MainActivity activity;
    private PeerListListener myPeerListListener;

    public WiFiDirectBroadcastReceiver(WifiP2pManager manager, Channel channel,
            MainActivity activity) {
        super();
        this.manager = manager;
        this.channel = channel;
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
                Log.i("ss12", "direct wifi enabled");
            } else {
            	//send the user to the wifi screen to enable it
                Log.i("ss12", "direct wifi disabled");
                
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(activity);
                dlgAlert.setPositiveButton("Ok",
                	    new DialogInterface.OnClickListener() {
                	        public void onClick(DialogInterface dialog, int which) {
                	        	Log.i("ss12", "ok button hit");
                	        	Intent wifi = new Intent(Settings.ACTION_WIFI_SETTINGS);
                                activity.startActivity(wifi);
                	        }
                	    }); 
                dlgAlert.setMessage("Wifi is disabled, would you like to enable?");
                dlgAlert.setTitle("No wifi signal");
                dlgAlert.setCancelable(true);
                dlgAlert.create().show();               
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            // Call WifiP2pManager.requestPeers() to get a list of current peers
        	if (manager != null) {
        		Log.i("ss12", "peers changed and manager != null");
                manager.requestPeers(channel, myPeerListListener);
                //manager.onPeersAvailable();
            }
        } else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {
            // Respond to new connection or disconnections
        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {
            // Respond to this device's wifi state changing
        }
    }
}
