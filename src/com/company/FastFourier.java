package com.company;

public class FastFourier {
    static public double[] FastTransform(int N, double[] x){
        double[] Fx = new double[N];
        double[] Fre = new double[N];
        double[] Fim = new double[N];
        double F1re,F2re,F1im,F2im;
        for (int p = 0; p < N / 2 - 1; p++) {
            F1re = 0;
            F2re = 0;
            F1im = 0;
            F2im = 0;
            //F1 = 0;
            //F2 = 0;
            for (int m = 0; m < N / 2 - 1; m++) {
                F1re += x[2*m+1] * Math.cos(((2 * Math.PI) / (N / 2)) * p * m);
                F1im += x[2*m+1] * Math.sin(((2 * Math.PI) / (N / 2)) * p * m);
                F2re += x[2*m] * Math.cos(((2 * Math.PI) / (N / 2)) * p * m);
                F2im += x[2*m] * Math.sin(((2 * Math.PI) / (N / 2)) * p * m);
            }
            //F1 = Math.abs(Math.sqrt(Math.pow(F1re, 2) + Math.pow(F1im, 2)));
            //F2 = Math.abs(Math.sqrt(Math.pow(F2re, 2) + Math.pow(F2im, 2)));
            Fre[p] = F2re + F1re * Math.cos(((2 * Math.PI) / (N)) * p);
            Fre[p + N / 2] = F2re + F1re * Math.cos(((2 * Math.PI) / (N / 2)) * p);
            Fim[p] = F2im + F1im * Math.sin(((2 * Math.PI) / (N)) * p);
            Fim[p + N / 2] = F2im + F1im * Math.sin(((2 * Math.PI) / (N / 2)) * p);
            //Fre[p+N/2] = -
        }
        for (int p = 0; p < N; p++) {
            Fx[p] = Math.abs(Math.sqrt(Math.pow(Fre[p], 2) + Math.pow(Fim[p], 2)));
        }
        return Fx;
    }
}
