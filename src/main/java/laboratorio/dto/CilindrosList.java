package laboratorio.dto;

import java.time.LocalDate;
import laboratorio.modelo.TipoMuestraCilindro;

public record CilindrosList(
        String cr,
        String numeroMuestra,
        String nombreObra,
        String seccion,
        LocalDate fechaToma,
        String tipoMuestraCilindro
) {

}

