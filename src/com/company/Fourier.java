package com.company;

public class Fourier {
    static public double[] Transform(int N, double[] x){
        double FRe = 0;
        double FIm = 0;
        double[] Fp = new double[N];
        /////////////////////////////////////1
        double[][] Wn = new double[N][N];
        for(int p = 0; p < N; p++){
            for(int m = 0; m < N; m++){
                Wn[p][m] =((2 * Math.PI / N) * p * m);
            }
        }
        for(int p = 0; p < N; p++){
            FRe = 0;
            FIm = 0;
            for(int m = 0; m < N; m++){
                FRe += x[m] * Math.cos(Wn[p][m]);
                FIm += x[m] * (-Math.sin(Wn[p][m]));
            }
            Fp[p] = Math.abs(Math.sqrt(Math.pow(FRe, 2) + Math.pow(FIm, 2)));
        }


        ///////////////////////////////////2
//        double F1re = 0;
//        double F2re = 0;
//        double F1im = 0;
//        double F2im = 0;
//        double[] Fre = new double[N];
//        double[] Fim = new double[N];
//        for (int p = 0; p < N; p++) {
//            for (int m = 0; m < (N / 2 - 1); m++) {
//                F1re += x[2*m+1] * Math.cos(((2 * Math.PI) / (N / 2)) * p * m);
//                F1im += x[2*m+1] * Math.sin(((2 * Math.PI) / (N / 2)) * p * m);
//                F2re += x[2*m] * Math.cos(((2 * Math.PI) / (N / 2)) * p * m);
//                F2im += x[2*m] * Math.sin(((2 * Math.PI) / (N / 2)) * p * m);
//            }
//            Fre[p] = F1re * Math.cos(((2 * Math.PI) / (N / 2)) * p) * F2re;
//            Fim[p] = F1im * Math.sin(((2 * Math.PI) / (N / 2)) * p) * F2im;
//            Fp[p] = Fre[p] - Fim[p];
//        }
////////////////////////////////////////////////////3

//        double[] FreArr = new double[N];
//        double[] FimArr = new double[N];
//        for (int n = 0; n < N; n++) {
//            for (int k = 0; k < N; k++) {
//                FRe += x[k] * Math.cos((2 * Math.PI/ N) * n * k);
//                FIm += x[k] * Math.sin((2 * Math.PI/ N) * n * k);
//            }
//            FreArr[n] = 2*FRe / N;
//            FimArr[n] = -2 * FIm / N;
//            Fp[n] += (FreArr[n]* Math.cos((2 * Math.PI/ N) * n)) - (FimArr[n] * Math.sin((2 * Math.PI/ N) * n));
//        }
        return Fp;
    }
}
