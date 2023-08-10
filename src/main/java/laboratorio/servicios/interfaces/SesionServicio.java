package laboratorio.servicios.interfaces;


import laboratorio.dto.SesionDTO;
import laboratorio.dto.TokenDTO;

public interface SesionServicio {

    TokenDTO login(SesionDTO dto) throws Exception;

}