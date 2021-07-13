package com.example.demo.repository;

import com.example.demo.models.Ticket;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
  Optional<Ticket> getTicketByTicketNumber(UUID ticketNumber);
}
