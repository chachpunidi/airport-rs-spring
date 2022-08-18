package ru.ch.airport.service.airport;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ch.airport.SqlConsumer;
import ru.ch.airport.dto.AirportDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseAirportServiceImpl implements AirportService {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<AirportDto> findAirports() {
        List<AirportDto> airportsList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String someSgl = "select * from airports a " ;
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(someSgl);

            while (rs.next()) {
                AirportDto airport = new AirportDto();
                airport.setCode(rs.getString("airport_code"));
                airport.setName(rs.getString("airport_name"));
                airportsList.add(airport);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return airportsList;
    }

    public AirportDto findAirport(String code) {
        try (Connection connection = dataSource.getConnection()) {
            String someSgl = String.format("select * from airports a where a.airport_code = '%s'", code);
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(someSgl);
            boolean hasNext = rs.next();
            if (!hasNext) {
                return null;
            }
            AirportDto airport = new AirportDto();
            airport.setCode(rs.getString("airport_code"));
            airport.setName(rs.getString("airport_name"));
            return airport;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Integer createAirport(AirportDto airport) {
        try (Connection connection = dataSource.getConnection()){
            int res = insertAirport(connection, airport);
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }}

    @Override
    public Integer createAirports(List<AirportDto> airports) {
        int rec = 0;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try {
                for (AirportDto airport : airports) {
                    rec = rec + insertAirport(connection, airport);
                }
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rec;
    }



    @Override
    public Integer updateAirport(String code, AirportDto airport) {
        try (Connection connection = dataSource.getConnection()) {
            List<SqlConsumer<PreparedStatement>> paramSetConsumerList = new ArrayList<>();
            int inx = 0;
            String comma = "";
            String updSql = "UPDATE airports SET ";
            if (airport.getName() != null) {
                updSql += comma + " airport_name = ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, airport.getName()));
                comma = ", ";
            }
            if (airport.getCity() != null) {
                updSql += comma + " city = ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, airport.getCity()));
                comma = ", ";
            }
            if (airport.getLongitude() != null) {
                updSql += comma + " longitude = ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setFloat(captureIdx, airport.getLongitude()));
                comma = ", ";
            }
            if (airport.getLatitude() != null) {
                updSql += comma + " latitude = ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setFloat(captureIdx, airport.getLatitude()));
                comma = ", ";
            }
            if (airport.getTimezone() != null) {
                updSql += comma + " timezone = ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, airport.getTimezone()));
                comma = ", ";
            }
            updSql += " where airport_code = ?";
            int captureIdx = ++inx;
            paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, airport.getCode()));

            PreparedStatement stmt = connection.prepareStatement(updSql);
            for (SqlConsumer<PreparedStatement>paramSetConsumer: paramSetConsumerList)  {
                paramSetConsumer.accept(stmt);
            }
            stmt.setString(inx, airport.getCode());
            System.out.println(updSql);
            int res = stmt.executeUpdate();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Integer insertAirport(Connection connection, AirportDto airport) throws SQLException {
        String someSgl = "INSERT INTO  airports (airport_code, airport_name, city, longitude, latitude, timezone )\n" +
                "values (?,?,?,?,?,?);";

        PreparedStatement stmt = connection.prepareStatement(someSgl);
        stmt.setString(1, airport.getCode());
        stmt.setString(2, airport.getName());
        stmt.setString(3, airport.getCity());
        stmt.setFloat(4, airport.getLongitude());
        stmt.setFloat(5, airport.getLatitude());
        stmt.setString(6, airport.getTimezone());

        int res = stmt.executeUpdate();
        return res;
    }
    @Override
    public Integer deleteAirport(String code){
        try (Connection connection = dataSource.getConnection()) {
            String someSgl = String.format("delete from airports where airport_code = '%s'" , code);
            Statement stmt = connection.createStatement();
            int res = stmt.executeUpdate(someSgl);
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

