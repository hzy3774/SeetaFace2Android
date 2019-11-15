//
// Created by huzongyao on 2019/11/15.
//

#include <malloc.h>
#include <cstring>
#include "NdkUtils.h"

void *lockAndroidBitmap(JNIEnv *env, jobject bitmap, AndroidBitmapInfo &info) {
    if (AndroidBitmap_getInfo(env, bitmap, &info) < 0) {
        LOGE("AndroidBitmap_getInfo Error!");
        return nullptr;
    }
    void *pixels = nullptr;
    if (AndroidBitmap_lockPixels(env, bitmap, &pixels) < 0) {
        LOGE("AndroidBitmap_lockPixels Error!");
        return nullptr;
    }
    return pixels;
}

void *rgba8888Torgb888(void *rgba, int width, int height) {
    long count = width * height;
    void *rgb = malloc((size_t) (count * 3));
    for (long i = 0; i < count; ++i) {
        memcpy((char *) rgb + 3 * i, (char *) rgba + 4 * i, 3);
    }
    return rgb;
}

jintArray seetaFaces2AIntArray(JNIEnv *env, SeetaFaceInfoArray &rects) {
    auto ret = env->NewIntArray(rects.size * 4);
    auto buffer = new int[rects.size * 4];
    for (int i = 0; i < rects.size; ++i) {
        auto &item = rects.data[i];
        auto rect = item.pos;
        buffer[2 * i] = rect.x;
        buffer[2 * i + 1] = rect.y;
        buffer[2 * i + 2] = rect.x + rect.width;
        buffer[2 * i + 3] = rect.y + rect.height;
    }
    env->SetIntArrayRegion(ret, 0, rects.size * 4, buffer);
    delete[]buffer;
    return ret;
}

jfloatArray seetaPoints2AFloats(JNIEnv *env, const std::vector<SeetaPointF> &points) {
    const auto count = (jsize) points.size();
    jfloatArray ret = env->NewFloatArray(count * 2);
    auto buffer = new float[count * 2];
    for (size_t i = 0; i < count; ++i) {
        const SeetaPointF &point = points[i];
        buffer[2 * i] = (float) point.x;
        buffer[2 * i + 1] = (float) point.y;
    }
    env->SetFloatArrayRegion(ret, 0, count * 2, buffer);
    delete[]buffer;
    return ret;
}