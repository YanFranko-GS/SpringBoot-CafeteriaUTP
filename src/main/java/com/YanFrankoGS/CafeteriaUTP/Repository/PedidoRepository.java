package com.YanFrankoGS.CafeteriaUTP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YanFrankoGS.CafeteriaUTP.Model.Pedido;
import com.YanFrankoGS.CafeteriaUTP.Model.Usuario;
import com.YanFrankoGS.CafeteriaUTP.Model.Enums.EstadoPedido;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
   
    List<Pedido> findByUsuario(Usuario usuario);
    List<Pedido> findByEstado(EstadoPedido estado);
    List<Pedido> findByFecha(LocalDate fecha);
    List<Pedido> findByUsuarioAndEstado(Usuario usuario, EstadoPedido estado);
}
