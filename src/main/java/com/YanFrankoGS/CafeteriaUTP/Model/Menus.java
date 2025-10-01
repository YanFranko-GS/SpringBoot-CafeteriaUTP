package com.YanFrankoGS.CafeteriaUTP.Model;




import com.YanFrankoGS.CafeteriaUTP.Model.Enums.CategoriasMenus;
import com.YanFrankoGS.CafeteriaUTP.Model.Enums.MenuDisponible;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "menus")
public class Menus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long idMenu;

    String nombre;
    String descripcion;
    double precio;

    @Enumerated(EnumType.STRING)
    CategoriasMenus categoria;

    @Enumerated(EnumType.STRING)
    MenuDisponible disponibilidad;

    String imagen_Url;



}
