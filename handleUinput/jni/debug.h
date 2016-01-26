/*
 * debug.h
 *
 *  Created on: 2016-1-22
 *      Author: Administrator
 */

#ifndef DEBUG_H_
#define DEBUG_H_

#include <utils/Log.h>

#ifdef ALOGD
#define LOGD      ALOGD
#endif
#ifdef ALOGV
#define LOGV      ALOGV
#endif
#ifdef ALOGE
#define LOGE      ALOGE
#endif
#ifdef ALOGI
#define LOGI      ALOGI
#endif

#endif /* DEBUG_H_ */
