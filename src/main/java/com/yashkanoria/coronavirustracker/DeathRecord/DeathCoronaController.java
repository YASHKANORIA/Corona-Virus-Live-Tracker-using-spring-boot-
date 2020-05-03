package com.yashkanoria.coronavirustracker.DeathRecord;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;


@Controller
public class DeathCoronaController {

     @Autowired
    private DeathcoronaVirusData deathcoronaVirusData;
    @GetMapping("/death")
    public String DeathPage(Model model)
    {
        ArrayList<DeathLocationWiseData> finalArrayDeathList = deathcoronaVirusData.getDeathdata();

        int sum=0;
        for(int i=0;i<finalArrayDeathList.size();i++)
        {
            sum=sum+finalArrayDeathList.get(i).getDeathlatestCount();
        }

            model.addAttribute("DeathlocationWiseData",finalArrayDeathList);
           model.addAttribute("TotalDeathCases",sum);
            return "death1";
    }

    public int returnDeathCount()
    {
        ArrayList<DeathLocationWiseData> finalArrayDeathList = deathcoronaVirusData.getDeathdata();

        int sum=0;
        for(int i=0;i<finalArrayDeathList.size();i++)
        {
            sum=sum+finalArrayDeathList.get(i).getDeathlatestCount();
        }

        return sum;
    }

}
