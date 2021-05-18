package com.example.Practice24.serviceTests;

import com.example.Practice24.models.Departure;
import com.example.Practice24.models.PostOffice;
import com.example.Practice24.repositories.DepartureRepository;
import com.example.Practice24.repositories.PostOfficeRepository;
import com.example.Practice24.repositories.UserRepository;
import com.example.Practice24.services.DepartureService;
import com.example.Practice24.services.PostOfficeService;
import com.example.Practice24.services.UserService;
import org.junit.Before;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepartureServiceTest {
    @MockBean
    private UserRepository userRepository;
    private UserService userService;
    @MockBean
    private PostOfficeRepository postOfficeRepository;
    private PostOfficeService postOfficeService;
    @MockBean
    private DepartureRepository departureRepository;
    private DepartureService departureService;
    @Captor
    private ArgumentCaptor<Departure> captor;
    private PostOffice PO;
    private List<Departure> departures = new ArrayList<>();

    @Before
    public void setUp() {
        Departure departure1 = new Departure();
        departure1.setType("testType");
        departure1.setDepartureDate("testDate");
        departure1.setPostOffice(PO);

        Departure departure2 = new Departure();
        departure2.setType("testType");
        departure2.setDepartureDate("testDate");
        departure2.setPostOffice(PO);

        departures.add(departure1);
        departures.add(departure2);

        when(departureRepository.findAll()).thenReturn(departures);
    }
    @Test
    public void getAllTest()
    {
        Departure departure1 = new Departure();
        departure1.setType("testType");
        departure1.setDepartureDate("testDate");
        departure1.setPostOffice(PO);

        Departure departure2 = new Departure();
        departure2.setType("testType");
        departure2.setDepartureDate("testDate");
        departure2.setPostOffice(PO);

        departures.add(departure1);
        departures.add(departure2);

        when(departureRepository.findAll()).thenReturn(departures);
        List<Departure> data = departureService.getAll();
        assertEquals(departures.get(0), data.get(0));
        assertEquals(departures.get(1), data.get(1));
    }

    @Test
    public void saveTest()
    {
        PO = new PostOffice();
        PO.setId(1);
        PO.setName("test");
        PO.setCityName("testCity");

        when(postOfficeRepository.findById(1)).thenReturn(Optional.of(PO));
        Departure departure1 = new Departure();
        departure1.setId(1);
        departure1.setType("testType");
        departure1.setDepartureDate("testDate");
        departure1.setPostOffice(PO);

        this.departureService.save(departure1, 1);
        verify(departureRepository).save(captor.capture());
        Departure captured = captor.getValue();
        assertEquals(departure1.getType(), captured.getType());
        assertEquals(departure1.getDepartureDate(), captured.getDepartureDate());
        assertEquals(departure1.getId(), captured.getId());
        assertEquals(departure1.getPostOffice().getId(), captured.getPostOffice().getId());
    }

    @Test
    public void deleteTest()
    {
        PO = new PostOffice();
        PO.setId(1);
        PO.setName("test");
        PO.setCityName("testCity");

        when(postOfficeRepository.findById(1)).thenReturn(Optional.of(PO));

        Departure departure1 = new Departure();
        departure1.setId(1);
        departure1.setType("testType");
        departure1.setDepartureDate("testDate");
        departure1.setPostOffice(PO);

        departureService.save(departure1, 1);
        verify(departureRepository).save(captor.capture());

        when(departureRepository.findById(departure1.getId())).thenReturn(Optional.of(departure1));

        departureService.delete(departure1.getId());
        verify(departureRepository, times(1)).deleteById(departure1.getId());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setPostOfficeService(PostOfficeService postOfficeService) {
        this.postOfficeService = postOfficeService;
    }
    @Autowired
    public void setDepartureService(DepartureService departureService) {
        this.departureService = departureService;
    }
}
