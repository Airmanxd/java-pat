package com.example.Practice24;

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
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PostOfficeServiceTest {
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
    private ArgumentCaptor<PostOffice> captor;
    private PostOffice PO;
    private List<PostOffice> postOffices = new ArrayList<>();

    @Test
    public void saveTest()
    {
        PO = new PostOffice();
        PO.setId(1);
        PO.setName("Test");
        PO.setCityName("TestCity");
        postOfficeService.save(PO);

        verify(postOfficeRepository).save(captor.capture());
        PostOffice capture = captor.getValue();

        assertEquals(PO.getName(), capture.getName());
        assertEquals(PO.getCityName(), capture.getCityName());
        assertEquals(PO.getId(), capture.getId());
    }

    @Test
    public void removeTest()
    {
        PO = new PostOffice();
        PO.setId(1);
        PO.setName("Test");
        PO.setCityName("TestCity");
        postOfficeRepository.save(PO);

        verify(postOfficeRepository).save(captor.capture());
        PostOffice capture = captor.getValue();

        postOfficeService.remove(capture.getId());
        verify(postOfficeRepository, times(1)).deleteById(capture.getId());
    }

    @Test
    public void getTest()
    {
        PO = new PostOffice();
        PO.setId(1);
        PO.setName("Test");
        PO.setCityName("TestCity");
        postOffices.add(PO);
        PO = new PostOffice();
        PO.setId(2);
        PO.setName("Test2");
        PO.setCityName("TestCity2");
        postOffices.add(PO);

        when(postOfficeRepository.findAll()).thenReturn(postOffices);

        List<PostOffice> testGet = postOfficeService.getAll();
        assertEquals(postOffices.get(0), testGet.get(0));
        assertEquals(postOffices.get(1), testGet.get(1));
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
