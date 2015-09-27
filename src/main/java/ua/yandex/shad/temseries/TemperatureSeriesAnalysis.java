package ua.yandex.shad.tempseries;

import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {

public static final int MIN_TEMPERATURE = -273;

public static final double frequency = 0.0001;

private double[] arr;

private int size;

private TempSummaryStatistics tempSummaryStatistics;

public TemperatureSeriesAnalysis() {
this.tempSummaryStatistics = new TempSummaryStatistics(0, 0, 0, 0);
}

public TemperatureSeriesAnalysis(double[] temperatureSeries) {
for (double val : temperatureSeries) {
if (val < MIN_TEMPERATURE) {
throw new InputMismatchException();
}
}
this.arr = temperatureSeries;
size = this.arr.length;
double avg = avg();
double dev = dev(avg);
double min = minTemp();
double max = maxTemp();
this.tempSummaryStatistics = 
new TempSummaryStatistics(avg, dev, min, max);
}

private double avg() {
if (arr.length == 0) {
return 0;
}
double avg = 0;
for (double elem : arr) {
avg += elem;
}
avg /= arr.length;
return avg;
}

public double average() {
if (arr.length == 0) {
throw new IllegalArgumentException();
}
return this.tempSummaryStatistics.getAvgTemp();
}

private double dev(double avg) {
double dev = 0;
for (double elem : arr) {
dev += (elem - avg) * (elem - avg);
}
dev = Math.sqrt(dev / arr.length);
return dev;
}

public double deviation() {
if (arr.length == 0) {
throw new IllegalArgumentException();
}
return this.tempSummaryStatistics.getDevTemp();
}

private double minTemp() {
if (arr.length == 0) {
return 0;
}
double min = arr[0];
for (int i = 1; i < arr.length; i++) {
if (arr[i] < min) {
min = arr[i];
}
}
return min;
}

public double min() {
if (arr.length == 0) {
throw new IllegalArgumentException();
}
return this.tempSummaryStatistics.getMinTemp();
}

private double maxTemp() {
if (arr.length == 0) {
return 0;
}
double max = arr[0];
for (int i = 1; i < arr.length; i++) {
if (arr[i] > max) { 
max = arr[i];
}
}
return max;
}

public double max() {
if (arr.length == 0) {
throw new IllegalArgumentException();
}
return this.tempSummaryStatistics.getMaxTemp();
}

private double findClosestTemp(double tempValue) {
if (arr.length == 0) {
throw new IllegalArgumentException();
}
double min = arr[0];
for (int i = 1; i < arr.length; i++) {
double minAbs = Math.abs(min - tempValue);
double valAbs = Math.abs(arr[i] - tempValue);
if (valAbs < minAbs 
|| (Math.abs(minAbs - valAbs) < frequency
&& arr[i] - tempValue > 0)) {
min = arr[i];
}
}
return min;
}

public double findTempClosestToZero() {
return findClosestTemp(0);
}

public double findTempClosestToValue(double tempValue) {
return findClosestTemp(tempValue);
}

private double[] findTempsLessOrGreater(double tempValue, boolean cond) {
if (arr.length == 0) {
throw new IllegalArgumentException();
}
int count = 0;
for (double elem : arr) {
if (cond) {
if (elem < tempValue) {
count++; 
}
}
else {
if (elem > tempValue) {
count++; 
}
}
}
double[] res = new double[count];
count = 0;
for (double elem : arr) {
if (cond) {
if (elem < tempValue) {
res[count++] = elem; 
}
}
else {
if (elem > tempValue) {
res[count++] = elem; 
}
}
}
return res;
}

public double[] findTempsLessThen(double tempValue) {
return findTempsLessOrGreater(tempValue, true);
}

public double[] findTempsGreaterThen(double tempValue) {
return findTempsLessOrGreater(tempValue, false);
}

public TempSummaryStatistics summaryStatistics() {
return this.tempSummaryStatistics;
}

public int addTemps(double ... temps){
int newSize = -1;
if (size + temps.length > arr.length) {
if (2 * arr.length < temps.length) {
newSize = 2 * arr.length;
}
else
newSize = size + temps.length + 10;
}
if (newSize != -1) {
double[] newTemps = new double[newSize];
System.arraycopy(arr, 0, newTemps, 0, size);
System.arraycopy(temps, 0, newTemps, size, temps.length);
size += temps.length;
arr = newTemps;
}
return size;
}

}
