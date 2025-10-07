package com.YanFrankoGS.CafeteriaUTP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YanFrankoGS.CafeteriaUTP.Model.DetallePedido;
import com.YanFrankoGS.CafeteriaUTP.Model.Pedido;
import com.YanFrankoGS.CafeteriaUTP.Model.Menus;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {


    List<DetallePedido> findByPedido(Pedido pedido);
    List<DetallePedido> findByPedido_IdPedido(Long idPedido);
    List<DetallePedido> findByMenu(Menus menu);
    void deleteByPedido(Pedido pedido);
}
