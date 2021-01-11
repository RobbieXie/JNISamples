# JNI SAMPLES 

参考文献： https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaNativeInterface.html

## 目录
 + 1_java_call_c  (java调用c方法)
 + 2_java_call_c++  (java调用c++方法)
 + 3_passing_args_results  (参数和结果的传递)
 + 4_modify_instance_variable  (修改instance对象变量)
 + 5_callback_java (c回调java方法)
 + 6_c中创建java对象（doing...）
 + 7_c中创建全局引用缓存（doing...）
 
## 基本流程
 1. java类中声明native方法
 2. javac -h 生成class及对应.h头文件
 3. 编写对应的.c实现相关逻辑（通常为对已有c的库做java适配）
 4. 编译c，生成动态链接库
 5. java执行(引入动态链接库)

## JNI常用方法

### JNI String方法
```cpp
const char * GetStringUTFChars(JNIEnv *env, jstring string, jboolean *isCopy);
   // 返回UTF8编码String的char*数组
void ReleaseStringUTFChars(JNIEnv *env, jstring string, const char *utf);
   // 通知VM native不再需要string了 
jstring NewStringUTF(JNIEnv *env, const char *bytes);
   // 返回一个新的UTF String对象
jsize GetStringUTFLength(JNIEnv *env, jstring string);
   // 返回UTF8 编码的String长度
void GetStringUTFRegion(JNIEnv *env, jstring str, jsize start, jsize length, char *buf);
   // 获取部分长度并copy到新数组
  
// UTF8 换成了 Unicode
const jchar * GetStringChars(JNIEnv *env, jstring string, jboolean *isCopy);
void ReleaseStringChars(JNIEnv *env, jstring string, const jchar *chars);
jstring NewString(JNIEnv *env, const jchar *unicodeChars, jsize length);
jsize GetStringLength(JNIEnv *env, jstring string);
void GetStringRegion(JNIEnv *env, jstring str, jsize start, jsize length, jchar *buf);

```

### JNI Primitive Array方法
```cpp
// ArrayType: jintArray, jbyteArray, jshortArray, jlongArray, jfloatArray, jdoubleArray, jcharArray, jbooleanArray
// PrimitiveType: int, byte, short, long, float, double, char, boolean
// NativeType: jint, jbyte, jshort, jlong, jfloat, jdouble, jchar, jboolean
NativeType * Get<PrimitiveType>ArrayElements(JNIEnv *env, ArrayType array, jboolean *isCopy);
void Release<PrimitiveType>ArrayElements(JNIEnv *env, ArrayType array, NativeType *elems, jint mode);
void Get<PrimitiveType>ArrayRegion(JNIEnv *env, ArrayType array, jsize start, jsize length, NativeType *buffer);
void Set<PrimitiveType>ArrayRegion(JNIEnv *env, ArrayType array, jsize start, jsize length, const NativeType *buffer);
ArrayType New<PrimitiveType>Array(JNIEnv *env, jsize length);
void * GetPrimitiveArrayCritical(JNIEnv *env, jarray array, jboolean *isCopy);
void ReleasePrimitiveArrayCritical(JNIEnv *env, jarray array, void *carray, jint mode);
```

### JNI instance or static 参数访问
```cpp
jclass GetObjectClass(JNIEnv *env, jobject obj);
   // 返回对象的class.
   
jfieldID GetFieldID(JNIEnv *env, jclass cls, const char *name, const char *sig);
   // 返回fieldId
 
NativeType Get<type>Field(JNIEnv *env, jobject obj, jfieldID fieldID);
void Set<type>Field(JNIEnv *env, jobject obj, jfieldID fieldID, NativeType value);
   // intance field值的 get set
   

// static Field
jfieldID GetStaticFieldID(JNIEnv *env, jclass cls, const char *name, const char *sig);
NativeType GetStatic<type>Field(JNIEnv *env, jclass clazz, jfieldID fieldID);
void SetStatic<type>Field(JNIEnv *env, jclass clazz, jfieldID fieldID, NativeType value);
```