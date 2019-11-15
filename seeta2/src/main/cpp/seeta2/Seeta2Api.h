//
// Created by huzongyao on 2019/11/15.
//

#ifndef SEETAFACE2ANDROID_SEETA2API_H
#define SEETAFACE2ANDROID_SEETA2API_H

#define SEETA_JNI(x) Java_com_hzy_face_seeta2_Seeta2Detector_##x

#ifdef __cplusplus
extern "C" {
#endif

#include <jni.h>

JNIEXPORT jlong JNICALL
SEETA_JNI(nInit)(JNIEnv *env, jclass type, jstring fdPath_);

JNIEXPORT jlong JNICALL
SEETA_JNI(nInitMark)(JNIEnv *env, jclass type, jstring fdPath_, jstring pdPath_);

JNIEXPORT jintArray JNICALL
SEETA_JNI(nDetect)(JNIEnv *env, jclass type, jlong instance, jobject bitmap);

JNIEXPORT jfloatArray JNICALL
SEETA_JNI(nMark)(JNIEnv *env, jclass type, jlong instance, jobject bitmap);

#ifdef __cplusplus
}
#endif

#endif //SEETAFACE2ANDROID_SEETA2API_H
