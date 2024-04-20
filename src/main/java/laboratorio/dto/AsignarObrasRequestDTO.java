package laboratorio.dto;

import java.util.List;

public record AsignarObrasRequestDTO(
        int codigoUsuario,
        List<ObraDTO> listaObras
) {
}
