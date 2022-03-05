package ru.ch.airport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ch.airport.service.aircraft.AircraftService;
import ru.ch.airport.service.aircraft.AircraftServiceIml;
import ru.ch.airport.service.airport.*;

@Configuration
public class ApplicationConfig {

    @Bean
    public AirportService airportService() {
        return new DatabaseAirportServiceImpl();
    }
    @Bean
    public AircraftService aircraftService() {
        return new AircraftServiceIml();
    }
}
