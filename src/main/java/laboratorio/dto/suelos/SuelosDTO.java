package laboratorio.dto.suelos;

import java.time.LocalDate;

public record SuelosDTO(
        String cr,
        int numerodeMuestra,
        String nombreObra,
        LocalDate fechaProgramada
) {
}
