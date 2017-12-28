package home.henry.math;


public class Config {
    static int NUM_RANGE_FROM;
    static int NUM_RANGE_TO;
    static int NUM_OF_QUESTIONS=20;
    static String PROFILE="Guest";

    public static int getNumOfQuestions() {
        return NUM_OF_QUESTIONS;
    }

    public static void setNumOfQuestions(int numOfQuestions) {
        NUM_OF_QUESTIONS = numOfQuestions;
    }

    public static String getPROFILE() {
        return PROFILE;
    }

    public static void setPROFILE(String PROFILE) {
        Config.PROFILE = PROFILE;
    }

    public static int getNumRangeFrom() {
        return NUM_RANGE_FROM;
    }

    public static void setNumRangeFrom(int numRangeFrom) {
        NUM_RANGE_FROM = numRangeFrom;
    }

    public static int getNumRangeTo() {
        return NUM_RANGE_TO;
    }

    public static void setNumRangeTo(int numRangeTo) {
        NUM_RANGE_TO = numRangeTo;

    }



}
