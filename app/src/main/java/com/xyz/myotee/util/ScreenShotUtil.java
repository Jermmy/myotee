package com.xyz.myotee.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xyz on 16/7/4.
 */
public class ScreenShotUtil {
    public static final String TAG = "ScreenShotUtil";

    public static Bitmap getScreenShot(View v, Rect rect) {
        v.buildDrawingCache();

        //v.getWindowVisibleDisplayFrame(rect);
        Log.i(TAG, "rect=========>" + rect + "\nwidth: " + rect.width() + "  height: " + rect.height());
        Log.i(TAG, "getScreenShot=====>width: " + v.getDrawingCache().getWidth() +
             "   height: " + v.getDrawingCache().getHeight());
        Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache(),
                rect.left, rect.top, rect.width(), rect.height());
        v.destroyDrawingCache();
        return bmp;
    }

    public static void saveScreenShot(View v, Rect rect, String filePath, Bitmap.CompressFormat format) throws IOException {
        Bitmap bitmap = getScreenShot(v, rect);
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        if (fileOutputStream != null) {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        }

    }

}
