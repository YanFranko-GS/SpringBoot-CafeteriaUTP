package com.YanFrankoGS.CafeteriaUTP.DTO;

import com.YanFrankoGS.CafeteriaUTP.Model.Enums.CategoriasMenus;
import com.YanFrankoGS.CafeteriaUTP.Model.Enums.MenuDisponible;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenusDTO {

    private Long idMenu;
    private String nombre;
    private String descripcion;
    private double precio;
    private CategoriasMenus categoria;
    private MenuDisponible disponibilidad;
    private String imagenUrl;
}
