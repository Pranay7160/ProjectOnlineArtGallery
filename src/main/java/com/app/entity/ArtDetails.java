package com.app.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArtDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long artId;

    private String material;
    private BigDecimal price;
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    // getters, setters, constructors
}

