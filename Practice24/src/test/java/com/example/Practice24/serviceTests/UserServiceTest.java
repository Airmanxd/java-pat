package com.example.Practice24.serviceTests;

import com.example.Practice24.models.User;
import com.example.Practice24.models.UserDTO;
import com.example.Practice24.repositories.DepartureRepository;
import com.example.Practice24.repositories.PostOfficeRepository;
import com.example.Practice24.repositories.UserRepository;
import com.example.Practice24.services.DepartureService;
import com.example.Practice24.services.PostOfficeService;
import com.example.Practice24.services.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
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
    private ArgumentCaptor<User> captor;

    @Before
    public void setUp() {
        User user = new User();
        user.setUsername("user");
        user.setPassword("password");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
    }
    @Test
    public void register()
    {
        UserDTO request = new UserDTO("testUser", "123456", "123456");
        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(request.getPassword());

        boolean test = userService.register(request, "cooker");

        assertTrue(test);
        verify(userRepository).save(captor.capture());
        User captured = captor.getValue();
        assertEquals(request.getUsername(), captured.getUsername());

    }
    @Test
    public void loadUserByUsername()
    {
        UserDTO user = new UserDTO();
        user.setUsername("user");
        user.setPassword("123");
        user.setMatchingPassword("123");
        boolean test = userService.register(user, "cookie");
        assertTrue(test);
        User test2 = new User();
        test2.setUsername("user");
        Mockito.doReturn(test2).when(userRepository).findByUsername("user");
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