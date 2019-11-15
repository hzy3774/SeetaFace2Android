package com.hzy.face.seeta2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.graphics.Rect;

public enum Seeta2Detector {
    INSTANCE;

    private volatile long mInstanceId;
    private boolean mHasDetector;
    private boolean mHasMarker;

    Seeta2Detector() {
    }

    public synchronized void init(Context context) {
        if (mInstanceId == 0) {
            Seeta2ModelUtils.initModels(context);
            mHasDetector = Seeta2ModelUtils.hasDetector();
            mHasMarker = Seeta2ModelUtils.hasLandmarker();
            if (mHasDetector) {
                if (mHasMarker) {
                    mInstanceId = nInitMark(Seeta2ModelUtils.DETECTOR_PATH,
                            Seeta2ModelUtils.LANDMARKER_PATH);
                } else {
                    mInstanceId = nInit(Seeta2ModelUtils.DETECTOR_PATH);
                }
            }
        }
    }

    public Rect[] detect(Bitmap bitmap) {
        if (mInstanceId != 0) {
            int[] ret = nDetect(mInstanceId, bitmap);
            return Seeta2Utils.intArray2RectArray(ret);
        }
        return new Rect[0];
    }

    public PointF[] mark81(Bitmap bitmap) {
        if (mInstanceId != 0 && mHasMarker) {
            float[] result = nMark(mInstanceId, bitmap);
            return Seeta2Utils.floatArray2PointFArray(result);
        }
        return new PointF[0];
    }

    public synchronized void destory() {
        if (mInstanceId != 0) {
            mInstanceId = 0;
        }
    }

    private static native float[] nMark(long instance, Bitmap bitmap);

    private static native int[] nDetect(long instance, Bitmap bitmap);

    private static native long nInit(String fdPath);

    private static native long nInitMark(String fdPath, String pdPath);

    static {
        System.loadLibrary("seeta2");
    }
}
