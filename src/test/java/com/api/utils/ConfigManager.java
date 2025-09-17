package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//write a program to read properties file from src/test/resources/config/config.properties
public class ConfigManager {

    private ConfigManager(){
        //Create a private constructor so objects of this class cannot be created outside the class
    }


    private static Properties prop = new Properties();
    private static String path = "config/config.properties";
    private static String env;


    static{
        env = System.getProperty("env","qa");
        env = env.toLowerCase().trim();
        System.out.println("Running tests in env "+env);
        switch(env){
            case "dev" -> path = "config/config.dev.properties";
            case "uat" -> path = "config/config.uat.properties";
            case "qa" -> path = "config/config.qa.properties";
            default ->  path = "config/config.qa.properties";
        }
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

        if(input==null){
            throw new RuntimeException("Cannot find the file at the file path"+path);
        }

        try {
            prop.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key){
        //Create the object of Properties class
        //Load the properties
        return prop.getProperty(key);

    }
}
