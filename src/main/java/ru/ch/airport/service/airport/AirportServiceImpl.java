package ru.ch.airport.service.airport;

import ru.ch.airport.dto.AirportDto;

import java.util.List;

public class AirportServiceImpl implements AirportService {
    @Override
    public List<AirportDto> airports() {

        AirportDto ret = new AirportDto();
        ret.setCode("San-Diego");
        ret.setName("San-Diego");

        return List.of(ret);
    }
}
