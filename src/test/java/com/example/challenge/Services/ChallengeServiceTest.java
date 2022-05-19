package com.example.challenge.Services;

import com.example.challenge.Models.Talk;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class ChallengeServiceTest {

    @Test
    void arrangetracks() {

        List<String> testlist=new ArrayList<>() {
            {
                add("> Writing Fast Tests Against Enterprise Rails 60min");
                add("> Overdoing it in Python 45min");
                add("> Lua for the Masses 30min");
                add("> Ruby Errors from Mismatched Gem Versions 45min");
                add("> Common Ruby Errors 45min");
                add("> Rails for Python Developers lightning");
                add("> Communicating Over Distance 60min");
                add("> Accounting-Driven Development 45min");
                add("> Woah 30min");
                add("> Sit Down and Write 30min");
                add("> Pair Programming vs Noise 45min");
                add("> Rails Magic 60min");
                add("> Ruby on Rails: Why We Should Move On 60min");
                add("> Clojure Ate Scala (on my project) 45min");
                add("> Programming in the Boondocks of Seattle 30min");
                add("> Ruby vs. Clojure for Back-End Development 30min");
                add("> Ruby on Rails Legacy App Maintenance 60min");
                add("> A World Without HackerNews 30min");
                add("> User Interface CSS in Rails Apps 30min");
            }
        };

        String lunchtest="";
        ChallengeService challengeService=new ChallengeService();
        List<Talk> testtalks=new ArrayList<>();
        List<String> response=new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            Collections.shuffle(testlist);
            testtalks=challengeService.duration(testlist);
            response=challengeService.arrangetracks(testtalks,1);
            assertThat(testlist.size()).isLessThan(response.size());
            assertThat(response.indexOf("> 12:00AM  Lunch")).isGreaterThan(-1);
            assertThat(response.contains("> 04:00PM  Networking Event") || response.contains("> 05:00PM  Networking Event"))
                    .isTrue();
            assertThat(response.get(response.size()-1).contains("05:00PM") || response.get(response.size()-1).contains("04:00PM"))
                    .isTrue();
        }

    }
}