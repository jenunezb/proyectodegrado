package laboratorio.servicios.interfaces;

import laboratorio.dto.DigitadorDTO;
import laboratorio.dto.EmpresaDTO;
import laboratorio.dto.ObraDTO;

public interface AdministradorServicio {

    int crearDigitador (DigitadorDTO digitadorDTO) throws Exception;

    int crearIngeniero(DigitadorDTO digitadorDTO) throws Exception;

    int crearEmpresa(EmpresaDTO empresaDTO) throws Exception;

    int crearObra(ObraDTO obraDTO) throws Exception;
}
