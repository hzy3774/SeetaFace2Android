//
// Created by huzongyao on 2019/11/15.
//

#ifndef SEETAFACE2ANDROID_SEETA2DETECTOR_H
#define SEETAFACE2ANDROID_SEETA2DETECTOR_H

#include <FaceLandmarker.h>
#include <FaceDetector.h>

class Seeta2Detector {
public:
    Seeta2Detector(const char *fdPath);

    Seeta2Detector(const char *fdPath, const char *pdPath);

    SeetaFaceInfoArray detect(SeetaImageData &image);

    std::vector<SeetaPointF> mark81(SeetaImageData &image);

    virtual ~Seeta2Detector();

private:
    seeta::FaceDetector *FD = nullptr;
    seeta::FaceLandmarker *FL = nullptr;
};


#endif //SEETAFACE2ANDROID_SEETA2DETECTOR_H
