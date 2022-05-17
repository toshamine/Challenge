package com.example.challenge.Models;

import lombok.Data;

@Data
public class Talk {

    private String title;

    private Integer Duration;

    public Talk(String title) {

        this.title = title;

        if(this.title.toUpperCase().indexOf("MIN")!=-1)
        {
            String min = this.title.toUpperCase()
                    .substring(this.title.length()-5)
                    .replaceAll("[^\\d.]", "");
            this.setDuration(Integer.valueOf(min));
        }
        else
        {
            this.setDuration(5);
        }
    }

    public Talk durations()
    {
        if(this.title.toUpperCase().indexOf("MIN")!=-1)
        {
            String min = this.title.toUpperCase()
                    .substring(0, this.title.indexOf("MIN"));
            this.setDuration(Integer.valueOf(min));
        }
        else
        {
            this.setDuration(5);
        }
        return this;
    }
}
