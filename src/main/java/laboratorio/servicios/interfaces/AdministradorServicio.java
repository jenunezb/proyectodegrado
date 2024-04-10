package laboratorio.servicios.interfaces;
import laboratorio.dto.*;
import laboratorio.modelo.Empresa;
import laboratorio.modelo.Sede;

import java.util.List;

public interface AdministradorServicio {

    int crearAdministrador(AdministradorDTO administradorDTO) throws Exception;

    int crearDigitador (DigitadorDTO digitadorDTO) throws Exception;

    int crearIngeniero(DigitadorDTO digitadorDTO) throws Exception;

    int crearEmpresa(EmpresaDTO empresaDTO) throws Exception;

    String crearSede(SedeDTO sedeDTO) throws Exception;


    int crearObra(ObraDTO obraDTO) throws Exception;

    int asignarObra(PersonaDTO personaDTO) throws Exception;

    int eliminarMuestra (int codigoMuestra) throws Exception;

    List<IngenieroGetDTO> listarIngenieros ();

    List<EmpresaDTO>listarEmpresas();

    List<SedeDTO>listarSedes();

    List<DetallePersonaDTO> listarDigitadores();

    int buscarObra(int codigoObra) throws Exception;

    List<ObraDTO> listarObras ();

    DetallePersonaDTO detalleIngeniero(int codigoIngeniero) throws Exception;

    DetallePersonaDTO detalleDigitador (int codigoDigitador) throws Exception;

    void crearCiudad(String ciudad) throws Exception;

    void eliminarCiudad(String ciudad) throws Exception;

    void eliminarEmpresa(String nombre) throws Exception;

    void eliminarSede(String nombre) throws Exception;

    Empresa buscarEmpresa(String nit) throws Exception;

    Empresa editarEmpresa(Empresa empresa) throws Exception;

    Sede editarSede(Sede sede) throws Exception;

    Sede buscarSede(String ciudad) throws Exception;

    List<AdministradorGetDTO> listaradministradores();

    void eliminarAdministrador(String correo) throws Exception;


}