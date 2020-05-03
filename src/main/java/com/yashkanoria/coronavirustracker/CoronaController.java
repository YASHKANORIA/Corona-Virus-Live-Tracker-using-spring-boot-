package com.yashkanoria.coronavirustracker;


import com.yashkanoria.coronavirustracker.DeathRecord.DeathCoronaController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;


@Controller
public class CoronaController {

     @Autowired
    private coronaVirusData corona;

     @Autowired
     private DeathCoronaController deathCoronaController;
    @GetMapping("/")
    public String homePage(Model model)
    {
        ArrayList<LocationWiseData> finalArrayList = corona.getData();

        int sum=0;
        for(int i=0;i<finalArrayList.size();i++)
        {
            sum=sum+finalArrayList.get(i).getLatestCount();
        }
            int count=deathCoronaController.returnDeathCount();
            model.addAttribute("locationWiseData",finalArrayList);
           model.addAttribute("TotalCases",sum);
        model.addAttribute("deathcount",count);
            return "home";
    }

    @GetMapping("/coronajson")
    public ArrayList<LocationWiseData> homePagejson()
    {
        ArrayList<LocationWiseData> finalArrayList = corona.getData();

        int sum=0;
        for(int i=0;i<finalArrayList.size();i++)
        {
            sum=sum+finalArrayList.get(i).getLatestCount();
        }
        return  finalArrayList;
    }

}
