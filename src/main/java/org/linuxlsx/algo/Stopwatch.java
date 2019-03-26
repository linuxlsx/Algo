package org.linuxlsx.algo;


public class Stopwatch {

    private long currentMillis = 0;

    public Stopwatch(){
        currentMillis = System.currentTimeMillis();
    }

    public long elapsedMillis(){
        long cm = System.currentTimeMillis();
        return cm - currentMillis;
    }

    public long elapsedSecond(){
        long cm = System.currentTimeMillis();

        return (cm - currentMillis) / 1000;
    }
}
