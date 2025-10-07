package com.YanFrankoGS.CafeteriaUTP.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.YanFrankoGS.CafeteriaUTP.Model.Menus;
import com.YanFrankoGS.CafeteriaUTP.Model.Enums.CategoriasMenus;
import com.YanFrankoGS.CafeteriaUTP.Model.Enums.MenuDisponible;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenusRepository extends JpaRepository<Menus, Long> {

    Optional<Menus> findByNombre(String nombre);
    List<Menus> findByCategoria(CategoriasMenus categoria);
    List<Menus> findByDisponibilidad(MenuDisponible disponibilidad);
    List<Menus> findByPrecioBetween(double minPrecio, double maxPrecio);
    boolean existsByNombre(String nombre);
}
