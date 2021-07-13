package com.example.demo.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.demo.models.Ticket;
import com.example.demo.service.TicketService;
import com.google.gson.Gson;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(TicketController.class)
class TicketControllerTest {

  @Autowired
  MockMvc mockMvc;
  @MockBean
  private TicketService ticketService;

  private Ticket ticket;

  private Gson gson;

  @BeforeEach
  void setUp() {
    ticket = new Ticket();
    ticket.setTicketNumber(UUID.randomUUID());
    ticket.setDiscount(BigDecimal.valueOf(Math.random() * 100).intValue());
    gson = new Gson();
  }

  @Test
  void createTicket() throws Exception {
    ResultActions resultActions = mockMvc
        .perform(MockMvcRequestBuilders.
            post("/tickets").
            contentType(MediaType.APPLICATION_JSON).
            contentType(gson.toJson(ticket))).andExpect(status().isOk());
  }

  @Test
  void getTicketByTicketNumber() throws Exception {
    when(ticketService.getTicketByTicketNumber(ticket.getTicketNumber())).thenReturn(ticket);

    ResultActions resultActions = mockMvc
        .perform(MockMvcRequestBuilders.
            get("/tickets/" + ticket.getTicketNumber())).
            andExpect(status().isOk()).
            andExpect(content().contentType(MediaType.APPLICATION_JSON)).
            andExpect(jsonPath("discount").value(ticket.getDiscount()));

  }
}
