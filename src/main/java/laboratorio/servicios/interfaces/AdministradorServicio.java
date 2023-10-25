package laboratorio.servicios.interfaces;

import laboratorio.dto.*;

import java.util.List;

public interface AdministradorServicio {

    int crearDigitador (DigitadorDTO digitadorDTO) throws Exception;

    int crearIngeniero(DigitadorDTO digitadorDTO) throws Exception;

    int crearEmpresa(EmpresaDTO empresaDTO) throws Exception;

    int crearObra(ObraDTO obraDTO) throws Exception;

    int asignarObra(PersonaDTO personaDTO) throws Exception;

    int eliminarMuestra (int codigoMuestra) throws Exception;

    List<IngenieroGetDTO> listarIngenieros ();

    List<DetallePersonaDTO> listarDigitadores();

    int buscarObra(int codigoObra) throws Exception;

    List<ObraDTO> listarObras ();

    DetallePersonaDTO detalleIngeniero(int codigoIngeniero) throws Exception;

    DetallePersonaDTO detalleDigitador (int codigoDigitador) throws Exception;
}