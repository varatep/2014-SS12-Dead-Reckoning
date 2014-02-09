package Handlers;

import android.content.Context;
import android.os.Handler;
import android.os.Vibrator;

/**
 * Created by ngorgi on 1/14/14.
 */
public class VibrationHandler {
    Vibrator vibrator;
    boolean rejectNew = false;
    Handler mHandler;

    public VibrationHandler(Context context) {
        vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        mHandler = new Handler();
    }

    public void playPulse(int millis) {
        vibrator.vibrate(millis);
    }

    public void playIntensity(int intensity) {

        int onTime = (int) (30 * ((float) intensity / 100));
        if (onTime > 100) {
            onTime = 100;
        }
        int offTime = 30 - onTime;
        if (offTime < 0) {
            offTime = 0;
        }

        long[] pattern = {0, onTime, offTime};
        if (!rejectNew) {
            vibrator.vibrate(pattern, 0);
        }
    }

    public void stopVibrate() {
        vibrator.cancel();
    }


    public void pulseWin() {
        long[] happyPattern = {0, 100, 200, 100, 100, 100, 100, 400};
        if (!rejectNew) {
            vibrator.vibrate(happyPattern, -1);
            mHandler.postDelayed(vibrationComplete, 750);

        }
    }


    public void pulseLose() {
        long[] sadPattern = {0, 500, 200, 500, 200, 450};
        if (!rejectNew) {

            vibrator.vibrate(sadPattern, -1);
            mHandler.postDelayed(vibrationComplete, 750);

        }
    }

    VibrationCompletedInterface vibrationCompletedInterface;

    public void setVibrationCompletedInterface(VibrationCompletedInterface vibrationCompletedInterface) {
        this.vibrationCompletedInterface = vibrationCompletedInterface;
    }

    public interface VibrationCompletedInterface {
        /**
         * The previously triggered notifiable vibration has completed.
         */
        public void vibrationCompleted();
    }


    public void playGameStartNotified() {
        vibrator.cancel();
        rejectNew = true;
        long[] happyPattern = {0, 100, 100, 100, 100, 100, 100};
        long duration = 0;
        for (long l : happyPattern) {
            duration = duration + l;
        }
        mHandler.postDelayed(vibrationComplete, 750);
        vibrator.vibrate(happyPattern, -1);
    }


    private Runnable vibrationComplete = new Runnable() {
        @Override
        public void run() {

            if (rejectNew) {
                if (vibrationCompletedInterface != null) {
                    vibrationCompletedInterface.vibrationCompleted();
                }
                rejectNew = false;
            }

        }
    };
}

