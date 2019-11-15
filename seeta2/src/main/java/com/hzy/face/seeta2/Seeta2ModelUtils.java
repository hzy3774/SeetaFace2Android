package com.hzy.face.seeta2;

import android.content.Context;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Seeta2ModelUtils {

    public static final String SEETA_DIR = "seeta";
    public static final String SEETA_FACE_DATA = "fd_2_00.dat";
    public static final String SEETA_POINTS_DATA = "pd_2_00_pts81.dat";
    public static String DETECTOR_PATH = "";
    public static String LANDMARKER_PATH = "";

    public static boolean hasDetector() {
        return new File(DETECTOR_PATH).exists();
    }

    public static boolean hasLandmarker() {
        return new File(LANDMARKER_PATH).exists();
    }

    public static String initModels(Context context) {
        try {
            String[] fileNames = context.getAssets().list(SEETA_DIR);
            File modelDir = new File(context.getFilesDir(), SEETA_DIR);
            if (modelDir.exists() ? modelDir.isDirectory() : modelDir.mkdirs()) {
                if (fileNames != null && fileNames.length > 0) {
                    byte[] buffer = null;
                    for (String fileName : fileNames) {
                        File outFile = new File(modelDir, fileName);
                        if (!outFile.exists()) {
                            InputStream is = context.getAssets()
                                    .open(SEETA_DIR + File.separator + fileName);
                            OutputStream os = new BufferedOutputStream(new FileOutputStream(outFile));
                            if (buffer == null) buffer = new byte[1000000];
                            for (int len; (len = is.read(buffer)) != -1; ) {
                                os.write(buffer, 0, len);
                            }
                            is.close();
                            os.close();
                        }
                    }
                }
            }
            DETECTOR_PATH = new File(modelDir, SEETA_FACE_DATA).getPath();
            LANDMARKER_PATH = new File(modelDir, SEETA_POINTS_DATA).getPath();
            return modelDir.getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
