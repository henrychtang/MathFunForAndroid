package home.henry.math;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * Created by Henry on 28/12/2017.
 */

public class ConfigStore {
    Config config;
    String configFile;

    public ConfigStore(String configFile) {
        this.configFile = configFile;
        if (resultStoreExist()) {
            config = readFromConfigFile();
            System.out.println("Ready from config file...");
        } else {
            System.out.println("Create new config...");
            config = createDummyConfig();
            writeToConfigFile(config);
        }
    }

    Config createDummyConfig() {
        return new Config();

    }

    boolean resultStoreExist() {
        File file = new File(configFile);
        if (file.exists() && file.isFile()) {
            System.out.println("file exists, and it is a file");
            return true;
        } else {
            System.out.println("File not exist");
            return false;
        }
    }

    public void writeToConfigFile(Config config) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(configFile), config);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Config readFromConfigFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(configFile), new TypeReference<Config>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
