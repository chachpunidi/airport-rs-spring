package ru.ch.airport.service.tickets;

import ru.ch.airport.dto.TicketsDto;

import java.util.List;

public interface TicketsService {
    List<TicketsDto> findTickets();

    TicketsDto findTicket(String ticketNo);

    Integer createTicket(TicketsDto ticket);

    Integer updateTicket(String ticketNo, TicketsDto ticket);

    Integer deleteTickets(String ticketNo);
    Integer createTickets(List<TicketsDto> tickets);

}
