package com.carrental;

import com.carrental.controller.HomeController;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CarRentalProjectApplicationTests {

    @Autowired
    private HomeController controller;
    
    @Test
    void contextLoads() {
        System.out.println("contextLoads");
        assertThat(controller).isNotNull();
    }

}
