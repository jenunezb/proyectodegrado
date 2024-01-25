package laboratorio.servicios.interfaces;


import laboratorio.dto.LoginDTO;
import laboratorio.dto.TokenDTO;

public interface AutenticacionServicio {
    TokenDTO login(LoginDTO dto) throws Exception;
}