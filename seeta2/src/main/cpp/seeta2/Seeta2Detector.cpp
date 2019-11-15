//
// Created by huzongyao on 2019/11/15.
//

#include "Seeta2Detector.h"

using namespace seeta;

Seeta2Detector::Seeta2Detector(const char *fdPath) {
    ModelSetting::Device device = ModelSetting::CPU;
    int id = 0;
    ModelSetting FD_model(fdPath, device, id);
    FD = new FaceDetector(FD_model);
    FD->set(FaceDetector::PROPERTY_VIDEO_STABLE, 1);
    FD->set(FaceDetector::PROPERTY_THRESHOLD1, 0.65f);
}

Seeta2Detector::Seeta2Detector(const char *fdPath, const char *pdPath) {
    ModelSetting::Device device = ModelSetting::CPU;
    int id = 0;
    ModelSetting FD_model(fdPath, device, id);
    ModelSetting FL_model(pdPath, device, id);
    FD = new FaceDetector(FD_model);
    FL = new FaceLandmarker(FL_model);
    FD->set(FaceDetector::PROPERTY_VIDEO_STABLE, 1);
    FD->set(FaceDetector::PROPERTY_THRESHOLD1, 0.65f);
}

Seeta2Detector::~Seeta2Detector() {
    if (FD != nullptr) {
        delete FD;
        FD = nullptr;
    }
    if (FL != nullptr) {
        delete FL;
        FL = nullptr;
    }
}

SeetaFaceInfoArray Seeta2Detector::detect(SeetaImageData &image) {
    return FD->detect(image);
}

std::vector<SeetaPointF> Seeta2Detector::mark81(SeetaImageData &image) {
    auto faces = FD->detect(image);
    std::vector<SeetaPointF> points;
    if (faces.size > 0 && FL != nullptr) {
        auto face = faces.data[0];
        points = FL->mark(image, face.pos);
    }
    return points;
}
