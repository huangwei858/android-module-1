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

#define TAG "myDemo-jni" // ������Զ����LOG�ı�ʶ
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // ����LOGD����
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // ����LOGI����
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // ����LOGW����
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // ����LOGE����
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // ����LOGF����

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

