package com.example.demo.models;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Ticket {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private UUID ticketNumber;

  private Integer discount;
}
