package laboratorio.servicios.interfaces;
import laboratorio.dto.*;
import laboratorio.dto.suelos.RegistroSuelosDto;
import laboratorio.modelo.*;

import java.util.List;
import java.util.Optional;

public interface AdministradorServicio {

    int crearAdministrador(AdministradorDTO administradorDTO) throws Exception;

    int crearDigitador (UsuarioDTO usuarioDTO) throws Exception;

    int crearIngeniero(UsuarioDTO usuarioDTO) throws Exception;

    int crearCliente(ClienteDTO clienteDTO) throws Exception;


    int crearEmpresa(EmpresaDTO empresaDTO) throws Exception;

    String crearSede(SedeDTO sedeDTO) throws Exception;


    int crearObra(ObraDTO obraDTO) throws Exception;

    String asignarObra(AsignarObrasRequestDTO asignarObrasRequestDTO) throws Exception;

    int eliminarMuestra (int codigoMuestra) throws Exception;

    List<IngenieroGetDTO> listarIngenieros ();

    List<EmpresaDTO>listarEmpresas();

    List<SedeDTO>listarSedes();

    List<UsuarioDTO> listarDigitadores();

    List<ClienteGetDTO> listarClientes();

    boolean buscarObra(String cr) throws Exception;
    List<ObraDTO> listarObras ();
    DetallePersonaDTO detalleIngeniero(int codigoIngeniero) throws Exception;
    DetallePersonaDTO detalleDigitador (int codigoDigitador) throws Exception;
    void crearCiudad(String ciudad) throws Exception;
    void eliminarCiudad(String ciudad) throws Exception;
    void eliminarEmpresa(String nombre) throws Exception;
    void eliminarSede(String nombre) throws Exception;
    Empresa buscarEmpresa(String nit) throws Exception;
    IngenieroGetDTO buscarIngeniero(int id) throws Exception;
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
    List<TipoMuestraCilindro> listarSeccion() throws Exception;
    List<EdadesDto> listarEdades(int id);
    void eliminarCompresionCilindro(int codigo) throws Exception;
    String guardarEdades(List<EdadesDto> edadesDto) throws Exception;
    String subirResultados (List<CilindroDTO> cilindroDTOList) throws Exception;
    List<ReporteDTO> listarReportes(FechasReporteDTO fechasReporteDTO) throws Exception;
    public  String registrarSuelo(RegistroSuelosDto registroSuelosDto) throws Exception;

}