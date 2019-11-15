//
// Created by huzongyao on 2019/11/15.
//

#include "Seeta2Api.h"
#include "Seeta2Detector.h"
#include "NdkUtils.h"


JNIEXPORT jlong JNICALL
SEETA_JNI(nInit)(JNIEnv *env, jclass type, jstring fdPath_) {
    const char *fdPath = env->GetStringUTFChars(fdPath_, nullptr);
    auto *detector = new Seeta2Detector(fdPath);
    env->ReleaseStringUTFChars(fdPath_, fdPath);
    return (jlong) detector;
}

JNIEXPORT jlong JNICALL
SEETA_JNI(nInitMark)(JNIEnv *env, jclass type, jstring fdPath_, jstring pdPath_) {
    const char *fdPath = env->GetStringUTFChars(fdPath_, nullptr);
    const char *pdPath = env->GetStringUTFChars(pdPath_, nullptr);
    auto *detector = new Seeta2Detector(fdPath, pdPath);
    env->ReleaseStringUTFChars(fdPath_, fdPath);
    env->ReleaseStringUTFChars(pdPath_, pdPath);
    return (jlong) detector;
}

JNIEXPORT jintArray JNICALL
SEETA_JNI(nDetect)(JNIEnv *env, jclass type, jlong instance, jobject bitmap) {
    if (instance) {
        auto *detector = (Seeta2Detector *) instance;
        AndroidBitmapInfo info;
        void *pixels = lockAndroidBitmap(env, bitmap, info);
        assert(info.format == ANDROID_BITMAP_FORMAT_RGBA_8888);
        assert(pixels != nullptr);
        void *rgb = rgba8888Torgb888(pixels, info.width, info.height);
        AndroidBitmap_unlockPixels(env, bitmap);
        SeetaImageData input = SeetaImageData();
        input.width = info.width;
        input.height = info.height;
        input.channels = 3;
        input.data = (unsigned char *) rgb;
        auto result = detector->detect(input);
        LOGI("Seeta Detected: [%d]", result.size);
        free(rgb);
        return seetaFaces2AIntArray(env, result);
    }
    return env->NewIntArray(0);
}

JNIEXPORT jfloatArray JNICALL
SEETA_JNI(nMark)(JNIEnv *env, jclass type, jlong instance, jobject bitmap) {
    if (instance) {
        auto *detector = (Seeta2Detector *) instance;
        AndroidBitmapInfo info;
        void *pixels = lockAndroidBitmap(env, bitmap, info);
        assert(info.format == ANDROID_BITMAP_FORMAT_RGBA_8888);
        assert(pixels != nullptr);
        void *rgb = rgba8888Torgb888(pixels, info.width, info.height);
        AndroidBitmap_unlockPixels(env, bitmap);
        SeetaImageData input = SeetaImageData();
        input.width = info.width;
        input.height = info.height;
        input.channels = 3;
        input.data = (unsigned char *) rgb;
        auto result = detector->mark81(input);
        free(rgb);
        return seetaPoints2AFloats(env, result);
    }
    return env->NewFloatArray(0);
}