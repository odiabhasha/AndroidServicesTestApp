// ICommon.aidl
package com.pp.androidservices;

// Declare any non-default types here with import statements

interface ICommon {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int calc(int num1,int num2);

    String getMessage();
}
