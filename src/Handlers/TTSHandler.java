package Handlers;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.widget.Toast;
import java.util.Locale;


public class TTSHandler {
    private TextToSpeech mTts;
    private Context context;

    public boolean isGoodToGo() {
        return goodToGo;
    }

    private boolean goodToGo = false;


    public TTSHandler(Context context) {
        this.context = context;
        mTts = new TextToSpeech(context, mInitListener);

    }

    private TextToSpeech.OnInitListener mInitListener = new TextToSpeech.OnInitListener() {

        public void onInit(int status) {
        	Log.i("splashactivity",String.valueOf(status));
            goodToGo = false;
            int result;
            Log.i("splashactivity", "success value:" + String.valueOf(TextToSpeech.SUCCESS));
             if (status == TextToSpeech.SUCCESS) {
            	 Log.i("splashactivity", "inside status == success");
               result = mTts.setLanguage(Locale.getDefault());
                 if (result == TextToSpeech.LANG_MISSING_DATA || 
                		 result == TextToSpeech.LANG_NOT_SUPPORTED) {//error in language settings
                     		Toast.makeText(context,
                     				"language not supported",
                     				Toast.LENGTH_SHORT).show();
                 }
                 Log.i("splashactivity", "checking goodToGo");
                 goodToGo = (result >= TextToSpeech.LANG_AVAILABLE);
                 Log.i("splashactivity", "good to go:" + String.valueOf(goodToGo));
            }
            else {//creation of TextToSpeech was unsuccessful
                Toast.makeText(context,
                        "Can not initiate Text to Speech synthesis engine",
                        Toast.LENGTH_SHORT).show();
            }
            if (!goodToGo) {
                Toast.makeText(context,
                        "Can not initiate language",
                        Toast.LENGTH_SHORT).show();

            }
        }

    };


    public void speakPhrase(String phrase) {
    	Log.i("splashactivity", "speaking phrase attempt");
        if (goodToGo) {
        	Log.i("splashactivity","inside goodToGo");
            if (mTts.isSpeaking()) {
                shutUp();
            }
            mTts.speak(phrase, TextToSpeech.QUEUE_FLUSH, null);
        } else {
            Toast.makeText(context,
                    "not good to go",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isSpeaking() {
        return mTts.isSpeaking();
    }

    public void shutDownTTS() {
        try {
            mTts.shutdown();
        } catch (Exception e) {
        }
    }

    public int shutUp() {
        return mTts.stop();
    }
}
