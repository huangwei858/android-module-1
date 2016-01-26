LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := handleUinput
LOCAL_SRC_FILES := handleUinput.c
LOCAL_SHARED_LIBRARIES := libutils
LOCAL_LDLIBS := -llog 
include $(BUILD_SHARED_LIBRARY)
