package laboratorio.dto.suelos;

import java.time.LocalDate;

public record RegistroSuelosDto(
        String cr,
        String muestra,
        String material,
        String ensayo,
        String localizacion,
        String cantera,
        LocalDate fechaToma,
        LocalDate fechaRecibido,
        String descripcion
) {
}
