package org.sparta.spartaassignment2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.sparta.spartaassignment2.controller.ScheduleController;
import org.sparta.spartaassignment2.repository.ScheduleRepository;
import org.sparta.spartaassignment2.service.ScheduleService;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;



public class MockitoTest {

    @InjectMocks
    private ScheduleService scheduleService;

    @Mock
    private ScheduleRepository scheduleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void mockTest1() {
        String id = "1";
        String expectedResult = "12";


		// given
//        Mockito.when(ScheduleController.);

        // when

		// then
    }
}
