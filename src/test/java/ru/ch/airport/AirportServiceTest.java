package ru.ch.airport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ch.airport.service.airport.AirportService;
import ru.ch.airport.service.airport.AirportServiceImpl;

@SpringBootTest
public class AirportServiceTest {
    private AirportService airportService = new AirportServiceImpl();

}
