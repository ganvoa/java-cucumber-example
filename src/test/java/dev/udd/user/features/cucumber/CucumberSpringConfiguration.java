package dev.udd.user.features.cucumber;

import org.springframework.boot.test.context.SpringBootTest;

import dev.udd.user.infrastructure.persistence.InMemoryUserRepository;
import io.cucumber.java.Before;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberSpringConfiguration {

    @Before
    public void before() {

        InMemoryUserRepository.USERS.clear();
    }

}
