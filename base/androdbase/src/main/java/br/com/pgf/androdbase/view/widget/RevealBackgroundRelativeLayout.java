package br.com.pgf.androdbase.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import br.com.pgf.androdbase.R;


/**
 * Created by peterson on 24/08/2016.
 */
public class RevealBackgroundRelativeLayout extends RelativeLayout {
   private Path mRevealPath;
   boolean mClipOutlines;
   float mCenterX;
   float mCenterY;
   float mRadius;
   Paint fillPaint;

   public RevealBackgroundRelativeLayout(Context context) {
      super(context);
      init(context);
   }

   public RevealBackgroundRelativeLayout(Context context, AttributeSet attrs) {
      super(context, attrs);
      init(context);
   }

   public RevealBackgroundRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
      super(context, attrs, defStyleAttr);
      init(context);
   }

   private void init(final Context context) {
      fillPaint = new Paint();
      fillPaint.setStyle(Paint.Style.FILL);
      fillPaint.setColor(Color.WHITE);
      mRevealPath = new Path();
      mClipOutlines = false;
      setWillNotDraw(false);
   }

   public void setClipOutLines(boolean shouldClip) {
      mClipOutlines = shouldClip;
   }

   public void setClipCenter(final int x, final int y) {
      mCenterX = x;
      mCenterY = y;
   }

   public void setClipRadius(final float radius) {
      mRadius = radius;
      invalidate();
   }

   @Override
   public void draw(Canvas canvas) {
      if (!mClipOutlines) {
         super.draw(canvas);
         return;
      }
      final int state = canvas.save();
      mRevealPath.reset();
      mRevealPath.addCircle(mCenterX, mCenterY, mRadius, Path.Direction.CW);
      canvas.clipPath(mRevealPath);
      super.draw(canvas);
      canvas.restoreToCount(state);
   }
}
