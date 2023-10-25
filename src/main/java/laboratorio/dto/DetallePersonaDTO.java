package laboratorio.dto;

import java.util.List;

//El detalle ingeniero lo utilizo para mostrar información detallada para modificar, también puedo ver en que obras está
// trabajando el ingeniero actualmente
public record DetallePersonaDTO(
        String Cedula,
        String nombre,
        String telefono,
        String ciudad,
        List<String> cr
) {
}
