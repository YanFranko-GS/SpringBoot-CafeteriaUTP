package com.YanFrankoGS.CafeteriaUTP.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YanFrankoGS.CafeteriaUTP.Model.Carrito;
import com.YanFrankoGS.CafeteriaUTP.Model.Usuario;

@Repository
public interface CarritoRepository extends JpaRepository<Carrito, Long> {
    
    Optional<Carrito> findByUsuario(Usuario usuario);
    List<Carrito> findAllByUsuario(Usuario usuario);
}
