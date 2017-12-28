package home.henry.math;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class ResultStore {
    List<ResultElement> resultList;
    String resultDbFile;
    DecimalFormat df = new DecimalFormat("#.##");
    public ResultStore() {
        if (resultStoreExist()) {
            resultList = readFromResultStore();
            System.out.println("Ready from file...");
        } else {
            System.out.println("Create new resultStore...");
            resultList = createDummyResultList();
            writeToResultStore(resultList);
        }
    }

    public ResultStore(String fullpath) {
        resultDbFile = fullpath;
        if (resultStoreExist()) {
            resultList = readFromResultStore();
            System.out.println("Ready from file...");
        } else {
            System.out.println("Create new resultStore...");
            resultList = createDummyResultList();
            writeToResultStore(resultList);
        }
    }


    public List<ResultElement> createDummyResultList() {
        List resultList = new LinkedList<ResultElement>();
        ResultElement resultElement1 = new ResultElement("Guest", new Date(), 78);
        ResultElement resultElement2 = new ResultElement("Guest", new Date(), 60);
        ResultElement resultElement3 = new ResultElement("Guest", new Date(), 54);
        resultList.add(resultElement1);
        resultList.add(resultElement2);
        resultList.add(resultElement3);
        //    System.out.println(resultList);
        return resultList;
    }

    public void writeToResultStore(List<ResultElement> resultList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(resultDbFile), resultList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<ResultElement> readFromResultStore() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(resultDbFile), new TypeReference<List<ResultElement>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    public void addResult(ResultElement resultElement) {
        resultList.add(resultElement);
        writeToResultStore(resultList);
        System.out.println("Test result persisted");
    }

    boolean resultStoreExist() {
        File file = new File(resultDbFile);
        if (file.exists() && file.isFile()) {
            System.out.println("file exists, and it is a file");
            return true;
        } else {
            System.out.println("File not exist");
            return false;

        }
    }


    public String getProfileAverage(List<ResultElement> profileResultList) {
        double total = 0;
        for (int i = 0; i < profileResultList.size(); ++i) {
            total = total + profileResultList.get(i).getPerformanceInSec();
        }
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(total / profileResultList.size());
    }

    public String getProfileBest(List<ResultElement> profileResultList) {
        double bestTime = profileResultList.get(0).getPerformanceInSec();
        for (int i = 0; i < profileResultList.size(); ++i) {
            bestTime = profileResultList.get(i).getPerformanceInSec() < bestTime ? profileResultList.get(i).getPerformanceInSec() : bestTime;
        }
        return df.format(bestTime);
    }

    public String getProfileWorst(List<ResultElement> profileResultList) {
        double worstTime = profileResultList.get(0).getPerformanceInSec();
        for (int i = 0; i < profileResultList.size(); ++i) {
            worstTime = profileResultList.get(i).getPerformanceInSec() > worstTime ? profileResultList.get(i).getPerformanceInSec() : worstTime;
        }
        return df.format(worstTime);
    }

    boolean isNewPlayer(final String profile) {
        Collection<ResultElement> result = Collections2.filter(resultList, new Predicate<ResultElement>() {


            @Override
            public boolean apply(final ResultElement resultElement) {
                return resultElement.getProfile().equals(profile);
            }
        });
        return result.size() == 0 ? true : false;
    }

    public List<ResultElement> getProfileStatistics(final String profile){
        Collection<ResultElement> profileReusult = Collections2.filter(resultList, new Predicate<ResultElement>() {
            @Override
            public boolean apply(final ResultElement resultElement) {
                return resultElement.getProfile().equals(profile);
            }
        });
        List profileReusultList = new ArrayList(profileReusult);
        return profileReusultList;
    }
    public void showReport(final String profile) {
        Collection<ResultElement> profileReusult = Collections2.filter(resultList, new Predicate<ResultElement>() {
            @Override
            public boolean apply(final ResultElement resultElement) {
                return resultElement.getProfile().equals(profile);
            }
        });
        List profileReusultList = new ArrayList(profileReusult);
        System.out.println(profileReusultList);
        System.out.println("========================Test Report===========================");
        System.out.println(profile + " , well come back!");
        System.out.println("Here's your previous results..."
        );
        System.out.println("Total test done: " + profileReusultList.size());
        System.out.println("Average: " + getProfileAverage(profileReusultList) + " sec");
        System.out.println("Best: " + getProfileBest(profileReusultList) + " sec");
        System.out.println("Worst: " + getProfileWorst(profileReusultList) + " sec");
        System.out.println("==============================================================");

    }

    public void showReport(final ResultElement userResultElement) {
        Collection<ResultElement> userReusult = Collections2.filter(resultList, new Predicate<ResultElement>() {
            @Override
            public boolean apply(final ResultElement resultElement) {
                return resultElement.getProfile().equals(userResultElement.getProfile());
            }
        });
        List userReusultList = new ArrayList(userReusult);
        System.out.println(userReusultList);
        System.out.println("========================Test Report===========================");
        System.out.println(userResultElement.getProfile() + " , here's your test report...");
        System.out.println("Total test done: " + userReusultList.size());
        System.out.println("This Time: " + userResultElement.getPerformanceInSec() + " sec");
        System.out.println("Average: " + getProfileAverage(userReusultList) + " sec");
        System.out.println("Best: " + getProfileBest(userReusultList) + " sec");
        System.out.println("Worst: " + getProfileWorst(userReusultList) + " sec");
        System.out.println("==============================================================");

    }

}
