package laboratorio.dto;

import laboratorio.modelo.TipoMuestraCilindro;

import java.time.LocalDate;

public record CompresionCilindrosDTO(
        String cr,
        TipoMuestraCilindro tipoMuestraCilindro,
        String seccion,
        String numeroMuestra,
        int resistencia,
        int cantidad,
        String descripcion,
        String observaciones,
        LocalDate fechaToma
) {
}
