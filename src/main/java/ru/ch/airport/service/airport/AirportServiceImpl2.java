package ru.ch.airport.service.airport;

import ru.ch.airport.dto.AirportDto;

import java.util.List;

public class AirportServiceImpl2 implements AirportService {

    private boolean isTest;

    public AirportServiceImpl2(boolean isTest) {
        this.isTest = isTest;
    }

    @Override
    public List<AirportDto> airports() {

        if (isTest) {
            AirportDto ret = new AirportDto();
            ret.setCode("San-DiegoTest");
            ret.setName("San-DiegoTest");
            return List.of(ret);
        } else {
            AirportDto ret = new AirportDto();
            ret.setCode("San-DiegoProd");
            ret.setName("San-DiegoProd");
            return List.of(ret);
        }
    }
}
