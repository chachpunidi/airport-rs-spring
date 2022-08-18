package ru.ch.airport.service.aircraft;

import ru.ch.airport.dto.AircraftDto;

import java.util.List;

public interface AircraftService {

    List<AircraftDto> findAircrafts();

    AircraftDto findAircraft(String code);

    Integer createAircraft(AircraftDto aircraft);
    Integer createAircrafts(List<AircraftDto> aircrafts);

    Integer updateAircraft(String code, AircraftDto aircraft);

    Integer deleteAircraft(String code);


}
