package com.hzy.face.seeta2;

import android.graphics.PointF;
import android.graphics.Rect;

public class Seeta2Utils {

    /**
     * construct RectFs by float array
     *
     * @param ints [r1.left, r1.top, r1.right, r1.bottom, ...]
     * @return RectFs [r1, r2, r3...]
     */
    public static Rect[] intArray2RectArray(int[] ints) {
        int rectCount = ints.length / 4;
        Rect[] ret = new Rect[rectCount];
        for (int i = 0; i < rectCount; i++) {
            Rect rect = new Rect(ints[i * 4], ints[i * 4 + 1],
                    ints[i * 4 + 2], ints[i * 4 + 3]);
            ret[i] = rect;
        }
        return ret;
    }

    /**
     * construct PointF array by floats
     *
     * @param floats [p1.x, p1.y, p2.x, p2.y, ...]
     * @return PointF [p1, p2, p3, ...]
     */
    public static PointF[] floatArray2PointFArray(float[] floats) {
        int pointLength = floats.length / 2;
        PointF[] points = new PointF[pointLength];
        for (int i = 0; i < pointLength; i++) {
            PointF p = new PointF(floats[i * 2], floats[i * 2 + 1]);
            points[i] = p;
        }
        return points;
    }
}
