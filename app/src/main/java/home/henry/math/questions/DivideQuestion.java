package home.henry.math.questions;

import java.util.Random;

public class DivideQuestion implements Question{
    int questionNum;
    int num1;
    int num2;
    int answer;

    public DivideQuestion(int questionNum) {
        this.questionNum = questionNum;
        Random random = new Random();
        answer = random.nextInt(8) + 2;
        num2 = random.nextInt(8) + 2;
        num1 = answer * num2;
    }

    public void nextQuestion() {
        questionNum = questionNum + 1;
        Random random = new Random();
        answer = random.nextInt(8) + 2;
        num2 = random.nextInt(8) + 2;
        num1 = answer * num2;
    }

    public String getQuestionNumber() {
        return  questionNum+"" ;
    }

    public String getQuestion() {
        return num1 + " / " + num2 + " = ?";
    }

    public int getQuestionNum() {
        return questionNum;
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == answer;
    }

}
