package ru.ch.airport.service.tickets;

import org.springframework.beans.factory.annotation.Autowired;
import ru.ch.airport.SqlConsumer;
import ru.ch.airport.dto.AircraftDto;
import ru.ch.airport.dto.AirportDto;
import ru.ch.airport.dto.TicketsDto;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketsServiceImpl implements TicketsService {

    @Autowired
    private DataSource dataSource;

    @Override
    public List<TicketsDto> findTickets() {
        List<TicketsDto> ticketsList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String someSgl = "select * from tickets t limit 100" ;
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(someSgl);
            while (rs.next()) {
                TicketsDto ticket = new TicketsDto();
                ticket.setTicketNo(rs.getString("ticket_no"));
                ticket.setBookRef(rs.getString("book_ref"));
                ticket.setPassengerId(rs.getString("passenger_id"));
                ticket.setPassengerName(rs.getString("passenger_name"));
                ticket.setContactData(rs.getString("contact_data"));

                ticketsList.add(ticket);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ticketsList;
 }

    @Override
    public TicketsDto findTicket(String ticketNo) {
        try (Connection connection = dataSource.getConnection()) {
            String someSgl = String.format("select * from tickets t where t.ticket_no = '%s'", ticketNo);
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery(someSgl);
            boolean hasNext = rs.next();
            if (!hasNext) {
                return null;
            }
            TicketsDto ticket = new TicketsDto();
            ticket.setTicketNo(rs.getString("ticket_no"));
            ticket.setBookRef(rs.getString("book_ref"));
            ticket.setPassengerId(rs.getString("passenger_id"));
            ticket.setPassengerName(rs.getString("passenger_name"));
            ticket.setContactData(rs.getString("contact_data"));
                 return ticket;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Integer insertTicket(Connection connection, TicketsDto ticket) throws SQLException {
        String someSgl = "INSERT INTO  tickets (ticket_no, book_ref, passenger_id, passenger_name, contact_data)\n" +
                "values (?,?,?,?,?::json);";

        PreparedStatement stmt = connection.prepareStatement(someSgl);
        stmt.setString(1, ticket.getTicketNo());
        stmt.setString(2, ticket.getBookRef());
        stmt.setString(3, ticket.getPassengerId());
        stmt.setString(4, ticket.getPassengerName());
        stmt.setString(5, ticket.getContactData());
        int res = stmt.executeUpdate();
        return res;
    }
    @Override
    public Integer createTicket(TicketsDto ticket) {
        try (Connection connection = dataSource.getConnection()) {

            int res = insertTicket(connection, ticket);
            return res;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer createTickets(List<TicketsDto> tickets) {
        int rec = 0;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);

            try {
                for (TicketsDto ticket : tickets) {
                    rec = rec + insertTicket(connection, ticket);
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
    public Integer updateTicket(String ticketNo , TicketsDto ticket) {
        try (Connection connection = dataSource.getConnection()) {
            List<SqlConsumer<PreparedStatement>> paramSetConsumerList = new ArrayList<>();
            int inx = 0;
            String comma = "";
            String updSql = "UPDATE tickets SET ";
            if (ticket.getBookRef() != null) {
                updSql += comma + " book_ref = ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, ticket.getBookRef()));
                comma = ", ";
            }
            if (ticket.getPassengerId() != null) {
                updSql += comma + " passenger_id = ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, ticket.getPassengerId()));
                comma = ", ";
            }
            if (ticket.getPassengerName() != null) {
                updSql += comma + " passenger_name = ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, ticket.getBookRef()));
                comma = ", ";
            }
            if (ticket.getContactData() != null) {
                updSql += comma + " contact_data= ?";
                int captureIdx = ++inx;
                paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, ticket.getContactData()));
                comma = ", ";
            }

            updSql += " where ticket_no = ?";
                    int captureIdx = ++inx;
                    paramSetConsumerList.add(pStmt -> pStmt.setString(captureIdx, ticket.getTicketNo()));
            PreparedStatement stmt = connection.prepareStatement(updSql);
            for (SqlConsumer<PreparedStatement>paramSetConsumer: paramSetConsumerList)  {
                paramSetConsumer.accept(stmt);
            }
            stmt.setString(inx, ticket.getTicketNo());
            System.out.println(updSql);
            int res = stmt.executeUpdate();
            return res;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    @Override
    public Integer deleteTickets(String ticketNo) {try (Connection connection = dataSource.getConnection()) {
        String someSgl = String.format("delete from tickets where ticket_no = '%s'" , ticketNo);
        Statement stmt = connection.createStatement();
        int res = stmt.executeUpdate(someSgl);
        return res;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    }
    }


