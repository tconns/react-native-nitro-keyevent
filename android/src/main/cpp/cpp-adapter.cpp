#include <jni.h>
#include "NitroKeyEventOnLoad.hpp"

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM* vm, void*) {
  return margelo::nitro::nitrokeyevent::initialize(vm);
}
