package com.example.Practice23.services;

import com.example.Practice23.models.Departure;
import com.example.Practice23.models.PostOffice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
public class SchedulerService {
    private DepartureService departureService;
    private PostOfficeService postOfficeService;
    @Scheduled(cron = "0 0/30 * * * *")
    public void task() throws IOException {
        log.info("Performing scheduled task");
        String path ="src/main/resources/dataDump";
        File directory = new File(path);
        if(directory.exists())
        {
            FileSystemUtils.deleteRecursively(directory);
        }
        else{
            directory.mkdir();
        }
        File departures = new File(path+"/departures.txt");
        departures.createNewFile();

        File POs = new File(path+"/PostOffices.txt");
        POs.createNewFile();

        List<Departure> departureDump = departureService.getAll();
        List<PostOffice> POdump = postOfficeService.getAll();

        BufferedWriter writer = new BufferedWriter(new FileWriter(path+"/departures.txt"));
        String result = "";
        for(Departure departure : departureDump)
            result += String.format("id: %d type: %s date: %s\n", departure.getId(), departure.getType(), departure.getDepartureDate());

        writer.write(result);
        writer.close();

        BufferedWriter writer1 = new BufferedWriter(new FileWriter(path +"/PostOffices.txt"));
        for(PostOffice PO : POdump)
            result += String.format("id: %d name: %s city name: %s\n", PO.getId(), PO.getName(), PO.getCityName());

        writer1.write(result);
        writer1.close();
    }
    @Autowired
    public void setDepartureService(DepartureService departureService) {
        this.departureService = departureService;
    }
    @Autowired
    public void setPostOfficeService(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }
}
