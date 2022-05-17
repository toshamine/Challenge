package com.example.challenge.Services;

import com.example.challenge.Models.Talk;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChallengeService {


    public List<Talk> duration(List<String> tracks)
    {
        Map<String,Integer> talks=new HashMap<>();
        return tracks.stream()
                .map(t -> new Talk(t))
                .collect(Collectors.toList());
    }

    public List<String> morningtrack(List<Talk> talks)
    {
        List<String> morning=new ArrayList<>();
        Integer sum =0;
       for(Talk talk:talks)
       {
           sum+=talk.getDuration();
           morning.add(talk.getTitle());
           if(sum>180)
           {
               sum-= talk.getDuration();
               morning.remove(talk.getDuration());
               continue;
           }
           if(sum==180)
           {
               break;
           }
       }
       return morning;
    }

}
