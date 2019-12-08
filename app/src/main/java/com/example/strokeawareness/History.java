package com.example.strokeawareness;

import java.util.Date;

public class History {
    String UserId;
    String category;
    String calendar;
    String risk;


    public History() {

    }


    public History (String UserId, String risk, String category, String calendar){
        this.UserId = UserId;
        this.risk = risk;
        this.category = category;
        this.calendar = calendar;
    }


    public String getUserId()
    {
        return UserId;
    }

    public void setUserId(String userId)
    {
        UserId = userId;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public String getCalendar(String calendar)
    {
        return calendar;
    }

    public void setCalendar(String calendar)
    {
        this.calendar = calendar;
    }

    public String getRisk()
    {
        return risk;
    }

    public void setRisk(String risk)
    {
        this.risk = risk;
    }
}
