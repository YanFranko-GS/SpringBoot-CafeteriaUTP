package com.YanFrankoGS.CafeteriaUTP.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.YanFrankoGS.CafeteriaUTP.Model.Enums.EstadoPedido;


import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPedido;

    // Relación ManyToOne con Usuario
    @ManyToOne
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    private LocalDate fecha;
    private LocalTime hora;

    
    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    
}
