package laboratorio.dto;

import laboratorio.modelo.Ciudad;

public record IngenieroPOSTDTO(
        String cedula,
        String nombre,
        Ciudad ciudad,
        String telefono,
        String password,
        String correo
) {
}
