package com.h0lmes.fakeshop.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_sequence_generator")
    @SequenceGenerator(name = "cart_sequence_generator")
    private Long id;

    @ManyToMany(cascade = {CascadeType.ALL}, targetEntity = Product.class)
    private List<Product> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public Cart setId(Long id) {
        this.id = id;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Cart setProducts(List<Product> products) {
        this.products = products;
        return this;
    }
}
