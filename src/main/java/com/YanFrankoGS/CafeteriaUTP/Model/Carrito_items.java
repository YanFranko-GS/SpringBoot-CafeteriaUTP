package com.YanFrankoGS.CafeteriaUTP.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "carrito_items")
@Entity

public class Carrito_items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idCarrito_items;

    @ManyToOne
    @JoinColumn(name = "idCarrito", nullable = false)
    Carrito carrito;

    @ManyToOne
    @JoinColumn(name = "idMenu", nullable = false)
    Menus menu;

    int cantidad;
    double subtotal;

}
