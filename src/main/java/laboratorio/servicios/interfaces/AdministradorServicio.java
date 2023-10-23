package laboratorio.servicios.interfaces;

import laboratorio.dto.DigitadorDTO;
import laboratorio.dto.EmpresaDTO;
import laboratorio.dto.ObraDTO;
import laboratorio.dto.PersonaDTO;

public interface AdministradorServicio {

    int crearDigitador (DigitadorDTO digitadorDTO) throws Exception;

    int crearIngeniero(DigitadorDTO digitadorDTO) throws Exception;

    int crearEmpresa(EmpresaDTO empresaDTO) throws Exception;

    int crearObra(ObraDTO obraDTO) throws Exception;

    int asignarObra(PersonaDTO personaDTO) throws Exception;
}
