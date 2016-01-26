#include <jni.h>
#include "handleUinput.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <errno.h>
#include <linux/input.h>
//#include <linux/uinput.h>
#include<android/log.h>

#define TAG "myDemo-jni" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型

JNIEXPORT void JNICALL Java_com_example_handleuinput_MainActivity_open(
		JNIEnv* env, jobject thiz) {
	int fd_kb, aux;
	char *szDev = "dev/input/event0";
	LOGD("start jni input open!");
	fd_kb = open(szDev, O_WRONLY | O_NONBLOCK);
	if (fd_kb < 0) {
		LOGD("Can't open input device:%s \n", szDev);
		return;
	}
	LOGD("open input device success!, fd_kb: %d, ", fd_kb);
}

