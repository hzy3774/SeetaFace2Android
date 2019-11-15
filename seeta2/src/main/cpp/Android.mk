LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)


LOCAL_MODULE := seeta2

SEETA2_SOURCE := \
    $(wildcard $(LOCAL_PATH)/seeta2/*.cpp) \
    $(wildcard $(LOCAL_PATH)/seeta2/SeetaNet/src/*.cpp) \
    $(wildcard $(LOCAL_PATH)/seeta2/SeetaNet/src/orz/mem/*.cpp) \
    $(wildcard $(LOCAL_PATH)/seeta2/SeetaNet/src/orz/sync/*.cpp) \
    $(wildcard $(LOCAL_PATH)/seeta2/SeetaNet/src/orz/tools/*.cpp) \
    $(wildcard $(LOCAL_PATH)/seeta2/FaceDetector/src/*.cpp) \

LOCAL_SRC_FILES += $(SEETA2_SOURCE)

LOCAL_C_INCLUDES += $(LOCAL_PATH)/seeta2/SeetaNet/include
LOCAL_C_INCLUDES += $(LOCAL_PATH)/seeta2/SeetaNet/src
LOCAL_C_INCLUDES += $(LOCAL_PATH)/seeta2/SeetaNet/src/include_inner
LOCAL_C_INCLUDES += $(LOCAL_PATH)/seeta2/SeetaNet/src/include_inner/layers
LOCAL_C_INCLUDES += $(LOCAL_PATH)/seeta2/FaceDetector/include

LOCAL_CXXFLAGS += -w -std=c++11 -frtti -fexceptions -ffunction-sections -fdata-sections
LOCAL_LDFLAGS += -Wl,--gc-sections -llog -ljnigraphics
LOCAL_ARM_NEON  := true

include $(BUILD_SHARED_LIBRARY)