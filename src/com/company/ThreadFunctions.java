package com.company;

import java.util.concurrent.Callable;

public abstract class ThreadFunctions implements Callable<double[]> {
    protected int N;
    protected  double[] x;
    public ThreadFunctions (int N, double[] x){
        this.N = N;
        this.x = x;
    }
}
