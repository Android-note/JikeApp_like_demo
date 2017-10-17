package com.yy.jike;

/**
 * @author yangyi 2017年10月17日11:14:15
 */

public class ButtonUtil {
    private static long lastClickTime = 0;
    private static final long DIFF = 1000;
    private static int lastButtonId = -1;

    public static boolean isFastDoubleClick() {
        return isFastDoubleClick(-1, DIFF);
    }

    public static boolean isFastDoubleClick(int buttonId) {
        return isFastDoubleClick(buttonId, DIFF);
    }

    public static boolean isFastDoubleClick(int buttonId, long diff) {
        long time = System.currentTimeMillis();
        long timeDiff = time - lastClickTime;
        if (lastButtonId == buttonId && lastClickTime > 0 && timeDiff < diff) {
            return true;
        }
        lastClickTime = time;
        lastButtonId = buttonId;
        return false;
    }

}
