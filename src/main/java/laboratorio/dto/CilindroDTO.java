package laboratorio.dto;

import java.time.LocalDate;

public record CilindroDTO(
        String cr,
        String muestra,
        String seccion,
        LocalDate fechaToma,
        LocalDate fechaFalla,
        int edad,
        float peso,
        float carga,
        FormaFalla formaFalla,
        String obra
) {
}
