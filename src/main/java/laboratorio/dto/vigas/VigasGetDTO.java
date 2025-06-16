package laboratorio.dto.vigas;

import java.time.LocalDate;

public record VigasGetDTO(
        String cr,
        String muestra,
        String ensayo,
        LocalDate fechaToma,
        LocalDate fechaFalla,
        int edad,
        float carga,
        String obra,
        int id,
        float ancho,
        float fondo,
        int l,
        int a
) {
}
