package home.henry.math.questions;

public interface Question {
    public void nextQuestion();

    public String getQuestionNumber();

    public String getQuestion();

    public int getQuestionNum();

    public boolean checkAnswer(int userAnswer);

}