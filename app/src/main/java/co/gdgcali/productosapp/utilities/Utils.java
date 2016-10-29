package co.gdgcali.productosapp.utilities;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by krlosf on 28/10/16.
 */

public class Utils {
    public static boolean saveBitmap(Bitmap bitmap, String fileOut) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(fileOut);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);

            return true;
        } catch (Exception e) {
            Log.e("", "", e);
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                Log.e("", "", e);
            }
        }

        return false;
    }
}
