package laboratorio.dto;

import java.time.LocalDate;

public record CilindrosList(
        String cr,
        String numeroMuestra,
        String nombreObra,
        String seccion,
        LocalDate fechaToma

) {
}
