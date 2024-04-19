package laboratorio.dto;

import java.time.LocalDate;

public record OrdenDTO(
        LocalDate fecha,
        String cr
) {
}
