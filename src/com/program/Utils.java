package com.program;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utils {
    public static ArrayList<Object> LoadJson() {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
        ArrayList<Object> itemList = new ArrayList<Object>();

        try (FileReader reader = new FileReader("userInput.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray itemJsonArray = (JSONArray) obj;

            //Iterate over object array
            itemJsonArray.forEach( item -> itemList.add(item) );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return itemList;
    }
}
