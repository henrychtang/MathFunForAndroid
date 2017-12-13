package home.henry.math;


import java.util.Date;

/**
 * Created by Henry on 27/11/2017.
 */
public class Timer {
    static long startTime;
    static long endTime;

    static public void startTimer() {
        Date date = new Date();
        startTime = date.getTime();
    }

    static public void stopTimer() {
        Date date = new Date();
        endTime = date.getTime();
    }

    static void showElapsedTime() {
        System.out.println("You have finished this test in " + (endTime - startTime)/1000 + " sec");
    }
    static long getElapsedTime(){
        return (endTime-startTime)/1000;
    }
}
