package com.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
public class Arts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aId;

    @Lob
    private byte[] image;

    private String description;
    private String sellerName;

    @ManyToOne
    @JoinColumn(name = "cat_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "art_id")
    private ArtDetails artDetails;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Seller seller;

    // getters, setters, constructors
}

