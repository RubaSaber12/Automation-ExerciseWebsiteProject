package Utilities;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.jsonpath.JsonPath;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {

    public final static String TEST_DATA_PATH = "src/test/resources/TestData/";

    // TODO: Read data from JSON file
    public static String getJsonData(String jsonFilename, String field) {
        try {
            // Define object of file Reader
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFilename + ".json");

            // Parse the JSON directly into a JsonElement
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    // TODO: Get properties from any .properties file
    public static String getPropertyValue(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
        return properties.getProperty(key);
    }

    //TODO: Read any field from json file
    public static String getJsonValue(String jsonFilename, String field) {
        try {
            FileReader reader = new FileReader(TEST_DATA_PATH + jsonFilename + ".json");
            Object jsonData = new Gson().fromJson(reader, Object.class);
            return JsonPath.read(jsonData, "$." + field);
        } catch (Exception e) {
            LogsUtils.error(e.getMessage());
            return ""; // Return empty string in case of any exception
        }
    }


}


