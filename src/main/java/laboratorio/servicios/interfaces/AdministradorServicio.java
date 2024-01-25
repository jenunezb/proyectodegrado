package laboratorio.servicios.interfaces;

import laboratorio.Excepciones.Excepciones;
import laboratorio.dto.*;
import laboratorio.modelo.Administrador;

import java.util.List;

public interface AdministradorServicio {

    int crearAdministrador(AdministradorDTO administradorDTO) throws Exception;

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

    void crearCiudad(String ciudad) throws Excepciones;
}