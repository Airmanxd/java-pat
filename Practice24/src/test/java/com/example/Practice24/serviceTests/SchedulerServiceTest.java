package com.example.Practice24.serviceTests;

import com.example.Practice24.models.Departure;
import com.example.Practice24.models.PostOffice;
import com.example.Practice24.repositories.DepartureRepository;
import com.example.Practice24.repositories.PostOfficeRepository;
import com.example.Practice24.repositories.UserRepository;
import com.example.Practice24.services.DepartureService;
import com.example.Practice24.services.PostOfficeService;
import com.example.Practice24.services.SchedulerService;
import com.example.Practice24.services.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class SchedulerServiceTest {
    @MockBean
    private DepartureService departureService;
    @MockBean
    private PostOfficeService postOfficeService;
    @MockBean
    private UserRepository userRepository;
    private UserService userService;
    @MockBean
    private PostOfficeRepository postOfficeRepository;
    @MockBean
    private DepartureRepository departureRepository;
    private List<Departure> departures = new ArrayList<>();
    private List<PostOffice> postOffices = new ArrayList<>();
    @Autowired
    private SchedulerService schedulerService;


    @Test
    public void test() throws IOException
    {
        PostOffice PO = new PostOffice();
        PO.setId(1);
        PO.setName("Test");
        PO.setCityName("TestCity");
        postOffices.add(PO);
        PostOffice PO1 = new PostOffice();
        PO1.setId(2);
        PO1.setName("Test2");
        PO1.setCityName("TestCity2");
        postOffices.add(PO1);
        Departure departure1 = new Departure();
        departure1.setId(1);
        departure1.setType("testType");
        departure1.setDepartureDate("testDate");
        departure1.setPostOffice(PO);
        departures.add(departure1);
        Departure departure2 = new Departure();
        departure2.setId(2);
        departure2.setType("testType2");
        departure2.setDepartureDate("testDate2");
        departure2.setPostOffice(PO1);
        departures.add(departure2);

        when(departureService.getAll()).thenReturn(departures);
        when(postOfficeService.getAll()).thenReturn(postOffices);

        schedulerService.task();

        File departs = new File("src/main/resources/dataDump/Departures.txt");
        File pos = new File("src/main/resources/dataDump/PostOffices.txt");
        assertNotNull(departs);
        assertNotNull(pos);

        String departureCheck = "";
        for(Departure departure : departures)
            departureCheck += String.format("id: %d type: %s date: %s\n", departure.getId(), departure.getType(), departure.getDepartureDate());

        String postOffceCheck = "";
        for(PostOffice post : postOffices)
            postOffceCheck += String.format("id: %d name: %s city name: %s\n", post.getId(), post.getName(), post.getCityName());

        Scanner departsreader = new Scanner(departs);
        Scanner POreader = new Scanner(pos);

        String departsRes = "";
        while (departsreader.hasNextLine()) {
            departsRes += departsreader.nextLine() + "\n";
        }

        String POres = "";
        while (POreader.hasNextLine()) {
            POres += POreader.nextLine() + "\n";
        }

        assertEquals(departureCheck, departsRes);
        assertEquals(postOffceCheck, POres);
    }
}
