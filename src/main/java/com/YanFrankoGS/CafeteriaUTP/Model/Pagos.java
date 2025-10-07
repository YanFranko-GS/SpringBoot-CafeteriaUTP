package com.YanFrankoGS.CafeteriaUTP.Model;

import java.time.LocalDateTime;

import com.YanFrankoGS.CafeteriaUTP.Model.Enums.OpcionPago;

import jakarta.persistence.Column;
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


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pagos")
public class Pagos {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idPago;

    @Enumerated(EnumType.STRING)
    OpcionPago metodoPago;   // YAPE, tarjeta, efectivo y plin

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Column(nullable = false, unique = true)
    private String codigoPago;

    private LocalDateTime fecha;
    private double montoTotal;
}
