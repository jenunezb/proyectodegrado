package laboratorio.servicios.interfaces;


import laboratorio.dto.*;

import java.util.List;

public interface AutenticacionServicio {
    TokenDTO login(LoginDTO dto) throws Exception;

   List<CiudadGetDTO> listarCiudades();

    List<EmpresaDTO> listarEmpresas();

    List<SedeDTO> listarSedes();
}