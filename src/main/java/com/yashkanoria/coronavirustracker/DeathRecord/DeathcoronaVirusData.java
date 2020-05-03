package com.yashkanoria.coronavirustracker.DeathRecord;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.ArrayList;

@Service



public class DeathcoronaVirusData {

    public static String VIRUS_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";


           ArrayList<DeathLocationWiseData> Deathdata = new ArrayList<>();

    public ArrayList<DeathLocationWiseData> getDeathdata() {
        return Deathdata;
    }

    public void setDeathdata(ArrayList<DeathLocationWiseData> deathdata) {
        Deathdata = deathdata;
    }

    public ArrayList<DeathLocationWiseData> getData() {
        return Deathdata;
    }

    @PostConstruct
         @Scheduled(cron = "* * 1 * * *")
        public void fetchData() throws IOException, InterruptedException {

             ArrayList<DeathLocationWiseData> Deathupdatedata = new ArrayList<>();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_URL)).build();
            HttpResponse<String> httpResponse = client.send(request,HttpResponse.BodyHandlers.ofString());



            //System.out.println(httpResponse.body());

            StringReader csvReader = new StringReader(httpResponse.body());

             Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
             for (CSVRecord record : records) {

                 DeathLocationWiseData deathLocationWiseData =  new DeathLocationWiseData();
                 deathLocationWiseData.setState(record.get("Province/State"));
                 deathLocationWiseData.setCountry(record.get("Country/Region"));
                 int DeathtotalCount=Integer.parseInt(record.get(record.size()-1));
                 int DeathRise =Integer.parseInt(record.get(record.size()-2));

                 deathLocationWiseData.setDeathlatestCount(DeathtotalCount);
                 deathLocationWiseData.setDeathrise(DeathtotalCount-DeathRise);
                // String Country = record.get("Country/Region");
                 //String name = record.get("Name");
                 Deathupdatedata.add(deathLocationWiseData);


                // System.out.println(locationWiseData.toString());
             }

             this.Deathdata=Deathupdatedata;

         }
}
