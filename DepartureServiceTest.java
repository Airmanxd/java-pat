package com.example.Practice24;

import com.example.Practice24.models.Departure;
import com.example.Practice24.models.PostOffice;
import com.example.Practice24.repositories.DepartureRepository;
import com.example.Practice24.repositories.PostOfficeRepository;
import com.example.Practice24.services.DepartureService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartureServiceTest {
    @MockBean
    private PostOfficeRepository postOfficeRepository;
    @MockBean
    private DepartureRepository departureRepository;
    private DepartureService departureService;
    @Captor
    private ArgumentCaptor<Departure> captor;
    private Departure departure;
    @Before
    public void prep()
    {
        PostOffice postOffice = new PostOffice();
        postOffice.setId(1234);
        postOffice.setName("Test");
        postOffice.setCityName("TestCity");
        postOfficeRepository.save(postOffice);

        departure = new Departure();
        departure.setId(123);
        departure.setType("Test");
        departure.setDepartureDate("TestDate");
        departureService.save(departure, 1234);

        departure = new Departure();
        departure.setId(12345);
        departure.setType("Test2");
        departure.setDepartureDate("TestDate2");
        departureService.save(departure, 1234);

    }
    @Test
    public void deleteTest()
    {
        PostOffice postOffice = new PostOffice();
        postOffice.setId(1234);
        postOffice.setName("Test");
        postOffice.setCityName("TestCity");
        postOfficeRepository.save(postOffice);

        departure = new Departure();
        departure.setId(123);
        departure.setType("Test");
        departure.setDepartureDate("TestDate");
        departureService.save(departure, 1234);

        departure = new Departure();
        departure.setId(12345);
        departure.setType("Test2");
        departure.setDepartureDate("TestDate2");
        departureService.save(departure, 1234);
        departureService.delete(123);
        List<Departure> departures = departureService.getAll();
        Assertions.assertEquals(1, departures.size());
        Assertions.assertEquals(12345, departures.get(0).getId());
    }
    @Test
    public void getAllTest()
    {
        //prep();
        List<Departure> departures = departureService.getAll();
        Assertions.assertEquals( 2, departures.size());
        Assertions.assertEquals(123, departures.get(0).getId());
        Assertions.assertEquals(12345, departures.get(1).getId());
    }
    @Test
    public void getSortedByFieldNameTest()
    {
        //prep();
        List<Departure> departuresById = departureService.getSortedDeparturesByField("id");
        List<Departure> departuresByType = departureService.getSortedDeparturesByField("type");
        Assertions.assertEquals(departuresById.get(0).getId(), 123);
        Assertions.assertEquals(departuresByType.get(0).getType(), "Test");
    }
    @Test
    public void getByPOTest()
    {
        //prep();
        PostOffice postOffice = new PostOffice();
        postOffice.setId(2);
        postOffice.setName("Test2");
        postOffice.setCityName("TestCity2");
        postOfficeRepository.save(postOffice);
        departure = new Departure();
        departure.setId(10);
        departure.setType("TestOtherPo");
        departure.setDepartureDate("TestDateOtherPo");
        departureService.save(departure, 2);
        Assertions.assertEquals(departureService.getAllDeparturesByPostOfficeId(2).get(0), departure);
        Assertions.assertEquals(departureService.getAllDeparturesByPostOfficeId(123).size(), 2);

    }
    @Autowired
    public void setDepartureService(DepartureService departureService) {
        this.departureService = departureService;
    }
}
