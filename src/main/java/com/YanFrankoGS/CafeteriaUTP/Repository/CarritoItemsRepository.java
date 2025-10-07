package com.YanFrankoGS.CafeteriaUTP.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YanFrankoGS.CafeteriaUTP.Model.Carrito;
import com.YanFrankoGS.CafeteriaUTP.Model.Carrito_items;

@Repository
public interface CarritoItemsRepository extends JpaRepository<Carrito_items, Long> {

    
    List<Carrito_items> findByCarrito(Carrito carrito);

    
    void deleteByCarrito(Carrito carrito);
}
