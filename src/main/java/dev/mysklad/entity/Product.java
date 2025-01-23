package dev.mysklad.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String description;
    private BigDecimal prise;
    private boolean availability = false;

    public Product(String name, String description, BigDecimal prise, boolean availability) {
        this.name = name;
        this.description = description;
        this.prise = prise;
        this.availability = availability;
    }
}
