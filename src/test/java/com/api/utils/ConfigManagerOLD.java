package com.api.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

//write a program to read properties file from src/test/resources/config/config.properties
public class ConfigManagerOLD {

    private ConfigManagerOLD(){
        //Create a private constructor so objects of this class cannot be created outside the class
    }


    private static Properties prop = new Properties();

    static{
        //Operation of loading the properties file in the memory!
        //static block it will executed...once during class loading time

        //File configFile = new File(System.getProperty("user.dir")+"/src/test/resources/config/config.properties");

       //Commenting above line and we can file seperator so that it should work in any windows, mac,linux, jenkins etc
        //to make test platform independant
        File configFile = new File(System.getProperty("user.dir") +File.separator+"src"
                +File.separator+"test"+File.separator+"resources"
                +File.separator+"config"+File.separator+"config.properties");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(configFile);
            prop.load(fileReader);
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
