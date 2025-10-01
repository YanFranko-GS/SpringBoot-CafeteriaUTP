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

@Entity
@Table(name = "detalle_pedidos")
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idDetallePedido;

    @ManyToOne
    @JoinColumn(name = "idPedido", nullable = false)
    Pedido idPedido;

    @ManyToOne
    @JoinColumn(name = "idMenu", nullable = false)
    Menus idMenu;

    int cantidad;
    double subtotal;

}
