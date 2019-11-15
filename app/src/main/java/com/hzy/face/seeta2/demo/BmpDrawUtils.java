package com.hzy.face.seeta2.demo;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

public class BmpDrawUtils {

    public static void drawRectsOnBitmap(Bitmap bitmap, Rect[] rects) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
        paint.setColor(Color.MAGENTA);
        for (Rect rect : rects) {
            canvas.drawRect(rect, paint);
        }
    }

    public static void drawPointsOnBitmap(Bitmap bitmap, PointF[] points) {
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.GREEN);
        float pointSize = bitmap.getWidth() / 150.0f;
        for (PointF p : points) {
            canvas.drawCircle(p.x, p.y, pointSize, paint);
        }
    }
}
