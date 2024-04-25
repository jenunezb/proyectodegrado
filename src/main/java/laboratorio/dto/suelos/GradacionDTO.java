package laboratorio.dto.suelos;

import java.time.LocalDate;
import java.util.List;

public record GradacionDTO(
   String cr,

   int codigoMuestra,

   LocalDate fechaEnsayo,

   List<Double> resultados,

   List<String> tamices
) {
}