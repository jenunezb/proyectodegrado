package laboratorio.dto.vigas;

import java.time.LocalDate;

public record VigasGetDTO(
        String cr,
        String muestra,
        String ensayo,
        LocalDate fechaToma,
        LocalDate fechaFalla,
        Integer edad,
        Float carga,
        String obra,
        Integer id,
        Float ancho,
        Float fondo,
        Integer l,
        Integer a
) {
}
