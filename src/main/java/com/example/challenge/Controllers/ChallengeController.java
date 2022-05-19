package com.example.challenge.Controllers;


import com.example.challenge.Models.Talk;
import com.example.challenge.Services.ChallengeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChallengeController {

    private ChallengeService challengeService;

    @PostMapping("/arrange")
    public List<String> arrange(@RequestBody List<String> tracks)
    {
        List<Talk> talks= challengeService.duration(tracks);
        return challengeService.arrangetracks(talks,1);
    }

}
