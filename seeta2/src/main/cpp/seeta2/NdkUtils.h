//
// Created by huzongyao on 2019/11/15.
//

#ifndef SEETAFACE2ANDROID_NDKUTILS_H
#define SEETAFACE2ANDROID_NDKUTILS_H

#include <jni.h>
#include <android/bitmap.h>
#include <FaceLandmarker.h>
#include <FaceDetector.h>

#ifdef NDEBUG
#define LOGD(...) do{}while(0)
#define LOGI(...) do{}while(0)
#define LOGW(...) do{}while(0)
#define LOGE(...) do{}while(0)
#define LOGF(...) do{}while(0)
#else
#define LOG_TAG "NATIVE.LOG"

#include <android/log.h>

#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG,__VA_ARGS__)
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,LOG_TAG,__VA_ARGS__)
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,LOG_TAG,__VA_ARGS__)
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,LOG_TAG,__VA_ARGS__)
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,LOG_TAG,__VA_ARGS__)
#endif

void *lockAndroidBitmap(JNIEnv *env, jobject bitmap, AndroidBitmapInfo &info);

/**
 * rgb data need to free by caller
 * @param rgba
 * @param width
 * @param height
 * @return
 */
void *rgba8888Torgb888(void *rgba, int width, int height);

jintArray seetaFaces2AIntArray(JNIEnv *env, SeetaFaceInfoArray &rects);

jfloatArray seetaPoints2AFloats(JNIEnv *env, const std::vector<SeetaPointF> &points);


#endif //SEETAFACE2ANDROID_NDKUTILS_H
