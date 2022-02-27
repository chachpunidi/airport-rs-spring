package ru.ch.airport.service.airport;

import ru.ch.airport.dto.AirportDto;

public class AirportServiceImpl implements AirportService {
    @Override
    public AirportDto airport() {

        AirportDto ret = new AirportDto();
        ret.setCode("San-Diego");
        ret.setName("San-Diego");

        return ret;
    }
}
