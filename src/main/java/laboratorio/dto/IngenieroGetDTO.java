package laboratorio.dto;

import laboratorio.modelo.Ciudad;

public record IngenieroGetDTO(
        String cedula,
        String nombre,
        Ciudad ciudad,
        String telefono,
        String correo
) {
}
