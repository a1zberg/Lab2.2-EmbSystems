package com.company;

public class Harmonic {
    public int n,W,N;
    private double A,fi;
    public double Mx,Dx;
    public double[]x;
    public Harmonic(int n, int W, int N,double A, double fi){
        this.n = n;
        this.W = W;
        this.N = N;
        this.A = A;
        this.fi = fi;
    }
    public void RandomSignal(){
        x = new double[N];
        double Wp = W/n;
        for(int t = 0; t < N; t++){
            for(int i = 0; i < n; i++){
                x[t] += A * Math.sin(Wp * t + fi);
                Wp += W/n;
            }
        }
    }

    public void mathExpectation(){
        for(int i = 0; i < N; i++){
            Mx += x[i];
        }
        Mx = Mx/N;
    }

    public void dispersion(){
        for(int i = 0; i < N; i++){
            Dx += Math.pow((x[i] - Mx), 2);
        }
        Dx = Dx/(N-1);
    }
}
