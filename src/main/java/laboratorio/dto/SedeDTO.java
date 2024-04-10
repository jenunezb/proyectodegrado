package laboratorio.dto;

import laboratorio.modelo.Ciudad;

public record SedeDTO (
        String ciudad,
        String direccion,
        String telefono
){
}
