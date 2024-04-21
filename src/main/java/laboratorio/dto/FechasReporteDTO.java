package laboratorio.dto;

import java.time.LocalDate;

public record FechasReporteDTO(
        LocalDate fechaInicio,
        LocalDate fechaFin,
        String cr
) {
}