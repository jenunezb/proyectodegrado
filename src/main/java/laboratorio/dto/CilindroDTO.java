package laboratorio.dto;

import java.time.LocalDate;

public record CilindroDTO(
        String cr,
        String muestra,
        String ensayo,
        LocalDate fechaToma,
        LocalDate fechaFalla,
        int edad,
        float peso,
        float carga,
        String obra,
        int id,
        float h,
        float h1,
        float d,
        Integer formaFalla
) {
}