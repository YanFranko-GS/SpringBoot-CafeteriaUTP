package com.YanFrankoGS.CafeteriaUTP.DTO;

import com.YanFrankoGS.CafeteriaUTP.Model.Enums.Erol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RolDTO {

    private Long idRol;
    private Erol nombre;
}
