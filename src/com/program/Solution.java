package com.program;

import com.program.Utils;
import java.net.*;
import java.io.*;
import java.util.ArrayList;

class Solution {
    public static void main(String[] args) {
        // Note: use HttpURLConnection to make api calls.
        //First get the input
        ArrayList<Object> itemList = Utils.LoadJson();
        String url = itemList.get(0).toString();
        String time = itemList.get(1).toString();

        //mke conncection

        //System.out.print("This is where your output will go");
    }
}
