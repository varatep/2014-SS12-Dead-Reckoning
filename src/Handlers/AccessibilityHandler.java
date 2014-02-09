package Handlers;

import android.content.Context;
import android.view.accessibility.AccessibilityManager;

public class AccessibilityHandler {
    boolean isAccessibilityEnabled;
    TTSHandler ttsHandler;
    public AccessibilityHandler(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        isAccessibilityEnabled = accessibilityManager.isEnabled();
        if (!isAccessibilityEnabled) {
            ttsHandler = new TTSHandler(context);
        }
    }

    public void announce(String s) {
        if (!isAccessibilityEnabled) {
            ttsHandler.speakPhrase(s);
        }
    }
}