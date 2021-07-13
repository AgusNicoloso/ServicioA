package com.example.demo.service;

import static org.mockito.Mockito.when;

import com.example.demo.models.Ticket;
import com.example.demo.repository.TicketRepository;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

@SpringBootTest
class TicketServiceTest {

  @Mock
  private TicketRepository ticketRepository;

  @InjectMocks
  private TicketService ticketService;

  private Ticket ticket;

  @BeforeEach
  void setUp() {
    ticket = new Ticket();
    ticket.setTicketNumber(UUID.randomUUID());
    ticket.setDiscount(BigDecimal.valueOf(Math.random() * 100).intValue());
  }

  @Test
  void getTicketByTicketNumber() {
    when(ticketRepository.getTicketByTicketNumber(ticket.getTicketNumber())).thenReturn(Optional.of(ticket));

    Ticket expectedTicket = ticketService.getTicketByTicketNumber(ticket.getTicketNumber());

    Assertions.assertEquals(expectedTicket.getTicketNumber(), ticket.getTicketNumber());

  }

  @Test
  void saveTicket() {
    when(ticketRepository.save(ticket)).thenReturn(ticket);

    Ticket expectedTicket = ticketService.save(ticket);

    Assertions.assertEquals(expectedTicket.getTicketNumber(), ticket.getTicketNumber());
    Assertions.assertEquals(expectedTicket.getDiscount(), ticket.getDiscount());

  }

  @Test
  void getTicketByTicketNumberNotFound() {
    when(ticketRepository.getTicketByTicketNumber(ticket.getTicketNumber()))
        .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND, "No se encontro el ticket"));
    Assertions.assertThrows(HttpClientErrorException.class, () -> ticketService.getTicketByTicketNumber(ticket.getTicketNumber()));

  }

}
