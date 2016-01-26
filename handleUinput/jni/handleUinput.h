/*
 * handleUinput.h
 *
 *  Created on: 2016-1-22
 *      Author: Administrator
 */

#ifndef HANDLEUINPUT_H_
#define HANDLEUINPUT_H_

#include <linux/input.h>
#include <sys/types.h>
#include <linux/types.h>

#ifdef _cplusplus
extern "C" {
#endif

//´ò¿ªº¯Êý
JNIEXPORT void JNICALL Java_com_example_handleuinput_MainActivity_open(
		JNIEnv *, jobject);
/*******************************************************************/

#ifdef _cplusplus
}
#endif

#endif /* HANDLEUINPUT_H_ */
