package com.YanFrankoGS.CafeteriaUTP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YanFrankoGS.CafeteriaUTP.Model.Pagos;
import com.YanFrankoGS.CafeteriaUTP.Model.Pedido;
import com.YanFrankoGS.CafeteriaUTP.Model.Enums.OpcionPago;

import java.util.List;
import java.util.Optional;

@Repository
public interface PagosRepository extends JpaRepository<Pagos, Long> {

    Optional<Pagos> findByCodigoPago(String codigoPago);
    List<Pagos> findByPedido(Pedido pedido);
    List<Pagos> findByMetodoPago(OpcionPago metodoPago);
    boolean existsByCodigoPago(String codigoPago);
}
