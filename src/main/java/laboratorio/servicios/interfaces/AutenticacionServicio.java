package laboratorio.servicios.interfaces;


import laboratorio.dto.CiudadGetDTO;
import laboratorio.dto.LoginDTO;
import laboratorio.dto.TokenDTO;

import java.util.List;

public interface AutenticacionServicio {
    TokenDTO login(LoginDTO dto) throws Exception;

   List<CiudadGetDTO> listarCiudades();
}