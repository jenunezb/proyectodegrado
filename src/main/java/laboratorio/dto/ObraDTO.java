package laboratorio.dto;

import laboratorio.modelo.Empresa;
import laboratorio.modelo.Ciudad;

import java.time.LocalDate;

public record ObraDTO(
        String direccion,
        String nombre,
        String telefono,
        String ciudad,
        String empresa,
        String cr
) {
}
