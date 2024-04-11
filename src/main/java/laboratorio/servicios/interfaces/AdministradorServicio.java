package laboratorio.servicios.interfaces;
import laboratorio.dto.*;
import laboratorio.modelo.*;

import java.util.List;
import java.util.Optional;

public interface AdministradorServicio {

    int crearAdministrador(AdministradorDTO administradorDTO) throws Exception;

    int crearDigitador (DigitadorDTO digitadorDTO) throws Exception;

    int crearIngeniero(DigitadorDTO digitadorDTO) throws Exception;

    int crearCliente(ClienteDTO clienteDTO) throws Exception;


    int crearEmpresa(EmpresaDTO empresaDTO) throws Exception;

    String crearSede(SedeDTO sedeDTO) throws Exception;


    int crearObra(ObraDTO obraDTO) throws Exception;

    int asignarObra(PersonaDTO personaDTO) throws Exception;

    int eliminarMuestra (int codigoMuestra) throws Exception;

    List<IngenieroGetDTO> listarIngenieros ();

    List<EmpresaDTO>listarEmpresas();

    List<SedeDTO>listarSedes();

    List<DigitadorDTO> listarDigitadores();

    List<ClienteDTO> listarClientes();

    int buscarObra(int codigoObra) throws Exception;

    List<ObraDTO> listarObras ();

    DetallePersonaDTO detalleIngeniero(int codigoIngeniero) throws Exception;

    DetallePersonaDTO detalleDigitador (int codigoDigitador) throws Exception;

    void crearCiudad(String ciudad) throws Exception;

    void eliminarCiudad(String ciudad) throws Exception;

    void eliminarEmpresa(String nombre) throws Exception;

    void eliminarSede(String nombre) throws Exception;

    Empresa buscarEmpresa(String nit) throws Exception;

    Ingeniero buscarIngenieroPorCedula(String cedula) throws Exception;

    Digitador buscarDigitadorPorCedula(String cedula) throws Exception;

    Empresa editarEmpresa(Empresa empresa) throws Exception;

    Ingeniero editarIngeniero(Ingeniero ingeniero) throws Exception;

    Sede editarSede(Sede sede) throws Exception;

    void editarAdministrador(String correo) throws Exception;
    Sede buscarSede(String ciudad) throws Exception;

    List<AdministradorGetDTO> listaradministradores();

    void eliminarAdministrador(String correo) throws Exception;

    void eliminarDigitador(String correo) throws Exception;

    void eliminarIngeniero(String correo) throws Exception;

    Optional<Cuenta> buscarAdministrador(String correo) throws Exception;
}