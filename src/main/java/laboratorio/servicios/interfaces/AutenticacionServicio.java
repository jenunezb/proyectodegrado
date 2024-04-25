package laboratorio.servicios.interfaces;


import laboratorio.dto.*;
import laboratorio.dto.suelos.SuelosDTO;

import java.util.List;

public interface AutenticacionServicio {
    TokenDTO login(LoginDTO dto) throws Exception;

   List<CiudadGetDTO> listarCiudades();

    List<EmpresaDTO> listarEmpresas();

    List<SedeDTO> listarSedes();

    List<ObraDTO> listarObras();

    List<CilindrosList> listarCilindros() throws Exception;
}