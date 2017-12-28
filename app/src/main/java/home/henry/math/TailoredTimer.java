package home.henry.math;


import java.text.DecimalFormat;
import java.util.Date;

public class TailoredTimer {
    static long startTime=0;
    static long endTime=0;

    static public void startTimer() {
        Date date = new Date();
        startTime = date.getTime();
    }

    static public void stopTimer() {
        Date date = new Date();
        endTime = date.getTime();
    }

    static public void showElapsedTime() {
        System.out.println("You have finished this test in " + getElapsedTime() + " sec");
    }

    static public double getElapsedTime() {
        if (endTime==0) {
            return 0;
        }else {
            return (double) (endTime - startTime) / 1000;
        }
    }
}
