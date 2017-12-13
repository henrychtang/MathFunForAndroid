package home.henry.math;

import java.util.Date;

/**
 * Created by Henry on 28/11/2017.
 */
public class ResultElement {
    String user;
    Date date;
    int performanceInSec;

    public ResultElement(){

    }
    public ResultElement(String user, Date date, int performanceInSec) {
        this.user = user;
        this.date = date;
        this.performanceInSec = performanceInSec;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPerformanceInSec(int performanceInSec) {
        this.performanceInSec = performanceInSec;
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public int getPerformanceInSec() {
        return performanceInSec;
    }

    @Override
    public String toString() {
        return "ResultElement{" +
                "user='" + user + '\'' +
                ", date=" + date +
                ", performanceInSec=" + performanceInSec +
                '}';
    }
}
