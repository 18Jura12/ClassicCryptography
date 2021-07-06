package com.java.crypto.jni;

public class HillJni {
    public static native int[][] multiplyMatrices(int[][] A, int[][] B, int modulo);
    
    public static void main(String[] args) {
        System.load(System.getProperty("user.dir") + "/src/main/resources/native/libhill.so");
        int[][] numEquivalents = {
            {20, 17},
            {19, 0},
            {14, 10}
        };
        int[][] key = {
            {5, 8, 22},
            {2, 5, 24},
            {10, 20, 17}
        };
        int modulo = 26;
        
        int[][] res = new HillJni().multiplyMatrices(key, numEquivalents, modulo);
        System.out.println(res[0].length);
        
        for(int i = 0; i < res.length; ++i) {
            for(int j = 0; j < res[i].length; ++j) {
                System.out.print(res[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
