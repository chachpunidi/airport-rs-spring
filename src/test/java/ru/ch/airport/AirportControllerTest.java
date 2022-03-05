package ru.ch.airport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.ch.airport.controller.AirportController;
import ru.ch.airport.dto.AircraftDto;
import ru.ch.airport.dto.AirportDto;
import ru.ch.airport.dto.PrimeAircraftDto;
import ru.ch.airport.service.aircraft.AircraftService;

@SpringBootTest
public class AirportControllerTest {
    private AirportController airportController;
    private AircraftService aircraftService;

    @Test
    public void testAirport() {
        AirportDto airportDtoVar = airportController.airports().get(0);
        Assertions.assertNotNull(airportDtoVar.getCode());
        Assertions.assertEquals("San-Diego", airportDtoVar.getName());
    }

    @Test
    public void testAircraft() {
        AircraftDto aircraftDtoVar = airportController.aircraft();
        Assertions.assertNotNull(aircraftDtoVar);
        Assertions.assertEquals("001", aircraftDtoVar.getCode());
        Assertions.assertEquals("Boeing", aircraftDtoVar.getManufacturer());
        Assertions.assertEquals("767", aircraftDtoVar.getModel());
    }

    @Test
    public void testAircraft1() {
        AircraftDto aircraftDtoVar = airportController.aircraft1();
        Assertions.assertNotNull(aircraftDtoVar);
        Assertions.assertNull(aircraftDtoVar.getCode());
        Assertions.assertEquals("Boeing1", aircraftDtoVar.getManufacturer());
        Assertions.assertEquals("767", aircraftDtoVar.getModel());
    }

    @Test
    public void testAircraft2() {
        AircraftDto aircraftDtoVar = (airportController.aircraft2());
        Assertions.assertNotNull(aircraftDtoVar);
        Assertions.assertEquals("001", aircraftDtoVar.getCode());
        Assertions.assertEquals(200L, aircraftDtoVar.getPlaces());
        Assertions.assertEquals("767", aircraftDtoVar.getModel());
    }

    @Test
    public void testHasAircrafts() {
        Assertions.assertTrue(aircraftService.hasAircrafts());
    }

    @Test
    public void testGetPrimeAircraft() {
        PrimeAircraftDto aircraftDtoVar = aircraftService.getPrimeAircraft();
        Assertions.assertNotNull(aircraftDtoVar);
        Assertions.assertEquals("prime-001", aircraftDtoVar.getCode());
        Assertions.assertEquals(200L, aircraftDtoVar.getPlaces());
        Assertions.assertEquals("767", aircraftDtoVar.getModel());
    }
}
