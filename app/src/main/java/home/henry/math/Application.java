package home.henry.math;

import java.util.Date;
import java.util.Scanner;

/**
 * Created by Henry on 27/11/2017.
 */
public class Application {
    public static void main(String[] args) throws Exception {
        String user = args[0];
        ResultStore resultStore = new ResultStore();
        System.out.println("========================= Math Game =========================");
        if (resultStore.isNewPlayer(user)) {
            showGreeting(user);
        } else {
            resultStore.showReport(user);
        }
        getReady();
        Timer.startTimer();
        performTest();
        Timer.stopTimer();
        Timer.showElapsedTime();
        System.out.println("========================== The End ==========================");
        ResultElement resultElement = new ResultElement(user, new Date(), (int) Timer.getElapsedTime());
        resultStore.addResult(resultElement);
        resultStore.showReport(resultElement);

    }

    static void showGreeting(String user) {
        System.out.println("Hello, " + user + "!!!");
        System.out.println("I will help you to improve your speed of calculation");
        System.out.println("You will be asked to answer 20 Questions.");
        System.out.println("The elapsed time will tell how good you are in the test.");
    }

    static void getReady() {
        System.out.println("Are you ready?");
        System.out.println("Press <Enter> to start...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    static void performTest() {


        for (int i = 1; i <= 20; ++i) {
            AdditionQuestion additionQuestion = new AdditionQuestion(i);
            additionQuestion.showQuestion();
            additionQuestion.waitUntilCorrect();
        }
    }
}
