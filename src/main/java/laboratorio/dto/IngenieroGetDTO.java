package laboratorio.dto;

import laboratorio.modelo.Ciudad;

public record IngenieroGetDTO(
        String cedula,
        String nombre,
        String ciudad,
        String telefono,
        String correo
) {
}
