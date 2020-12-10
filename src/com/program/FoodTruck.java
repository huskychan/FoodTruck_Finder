package com.program;

public class FoodTruck{
    private String Applicant;
    private String DayofWeekStr;
    private String start24;
    private String end24;

    public FoodTruck(String Applicant,String DayofWeekStr,String start24,String end24){
        this.Applicant = Applicant;
        this.DayofWeekStr =DayofWeekStr;
        this.start24 =start24;
        this.end24 =end24;
    }

    public String getApplicant(){
        return Applicant;
    }

    public String getDayofWeekStr() {return DayofWeekStr;}

    public String getStart24() {return start24;}

    public String getEnd24(){
        return end24;
    }

    public void setApplicant(String applicant) {
        this.Applicant = applicant;
    }

    public void setDayofWeekStr(String dayOfWeekStr){
        this.DayofWeekStr = dayOfWeekStr;
    }

    public void setStart24(String start24){
        this.start24 = start24;
    }

    public void setEnd24(String end24){
        this.end24 = end24;

    }




}