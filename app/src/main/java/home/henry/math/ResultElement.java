package home.henry.math;

import java.util.Date;


public class ResultElement {
    String profile;
    String questionType;
    Date date;
    double performanceInSec;

    public ResultElement() {

    }

    public ResultElement(String profile, String questionType, Date date, double performanceInSec) {
        this.profile = profile;
        this.questionType=questionType;
        this.date = date;
        this.performanceInSec = performanceInSec;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPerformanceInSec(double performanceInSec) {
        this.performanceInSec = performanceInSec;
    }

    public String getProfile() {
        return profile;
    }

    public Date getDate() {
        return date;
    }

    public double getPerformanceInSec() {
        return performanceInSec;
    }

    @Override
    public String   toString() {
        return "ResultElement{" +
                "profile='" + profile + '\'' +
                ", questionType='" + questionType + '\'' +
                ", date=" + date +
                ", performanceInSec=" + performanceInSec +
                '}';
    }
}
