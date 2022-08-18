package ru.ch.airport.service.aircraft;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ch.airport.SqlConsumer;
import ru.ch.airport.dto.AircraftDto;
import ru.ch.airport.dto.AirportDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AircraftServiceImpl implements AircraftService {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<AircraftDto> findAircrafts() {
        List<AircraftDto> aircraftList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String someSgl = "select * from aircrafts a " ;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(someSgl);
            while (rs.next()) {
                AircraftDto aircraft = new AircraftDto();
                aircraft.setCode(rs.getString("aircraft_code"));
                aircraft.setModel(rs.getString("model"));
                aircraft.setRange(rs.getLong("range"));
                aircraftList.add(aircraft);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return aircraftList;
    }

    @Override
    public AircraftDto findAircraft(String code) {
        try (Connection connection = dataSource.getConnection()) {
            String someSgl = String.format("select * from aircrafts a where a.aircraft_code = '%s'", code);
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(someSgl);
            boolean hasNext = rs.next();
            if (!hasNext) {
                return null;
            }
            AircraftDto aircraft = new AircraftDto();
            aircraft.setCode(rs.getString("aircraft_code"));
            aircraft.setModel(rs.getString("model"));
            return aircraft;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer createAircraft(AircraftDto aircraft) {
      try (Connection connection = dataSource.getConnection()) {
           int res = insertAircraft(connection, aircraft);
           return res;
      } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Integer createAircrafts(List<AircraftDto> aircrafts) {
        int rec =0;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try {
                for (AircraftDto aircraft : aircrafts) {
                    rec = rec + insertAircraft(connection, aircraft);
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
    private Integer insertAircraft(Connection connection, AircraftDto aircraft) throws SQLException {
        String someSgl = "INSERT INTO  aircrafts (aircraft_code, model, range)\n" +
                "values (?,?,?);";

        PreparedStatement stmt = connection.prepareStatement(someSgl);
        stmt.setString(1, aircraft.getCode());
        stmt.setString(2, aircraft.getModel());
        stmt.setLong(3, aircraft.getRange());

        int res = stmt.executeUpdate();
        return res;
    }

    @Override
    public Integer updateAircraft(String code, AircraftDto aircraft) {
        try (Connection connection = dataSource.getConnection()) {
            List<SqlConsumer<PreparedStatement>> paramSetConsumerList = new ArrayList<>();
            int inx = 0;
            String comma = "";
            String updSql = "UPDATE aircrafts SET ";

            if (aircraft.getRange() != null) {
                updSql += comma + " range = ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setLong(captureIdx, aircraft.getRange()));
                comma = ", ";
            }
            if (aircraft.getModel() != null) {
                updSql += comma + " model = ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, aircraft.getModel()));
                comma = ", ";
            }
            updSql += "where aircraft_code = ?";

            int captureIdx = ++inx;
            paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, aircraft.getCode()));

            PreparedStatement stmt = connection.prepareStatement(updSql);
            for (SqlConsumer<PreparedStatement>paramSetConsumer: paramSetConsumerList)  {
                paramSetConsumer.accept(stmt);
            }
            stmt.setString(inx, aircraft.getCode());
            System.out.println(updSql);
            int res = stmt.executeUpdate();
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public Integer deleteAircraft(String code){
        try (Connection connection = dataSource.getConnection()) {
            String someSgl = String.format("delete from aircrafts where aircraft_code = '%s'" , code);
            Statement stmt = connection.createStatement();
            int res = stmt.executeUpdate(someSgl);
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}