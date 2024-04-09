package laboratorio.dto;

import laboratorio.modelo.Ciudad;

public record SedeDTO (
        Ciudad ciudad,
        String direccion,
        String telefono
){
}
