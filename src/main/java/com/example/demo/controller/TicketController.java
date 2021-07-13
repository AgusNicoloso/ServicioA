package com.example.demo.controller;

import com.example.demo.models.Ticket;
import com.example.demo.service.TicketService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tickets")
public class TicketController {

  @Autowired
  private TicketService ticketService;

  @PostMapping
  public ResponseEntity<Ticket> saveTicket() {
    return ResponseEntity.ok(ticketService.save(new Ticket()));
  }

  @GetMapping("/{ticketNumber}")
  public ResponseEntity<Ticket> getTicketByTicketNumber(@PathVariable("ticketNumber") UUID ticketNumber) {
    return ResponseEntity.ok(ticketService.getTicketByTicketNumber(ticketNumber));
  }
}
