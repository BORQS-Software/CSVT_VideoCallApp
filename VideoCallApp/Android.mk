LOCAL_PATH:= $(call my-dir)
VT_TOP:=$(LOCAL_PATH)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, src)

LOCAL_PACKAGE_NAME := VideoCall 
LOCAL_CERTIFICATE := platform

# This will install the file in /system/vendor/ChinaUnicom
LOCAL_MODULE_PATH := $(TARGET_OUT)/vendor/ChinaUnicom/system/app

#LOCAL_PREBUILT_STATIC_JAVA_LIBRARIES := VideoCallApi:jar/javalib.jar

#LOCAL_JAVA_LIBRARIES := VideoCallApi

LOCAL_PROGUARD_FLAGS := -include $(LOCAL_PATH)/proguard.flags
LOCAL_JAVA_LIBRARIES := telephony-common telephony-msim videocallapi

OMS_RESOURCES_LIBRARIES = true

include $(BUILD_PACKAGE)
#include $(BUILD_MULTI_PREBUILT)

# include all tests and jni libraries
include $(call all-makefiles-under,$(LOCAL_PATH))

