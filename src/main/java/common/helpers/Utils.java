package common.helpers;

import driver.DriverManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Random;

public class Utils {
    public static Properties readProperties(String filePath, boolean isExternalResource) {
        InputStream envInput;
        Properties props = new Properties();
        String path = "";

        try {
            envInput = Utils.class.getResourceAsStream(System.getProperty("file.separator") + filePath);
            if (envInput == null) {
                if (isExternalResource) {
                    path = System.getProperty("user.dir") + System.getProperty("file.separator");
                } else {
                    path = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
                            + System.getProperty("file.separator") + "test" + System.getProperty("file.separator");
                }
                envInput = new FileInputStream(path + filePath);
            }

            Reader reader = new InputStreamReader(envInput, StandardCharsets.UTF_8);
            props.load(reader);
        } catch (IOException e) {

        }
        return props;
    }

    public static String getRandomAlphaNumericString(int length) {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";
        StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }

        return sb.toString();
    }

    public static Object getDataFromJsonFile(String fileName){
        JSONParser parser = new JSONParser();
        String locatorTestPath = System.getProperty("user.dir") + System.getProperty("file.separator") + "src"
                + System.getProperty("file.separator") + "test";

        String locatorResourcePath = System.getProperty("file.separator") + "resources"
                + System.getProperty("file.separator") + "data" + System.getProperty("file.separator")
                + fileName + ".json";
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(locatorTestPath + locatorResourcePath));
            Logger.info(DriverManager.getDriverName() + ":" + locatorTestPath + locatorResourcePath);
            for (Object o : a){
                return (JSONObject) o;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int randomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
}
