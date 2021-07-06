#include "com_java_crypto_jni_HillJni.h"
#include <stdio.h>


JNIEXPORT jobjectArray JNICALL Java_com_java_crypto_jni_HillJni_multiplyMatrices
  (JNIEnv * env , jclass jcls, jobjectArray A, jobjectArray B, jint modulo) {
    
    //create resultMatrix
    jint sizeA = (*env)->GetArrayLength(env, A);
    jint sizeB = (*env)->GetArrayLength(env, B);
    
    jclass intArrCls = (*env)->FindClass(env, "[I");
    jobjectArray resultMatrix = (*env)->NewObjectArray(env, sizeA, intArrCls, NULL);
    
    jobjectArray firstRow = (jobjectArray)((*env)->GetObjectArrayElement(env, B, 0));
    int sizeColumnsB = (*env)->GetArrayLength(env, firstRow);
    
    jobjectArray firstRowA = (jobjectArray)((*env)->GetObjectArrayElement(env, A, 0));
    int sizeColumnsA = (*env)->GetArrayLength(env, firstRowA);
    
    //values of matrix B
    jint** b = malloc(sizeB * sizeof(jint*));
    jintArray* bRows = malloc(sizeColumnsB * sizeof(jintArray*)); 
    for (int k = 0; k < sizeB; k++) {
        bRows[k] = (*env)->GetObjectArrayElement(env, B, k);
        b[k] = (*env)->GetIntArrayElements(env, bRows[k], NULL);
     }
    
    //values of matrix A
    jint** a = malloc(sizeA * sizeof(jint*));
    jintArray* aRows = malloc(sizeColumnsA * sizeof(jintArray*)); 
    for (int k = 0; k < sizeA; k++) {
        aRows[k] = (*env)->GetObjectArrayElement(env, A, k);
        a[k] = (*env)->GetIntArrayElements(env, aRows[k], NULL);
     }
    
    
    jint* resultRow = (jint*)malloc(sizeColumnsB * sizeof(jint));
    
    for(int i = 0; i < sizeA; ++i) {        
        for(int j = 0; j < sizeColumnsB; j++) {
            resultRow[j] = 0;
            
            for (int k = 0; k < sizeB; k++) {
                resultRow[j] += a[i][k] * b[k][j];                 
            }
            resultRow[j] %= modulo; 
        }
        
        jintArray res_row = (*env)->NewIntArray(env, sizeColumnsB);
        (*env)->SetIntArrayRegion(env, res_row, 0, sizeColumnsB, resultRow);
        (*env)->SetObjectArrayElement(env, resultMatrix, i, res_row);        
    }

    return resultMatrix;
 }
