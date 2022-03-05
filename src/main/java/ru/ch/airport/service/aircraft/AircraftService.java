package ru.ch.airport.service.aircraft;

import ru.ch.airport.dto.AircraftDto;
import ru.ch.airport.dto.PrimeAircraftDto;

public interface AircraftService {
    /**
     * Получить самолеты.
     * @return самолеты
     */
    AircraftDto aircraft();

    AircraftDto aircraft1();

    AircraftDto aircraft2();

    boolean hasAircrafts();

    PrimeAircraftDto getPrimeAircraft();
}
