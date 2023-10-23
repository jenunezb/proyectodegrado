package laboratorio.dto;

import laboratorio.modelo.Empresa;
import laboratorio.modelo.ensayo.enums.Ciudad;

import java.time.LocalDate;

public record ObraDTO(
        String direccion,
        LocalDate fechaInicio,
        String nombre,
        String telefono,
        Ciudad ciudad,
        Empresa empresa,
        String cr
) {
}
