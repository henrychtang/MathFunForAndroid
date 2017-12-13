package home.henry.math;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.*;

;
/**
 * Created by Henry on 28/11/2017.
 */
public class ResultStore {
    List<ResultElement> resultList;
    ObjectMapper objectMapper = new ObjectMapper();
    String resultDbFile = "target/resultDB.json";

    public ResultStore() {


        if (resultStoreExist()) {
            resultList = readFromResultStore();
            System.out.println("Ready from file...");
        } else {
            System.out.println("Create new resultStore...");
            resultList = createDummyResultList();
            writeToResultStore(resultList);
        }
        //   System.out.println(resultList);

    }


    List<ResultElement> createDummyResultList() {

        List resultList = new LinkedList<ResultElement>();
        ResultElement resultElement1 = new ResultElement("Audrey", new Date(), 78);
        ResultElement resultElement2 = new ResultElement("Audrey", new Date(), 60);
        ResultElement resultElement3 = new ResultElement("Audrey", new Date(), 54);
        resultList.add(resultElement1);
        resultList.add(resultElement2);
        resultList.add(resultElement3);
        //    System.out.println(resultList);
        return resultList;
    }

    void writeToResultStore(List<ResultElement> resultList) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(resultDbFile), resultList);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    List<ResultElement> readFromResultStore() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(resultDbFile), new TypeReference<List<ResultElement>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }

    void addResult(ResultElement resultElement) {
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


    int getUserAverage(List<ResultElement> userResultList) {
        int total = 0;
        for (int i = 0; i < userResultList.size(); ++i) {
            total = total + userResultList.get(i).getPerformanceInSec();
        }
        return total / userResultList.size();
    }

    int getUserBestList(List<ResultElement> userResultList) {
        int bestTime = userResultList.get(0).getPerformanceInSec();
        for (int i = 0; i < userResultList.size(); ++i) {
            bestTime = userResultList.get(i).getPerformanceInSec() < bestTime ? userResultList.get(i).getPerformanceInSec() : bestTime;
        }
        return bestTime;
    }

    int getUserWorstList(List<ResultElement> userResultList) {
        int worstTime = userResultList.get(0).getPerformanceInSec();
        for (int i = 0; i < userResultList.size(); ++i) {
            worstTime = userResultList.get(i).getPerformanceInSec() > worstTime ? userResultList.get(i).getPerformanceInSec() : worstTime;
        }
        return worstTime;
    }
    boolean isNewPlayer(final String user){


        Collection<ResultElement> result = Collections2.filter(resultList, new Predicate<ResultElement>() {


            @Override
            public boolean apply(final ResultElement resultElement) {
                return resultElement.getUser().equals(user);
            }
        });
        return result.size()==0? true:false;
    }
    void showReport(final String user) {

        Collection<ResultElement> userReusult = Collections2.filter(resultList, new Predicate<ResultElement>() {
            @Override
            public boolean apply(final ResultElement resultElement) {
                return resultElement.getUser().equals(user);
            }
        });
        List userReusultList = new ArrayList(userReusult);
        System.out.println(userReusultList);
        System.out.println("========================Test Report===========================");
        System.out.println(user + " , well come back!");
        System.out.println("Here's your previous results..."
        );
        System.out.println("Total test done: " + userReusultList.size());
        System.out.println("Average: " + getUserAverage(userReusultList) + " sec");
        System.out.println("Best: " + getUserBestList(userReusultList) + " sec");
        System.out.println("Worst: " + getUserWorstList(userReusultList) + " sec");
        System.out.println("==============================================================");

    }

    void showReport(final ResultElement userResultElement) {

        Collection<ResultElement> userReusult = Collections2.filter(resultList, new Predicate<ResultElement>() {
            @Override
            public boolean apply(final ResultElement resultElement) {
                return resultElement.getUser().equals(userResultElement.getUser());
            }
        });
        List userReusultList = new ArrayList(userReusult);
        System.out.println(userReusultList);
        System.out.println("========================Test Report===========================");
        System.out.println(userResultElement.getUser() + " , here's your test report...");
        System.out.println("Total test done: " + userReusultList.size());
        System.out.println("This Time: " + userResultElement.getPerformanceInSec() + " sec");
        System.out.println("Average: " + getUserAverage(userReusultList) + " sec");
        System.out.println("Best: " + getUserBestList(userReusultList) + " sec");
        System.out.println("Worst: " + getUserWorstList(userReusultList) + " sec");
        System.out.println("==============================================================");

    }

}
