package com.example.testbot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrencyState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String currency;
    Double price;
}
