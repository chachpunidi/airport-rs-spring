package ru.ch.airport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.ch.airport.dto.TicketsDto;
import ru.ch.airport.service.tickets.TicketsService;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketsController {

    private TicketsService ticketsService;

    @Autowired
    public TicketsController(TicketsService ticketsService) {
        this.ticketsService = ticketsService;
    }


    @GetMapping
    public List<TicketsDto> tickets() {
        return ticketsService.findTickets();
    }

    @GetMapping("/{ticketNo}")
    public TicketsDto ticket(@PathVariable(value = "ticketNo") String ticketNo) {
        return ticketsService.findTicket(ticketNo);
    }

    @PutMapping
    public Integer createTicket(@RequestBody TicketsDto ticket) {

        return  ticketsService.createTicket(ticket);
    }

    @PutMapping("/batch")
    public Integer createTickets(@RequestBody List<TicketsDto> tickets) {

        return ticketsService.createTickets(tickets);
    }


    @PatchMapping("/{ticketNo}")
    public Integer updateTickets(@PathVariable(value = "ticketNo") String ticketNo, @RequestBody TicketsDto ticket) {
    return     ticketsService.updateTicket(ticketNo, ticket);
    }

    @DeleteMapping("/{ticketNo}")
    public Integer deleteTickets(@PathVariable(value = "ticketNo") String ticketNo) {
        return ticketsService.deleteTickets(ticketNo);
    }
}