package laboratorio.dto;


import laboratorio.modelo.TipoMuestraCilindro;

import java.time.LocalDate;

public record ReporteDTO(
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
        double densidad,
        double esfuerzoKg,
        double esfuerzoPsi,
        double esfuerzoMpa,
        double desarrollo,
        String obs,
        String descripcion,
        int resistencia,
        float h,
        float h1,
        float d
) {
}

