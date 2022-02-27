package ru.ch.airport.service.aircraft;


import ru.ch.airport.dto.AircraftDto;
import ru.ch.airport.dto.PrimeAircraftDto;

public class AircraftServiceIml implements AircraftService {
    @Override
    public AircraftDto aircraft() {
        AircraftDto rt =new AircraftDto();
        rt.setCode("001");
        rt.setManufacturer("Boeing");
        rt.setModel("767");
        return rt;
    }
    @Override
    public AircraftDto aircraft1() {
        AircraftDto rt = new AircraftDto();

        rt.setManufacturer("Boeing1");
        rt.setModel("767");
        return rt;
    }

    @Override
    public AircraftDto aircraft2() {
        AircraftDto rt = new AircraftDto();
        rt.setPlaces(200L);
        rt.setCode("001");

        rt.setModel("767");
        return rt;
        
    }

    @Override
    public boolean hasAircrafts() {
        return true;
    }

    @Override
    public PrimeAircraftDto getPrimeAircraft() {
        PrimeAircraftDto rt = new PrimeAircraftDto();
        rt.setCode("prime-001");
        rt.setModel("767");
        rt.setPlaces(200L);
        return rt;

    }
}

