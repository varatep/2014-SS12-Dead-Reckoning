package Handlers;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.Toast;
import java.util.Locale;



/**
 * Created by Austin on 2/7/14.
 *
 *
 */
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
            goodToGo = false;
            int result;
             if (status == TextToSpeech.SUCCESS) {
               result = mTts.setLanguage(Locale.getDefault());
                 if (result == TextToSpeech.LANG_MISSING_DATA || 
                		 result == TextToSpeech.LANG_NOT_SUPPORTED) {//error in language settings
                     		Toast.makeText(context,
                     				"language not supported",
                     				Toast.LENGTH_SHORT).show();
                 }
                 goodToGo = (result > TextToSpeech.LANG_AVAILABLE);
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
        if (goodToGo) {
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
