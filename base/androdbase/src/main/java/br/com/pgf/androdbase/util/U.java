package br.com.pgf.androdbase.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by peterson on 23/08/2016.
 */
public final class U {


   public static int dpToPx(int dp) {
      return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
   }

   public static int getScreenHeight(final @NonNull Context context) {
      final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
      final Display display = wm.getDefaultDisplay();
      final Point size = new Point();
      display.getSize(size);
      final int screenHeight = size.y;
      return screenHeight;
   }

   public static int getScreenWidth(final @NonNull Context context) {
      final WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
      final Display display = wm.getDefaultDisplay();
      final Point size = new Point();
      display.getSize(size);
      final int screenWidth = size.x;
      return screenWidth;
   }

   public static boolean isAndroid5() {
      return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
   }

}
