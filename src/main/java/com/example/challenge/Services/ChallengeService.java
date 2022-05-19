package com.example.challenge.Services;

import com.example.challenge.Models.Talk;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ChallengeService {


    //Get every talk duration
    public List<Talk> duration(List<String> tracks)
    {
        Map<String,Integer> talks=new HashMap<>();
        return tracks.stream()
                .map(t -> new Talk(t))
                .collect(Collectors.toList());
    }

    //Arrange the talks into tracks
    public List<String> arrangetracks(List<Talk> talks,int tracknumber)
    {
        LocalTime time=LocalTime.of(9,0);
        List<String> morning=new ArrayList<>();
        List<String> evening=new ArrayList<>();
        List<Talk> resttalks=new ArrayList<>();

        morning.add("Track "+tracknumber);

        resttalks.addAll(talks);
        Integer sum =0;
        //Arranging Morning sesssion
       for(Talk talk:talks)
       {
           sum+=talk.getDuration();
           String finaltitle=new StringBuilder(talk.getTitle())
                   .insert(talk.getTitle().indexOf(">")+1," "+time.toString()+"AM ").toString();
           time=time.plusMinutes(talk.getDuration());


           morning.add(finaltitle);
           int index=resttalks.indexOf(talk);
           resttalks.remove(talk);
           if(sum>180)
           {
               sum-= talk.getDuration();
               time=time.minusMinutes(talk.getDuration());
               resttalks.add(index,talk);
               morning.remove(finaltitle);
               continue;
           }
           if(sum==180)
           {
               break;
           }

       }

       List<Talk> secondtracktalks=new ArrayList<>();
       secondtracktalks.addAll(resttalks);
       sum=0;
       time=LocalTime.of(1,0);

        //Arranging Evening sesssion
        for(Talk talk:resttalks)
        {
            sum+=talk.getDuration();

            String finaltitle=new StringBuilder(talk.getTitle())
                    .insert(talk.getTitle().indexOf(">")+1," "+time.toString()+"PM ").toString();
            time=time.plusMinutes(talk.getDuration());

            evening.add(finaltitle);
            int index=secondtracktalks.indexOf(talk);
            secondtracktalks.remove(talk);

            if(sum>240)
            {
                sum-= talk.getDuration();
                time=time.minusMinutes(talk.getDuration());
                secondtracktalks.add(index,talk);
                evening.remove(finaltitle);
                continue;
            }
            if(sum==240)
            {
                break;
            }


        }
        morning.add("> 12:00AM  Lunch");
        morning.addAll(evening);


        if(time.isBefore(LocalTime.of(4,1)))
        {
            morning.add("> 04:00PM  Networking Event");
        }
        else
        {
            morning.add("> 05:00PM  Networking Event");
        }

        //Arranging extra trakcs (if more then 1)
       if(secondtracktalks.size()>0) {
            List<String> Track2 = arrangetracks(secondtracktalks,tracknumber+1);
            morning.addAll(Track2);
        }

        return morning;
    }



}
