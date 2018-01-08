package home.henry.math.questions;

import java.util.Random;

public class SubtractQuestion implements Question{
    int questionNum;
    int num1;
    int num2;
    int answer;

    public SubtractQuestion(int questionNum) {
        this.questionNum = questionNum;
        Random random = new Random();
        num1 = random.nextInt(15)+5;
        num2 = random.nextInt(num1);
        answer = num1 - num2;
    }

    public void nextQuestion() {
        questionNum = questionNum + 1;
        Random random = new Random();
        num1 = random.nextInt(15)+5;
        num2 = random.nextInt(num1);
        answer = num1 - num2;
    }

    public String getQuestionNumber() {
        return questionNum + "";
    }

    public String getQuestion() {
        return num1 + " - " + num2 + " = ?";
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == answer;
    }

}
