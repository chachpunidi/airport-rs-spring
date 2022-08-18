package ru.ch.airport.service.airport;

import ru.ch.airport.dto.AirportDto;

import java.util.List;

public interface AirportService {
     List<AirportDto> findAirports();
     AirportDto findAirport(String code);
     Integer createAirport(AirportDto airport);
     Integer createAirports(List<AirportDto> airports);
     Integer deleteAirport(String code);
     Integer updateAirport(String code, AirportDto airport);
}
