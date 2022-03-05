package ru.ch.airport.service.airport;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ch.airport.dto.AirportDto;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAirportServiceImpl implements AirportService {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<AirportDto> airports() {
        List<AirportDto> airports = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String someSgl = "select * from airports a " ;
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(someSgl);

            while (rs.next()) {
                AirportDto airport = new AirportDto();
                airport.setCode(rs.getString("airport_code"));
                airport.setName(rs.getString("airport_name"));
                airports.add(airport);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return airports;
    }
}
