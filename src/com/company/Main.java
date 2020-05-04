package com.company;

import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        final int n = 8;
        final int W = 1100;
        final int N = 25600;
        double A = Math.random()* Math.PI;
        double fi = Math.random()* Math.PI;

        Harmonic harm1 = new Harmonic(n, W, N, A, fi);
        harm1.RandomSignal();
        harm1.mathExpectation();
        harm1.dispersion();

        //creating chart
        double[] xData = new double[N];
        for (int i = 0; i < N; i++) {
            xData[i] = i;
        }
        XYChart chart = new XYChartBuilder().width(1500).height(600).title("x(t)").xAxisTitle("t").yAxisTitle("x").build();
        XYSeries series = chart.addSeries("x(t)", xData, harm1.x);
        double[] mXarr = new double[N];
        double[] dXarr = new double[N];
        for (int i = 0; i < N; i++) {
            mXarr[i] = harm1.Mx;
            dXarr[i] = harm1.Dx;
        }
        chart.addSeries("Math Expectation", xData, mXarr);
        chart.addSeries("Dispersion", xData, dXarr);
        new SwingWrapper(chart).displayChart();

//        double[] Fp = Fourier.Transform(N,harm1.x);
//        XYChart chart2 = new XYChartBuilder().width(1500).height(600).title("Fourier").xAxisTitle("p").yAxisTitle("F").build();
//        XYSeries series2 = chart2.addSeries("F(p)", xData, Fp);
//        new SwingWrapper(chart2).displayChart();
        long start1 = System.currentTimeMillis();
        double[] FFp = FastFourier.FastTransform(N,harm1.x);
        long finish1 = System.currentTimeMillis();
        XYChart chart3 = new XYChartBuilder().width(1500).height(600).title("Fast Fourier").xAxisTitle("p").yAxisTitle("F").build();
        XYSeries series3 = chart3.addSeries("FF(p)", xData, FFp);
        new SwingWrapper(chart3).displayChart();


        //parallel
//        double[] FxPar = new double[N];
//        Thread T1 = new Thread(new F1(N/2, harm1.x, FxPar));
//        Thread T2 = new Thread(new F2(N/2, harm1.x, FxPar));
//
//        T1.start();
//        T2.start();
//
//        System.out.println("res:");
//        for (int i = 0; i < FxPar.length; i++) {
//            System.out.print(FxPar[i] + " ");
//        }
//        T1.join();
//        T2.join();
//

        //Callable, использую его впервые
        Callable<double[]> callable1 = new F1(N,harm1.x);
        Callable<double[]> callable2 = new F2(N,harm1.x);
        FutureTask<double[]> future = new FutureTask<>(callable1);
        FutureTask<double[]> future2 = new FutureTask<>(callable2);
        long start2 = System.currentTimeMillis();
        new Thread(future).start();
        new Thread(future2).start();
        double[] Fre = future.get();
        double[] Fim = future2.get();
        long finish2 = System.currentTimeMillis();
        double[] result = new double[N];
        for (int p = 0; p < N; p++) {
            result[p] = Math.abs(Math.sqrt(Math.pow(Fre[p], 2) + Math.pow(Fim[p], 2)));
        }
//        for (int i = 0; i < result.length; i++) {
//            System.out.print(result[i] + " ");
//        }
        if(Arrays.equals(FFp,result)) System.out.println("\nРезультаты старые и результат с потока одинаковые.");
        System.out.println("Время выполнения старого способа:" + (finish1 - start1));
        System.out.println("Время выполнения способа с потоками:" + (finish2 - start2));
    }
}
