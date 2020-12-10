package com.program;

import com.program.Utils;
import java.net.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


class Solution {
    public static void main(String[] args) throws IOException, java.text.ParseException{
        // Note: use HttpURLConnection to make api calls.
        //First get the input
        ArrayList<Object> itemList = Utils.LoadJson();
        String url_String = itemList.get(0).toString();
        String time = itemList.get(1).toString();
        long unixSeconds = Long.parseLong(time);

        //Convert the time
        Date date = new java.util.Date(unixSeconds*1000L);
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("EEEE HH:mm");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT-0"));
        String curDate = sdf.format(date);



        //make conncection
        List<FoodTruck> list = new ArrayList<>();
        URL url = new URL(url_String);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type","application/json");
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        //If connection is sucessful
        if (conn.getResponseCode() == 200) {
            InputStream is = conn.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = is.read(buffer)) != -1){
                baos.write(buffer,0,len);
            }

            String jsonString = baos.toString();
            baos.close();
            is.close();


            JSONParser jsonParser = new JSONParser();

            try{
                Object obj = jsonParser.parse(jsonString);
                JSONArray jsonArray = (JSONArray) obj;

                for (int i=0;i<jsonArray.size();i++) {
                    FoodTruck foodTruck = new FoodTruck("","","","");
                    JSONObject jsonObject =(JSONObject) jsonArray.get(i);

                    //Get those content
                    foodTruck.setApplicant((String) jsonObject.get("Applicant"));
                    foodTruck.setDayofWeekStr((String) jsonObject.get("DayOfWeekStr"));
                    foodTruck.setStart24((String) jsonObject.get("start24"));
                    foodTruck.setEnd24((String) jsonObject.get("end24"));


                    list.add(foodTruck);

                }

            }catch (ParseException e){
                e.printStackTrace();

            }

        }

        //Now we can start filtering them
        ArrayList<String> ansList = getAvailableTrucker(sdf,curDate,list);
        for (String truck : ansList) {
            System.out.println(truck);

        }
    }


    //the method for filtering
    private static ArrayList<String> getAvailableTrucker(SimpleDateFormat sdf, String curDate, List<FoodTruck> truckList) throws java.text.ParseException{
        //use set to eliminate duplication
        HashSet<String> openNow = new HashSet<>();
        for (FoodTruck truck : truckList) {
            Boolean open =checkOpen(sdf,curDate,truck);
            if (open) {
                openNow.add(truck.getApplicant());

            }

        }
        ArrayList<String> truckNames = new ArrayList<>();
        truckNames.addAll(openNow);
        Collections.sort(truckNames);
        return truckNames;
    }

    private static Boolean checkOpen(SimpleDateFormat sdf, String curDate, FoodTruck truck) throws java.text.ParseException {

        String truckWeek = truck.getDayofWeekStr();
        String str = truck.getStart24();
        String end = truck.getEnd24();
        String[] today = curDate.split(" ");

        //First check whether it will open today
        if (truckWeek.equals(today[0])) {
            SimpleDateFormat time = new SimpleDateFormat("HH:MM");
            Date tStart = time.parse(str);
            Date tEnd = time.parse(end);
            Date cur = time.parse(today[1]);
            if (tStart.before(cur) && cur.before(tEnd)) {
                return true;

            } else {
                return false;
            }

        } else {
            return false;
        }
    }







}
