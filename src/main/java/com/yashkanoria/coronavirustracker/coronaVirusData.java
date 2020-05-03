package com.yashkanoria.coronavirustracker;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@Service



public class coronaVirusData {

    public static String VIRUS_URL="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";


           ArrayList<LocationWiseData> data = new ArrayList<>();

    public ArrayList<LocationWiseData> getData() {
        return data;
    }

    @PostConstruct
         @Scheduled(cron = "* * 1 * * *")
        public void fetchData() throws IOException, InterruptedException {

             ArrayList<LocationWiseData> updateData = new ArrayList<>();
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(VIRUS_URL)).build();
            HttpResponse<String> httpResponse = client.send(request,HttpResponse.BodyHandlers.ofString());

            //System.out.println(httpResponse.body());

            StringReader csvReader = new StringReader(httpResponse.body());

             Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
             for (CSVRecord record : records) {

                 LocationWiseData locationWiseData =  new LocationWiseData();
                 locationWiseData.setState(record.get("Province/State"));
                 locationWiseData.setCountry(record.get("Country/Region"));
                 int LatesttotalCount=Integer.parseInt(record.get(record.size()-1));
                 int CasesRise =Integer.parseInt(record.get(record.size()-2));

                 locationWiseData.setLatestCount(LatesttotalCount);
                 locationWiseData.setCasesrise(LatesttotalCount-CasesRise);
                // String Country = record.get("Country/Region");
                 //String name = record.get("Name");
                 updateData.add(locationWiseData);


                // System.out.println(locationWiseData.toString());
             }

             this.data=updateData;

         }
}
