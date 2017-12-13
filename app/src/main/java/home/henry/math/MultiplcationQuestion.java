package home.henry.math;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by Henry on 27/11/2017.
 */
public class MultiplcationQuestion {
    int questionNum;
    int num1;
    int num2;
    int answer;

    public MultiplcationQuestion(int questionNum) {
        this.questionNum = questionNum;
        Random random = new Random();
        num1 = random.nextInt(7) + 3;
        num2 = random.nextInt(7) + 3;
        answer = num1 + num2;
    }

    public void waitUntilCorrect() {
        Scanner scan = new Scanner(System.in);
        boolean stay_flag = true;
        while (stay_flag) {
            String inputAnswer = scan.nextLine();
            if ((answer + "").equals(inputAnswer)) {
                System.out.println("Excellent!");
                break;
            } else {
                System.out.println("Try again!");
            }
        }
    }

    public void showQuestion() {
        System.out.println(questionNum + ": " + num1 + " + " + num2 + " = ?");
    }



}
