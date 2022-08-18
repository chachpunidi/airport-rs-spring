package ru.ch.airport.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.ch.airport.service.aircraft.AircraftService;
import ru.ch.airport.service.aircraft.AircraftServiceImpl;
import ru.ch.airport.service.airport.*;
import ru.ch.airport.service.tickets.TicketsService;
import ru.ch.airport.service.tickets.TicketsServiceImpl;

@Configuration
public class ApplicationConfig {

    @Bean
    public AirportService airportService() {
        return new DatabaseAirportServiceImpl();
    }
    @Bean
    public AircraftService aircraftService() {
        return new AircraftServiceImpl();
    }
    @Bean
    public TicketsService ticketsService() {
        return  new TicketsServiceImpl();
    }
}
