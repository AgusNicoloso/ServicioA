package com.example.demo.service;

import com.example.demo.models.Ticket;
import com.example.demo.repository.TicketRepository;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class TicketService {

  @Autowired
  private TicketRepository ticketRepository;

  public Ticket save(Ticket ticket) {
    ticket.setTicketNumber(UUID.randomUUID());
    ticket.setDiscount(BigDecimal.valueOf(Math.random() * 100).intValue());
    return ticketRepository.save(ticket);
  }

  public Ticket getTicketByTicketNumber(UUID ticketNumber) {
    return ticketRepository.getTicketByTicketNumber(ticketNumber)
        .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, "No se encontro el ticket"));
  }


}
