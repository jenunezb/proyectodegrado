package laboratorio.dto;

import laboratorio.modelo.TipoMuestraCilindro;

import java.time.LocalDate;

public record CompresionCilindrosGetDTO(
        String cr,
        TipoMuestraCilindro tipoMuestraCilindro,
        String seccion,
        String numeroMuestra,
        LocalDate fechaToma,
        int resistencia,
        int cantidad,
        String descripcion,
        String observaciones
) {
}
