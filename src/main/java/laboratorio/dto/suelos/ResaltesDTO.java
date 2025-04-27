package laboratorio.dto.suelos;

import java.util.Date;

public record ResaltesDTO(
        int codigo,
        String norma,
        Date fechaMedicion,
        double altura,
        double espaciamiento,
        boolean espaciamientoLongitudinalGrafiles,
        double separacion,
        int angulo,
        boolean resaltesDireccionInversa,
        double longitudProbeta,
        double pesoProbeta,
        int muestra,
        String obra
) {

}
