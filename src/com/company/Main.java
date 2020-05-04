package com.company;

import org.knowm.xchart.*;
import org.knowm.xchart.style.markers.SeriesMarkers;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        final int n = 8;
        final int W = 1100;
        final int N = 256;
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

        double[] FFp = FastFourier.FastTransform(N,harm1.x);
        XYChart chart3 = new XYChartBuilder().width(1500).height(600).title("Fast Fourier").xAxisTitle("p").yAxisTitle("F").build();
        XYSeries series3 = chart3.addSeries("FF(p)", xData, FFp);
        new SwingWrapper(chart3).displayChart();
    }
}
