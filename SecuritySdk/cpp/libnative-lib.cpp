#include <jni.h>
#include <string>

std::string getData() { // Some function to get the key depends on the parameter
//    std::string app_secret = "vVzFYGLALAyMmWecJ91unlqOcMzL0KL3";
    std::string app_secret = "OsuSJiUl/oGzzbejPJlUpOyq5CTOKblJClWfYfQTeOSYk40lZWnzPChmZwD2/Uvg";

    return app_secret;
}

extern "C" jstring
Java_com_example_securitysdk_security_EncryptionManager_getApiKey(
        JNIEnv *env,
        jobject /* this */
) {
    std::string app_secret = "Null";
    app_secret = getData();
    return env->NewStringUTF(app_secret.c_str());
}