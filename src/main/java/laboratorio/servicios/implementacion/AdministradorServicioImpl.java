package laboratorio.servicios.implementacion;
import laboratorio.dto.*;
import laboratorio.modelo.*;
import laboratorio.modelo.ensayo.Cilindro;
import laboratorio.modelo.ensayo.CompresionCilindros;
import laboratorio.repositorios.*;
import laboratorio.repositorios.ensayo.CilindroRepo;
import laboratorio.repositorios.ensayo.CompresionCilindrosRepo;
import laboratorio.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio {

    private final DigitadorRepo digitadorRepo;
    private final IngenieroRepo ingenieroRepo;
    private final EmpresaRepo empresaRepo;
    private final ObraRepo obraRepo;
    private final CiudadRepo ciudadRepo;
    private final ClienteRepo clienteRepo;
    private final AdministradorRepo administradorRepo;
    private final SedeRepo sedeRepo;
    private final CuentaRepo cuentaRepo;
    private final CilindroRepo cilindroRepo;
    private final CompresionCilindrosRepo compresionCilindrosRepo;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public int crearAdministrador(AdministradorDTO administradorDTO) throws Exception {

        if (administradorDTO == null) {
            throw new IllegalArgumentException("El objeto administradorDTO no puede ser nulo");
        }
        if (administradorDTO.correo() == null || administradorDTO.correo().isEmpty()) {
            throw new Exception("Por favor completa el campo de correo");
        }

        if (estaRepetidoCorreo(administradorDTO.correo())) {
            throw new Exception("El correo ya se encuentra registrado");
        }

        Administrador administrador = new Administrador();
        administrador.setCorreo(administradorDTO.correo());
        String passwordEncriptada = passwordEncoder.encode(administradorDTO.password());
        administrador.setPassword(passwordEncriptada);
        Administrador administradorNuevo = administradorRepo.save(administrador);

        return administradorNuevo.getCodigo();
    }

    @Override
    public int crearDigitador(UsuarioDTO usuarioDTO) throws Exception {
        if (estaRepetidaCedula(usuarioDTO.cedula())) {
            throw new Exception("La cédula ya se encuentra registrada");
        }

        if (estaRepetidoCorreo(usuarioDTO.correo())) {
            throw new Exception("El correo ya se encuentra registrado");
        }

        Digitador digitador = new Digitador();
        digitador.setCedula(usuarioDTO.cedula());
        digitador.setNombre(usuarioDTO.nombre());
        digitador.setTelefono(usuarioDTO.telefono());
        digitador.setCiudad(ciudadRepo.findByNombre(usuarioDTO.ciudad()));
        digitador.setCorreo(usuarioDTO.correo());
        digitador.setEstado(true);
        String passwordEncriptada = passwordEncoder.encode(usuarioDTO.password());
        digitador.setPassword(passwordEncriptada);

        Digitador digitadorNuevo = digitadorRepo.save(digitador);

        return digitadorNuevo.getCodigo();
    }

    @Override
    // Como el digitador y el ingeniero comparten los mismos atributos, ingreso DigitadorDTO y no afecta en nada el método
    public int crearIngeniero(UsuarioDTO usuarioDTO) throws Exception {
        if (estaRepetidaCedula(usuarioDTO.cedula())) {
            throw new Exception("La cédula ya se encuentra registrada");
        }

        if (estaRepetidoCorreo(usuarioDTO.correo())) {
            throw new Exception("El correo ya se encuentra registrado");
        }

        Ingeniero ingeniero = new Ingeniero();
        ingeniero.setCedula(usuarioDTO.cedula());
        ingeniero.setNombre(usuarioDTO.nombre());
        ingeniero.setTelefono(usuarioDTO.telefono());
        ingeniero.setCiudad(ciudadRepo.findByNombre(usuarioDTO.ciudad()));
        ingeniero.setCorreo(usuarioDTO.correo());
        ingeniero.setEstado(true);
        String passwordEncriptada = passwordEncoder.encode(usuarioDTO.password());
        ingeniero.setPassword(passwordEncriptada);

        Ingeniero ingenieroNuevo = ingenieroRepo.save(ingeniero);

        return ingenieroNuevo.getCodigo();
    }

    @Override
    public int crearCliente(ClienteDTO clienteDTO) throws Exception {
        if (estaRepetidaCedula(clienteDTO.cedula())) {
            throw new Exception("La cédula ya se encuentra registrada");
        }

        if (estaRepetidoCorreo(clienteDTO.correo())) {
            throw new Exception("El correo ya se encuentra registrado");
        }

        Cliente cliente = new Cliente();
        cliente.setCedula(clienteDTO.cedula());
        cliente.setNombre(clienteDTO.nombre());
        cliente.setCiudad(ciudadRepo.findByNombre(clienteDTO.ciudad()));
        cliente.setCorreo(clienteDTO.correo());
        cliente.setEstado(true);
        String passwordEncriptada = passwordEncoder.encode(clienteDTO.password());
        cliente.setPassword(passwordEncriptada);
        cliente.setCargo(clienteDTO.cargo());
        cliente.setTelefono(clienteDTO.telefono());


        Cliente cliente1 = clienteRepo.save(cliente);

        return cliente1.getCodigo();
    }

    @Override
    public int crearEmpresa(EmpresaDTO empresaDTO) throws Exception {
        if (empresaRepo.existsById(empresaDTO.nit())) {
            throw new Exception("La empresa ya se encuentra registrada");
        }

        Empresa empresa = new Empresa();
        empresa.setNit(empresaDTO.nit());
        empresa.setNombre(empresaDTO.nombre());
        empresa.setDireccion(empresaDTO.direccion());
        empresa.setTelefono(empresaDTO.telefono());

        Empresa empresaNueva = empresaRepo.save(empresa);

        return empresaNueva.getNit();
    }

    @Override
    public String crearSede(SedeDTO sedeDTO) throws Exception {
        // Verificar si ya existe una sede para la ciudad proporcionada
        Sede sedeExistente = sedeRepo.findByCiudad(sedeDTO.ciudad());
        if (sedeExistente != null) {
            throw new Exception("La sede ya se encuentra registrada para esta ciudad");
        }

        // Crear una nueva instancia de Sede utilizando los datos del DTO
        Sede nuevaSede = new Sede();
        nuevaSede.setCiudad(sedeDTO.ciudad());
        nuevaSede.setDireccion(sedeDTO.direccion());
        nuevaSede.setTelefono(sedeDTO.telefono());

        // Guardar la nueva sede en la base de datos utilizando el repositorio
        Sede sedeGuardada = sedeRepo.save(nuevaSede);

        // Devolver el nombre de la ciudad de la sede guardada
        return sedeGuardada.getCiudad();
    }


    @Override
    public int crearObra(ObraDTO obraDTO) throws Exception {

        if (obraRepo.existsByCR(obraDTO.cr())) {
            throw new Exception("Ya existe una obra con este CR: " + obraDTO.cr());
        }

        Obra obra = new Obra();

        Optional<Empresa> empresa = empresaRepo.findByNombre(obraDTO.empresa());
        obra.setEmpresa(empresa.get());
        obra.setDireccion(obraDTO.direccion());
        obra.setFecha_inicio(LocalDate.now());
        obra.setNombre(obraDTO.nombre());
        obra.setTelefono(obraDTO.telefono());
        obra.setCiudad(buscarCiudad(obraDTO.ciudad()));
        obra.setCR(obraDTO.cr());

        Obra obraNueva = obraRepo.save(obra);

        return obraNueva.getId();

    }

    @Override
    public int asignarObra(PersonaDTO personaDTO) throws Exception {
        int retorno = 0;
        Optional<Ingeniero> ingenieroBuscado = ingenieroRepo.findByCedula(personaDTO.cedula());
        Optional<Obra> obraBuscada = obraRepo.findById(personaDTO.codigoObra());
        List<Obra> obras = new ArrayList<>();
        if (ingenieroBuscado.isEmpty()) {
            Optional<Digitador> digitador = digitadorRepo.findByCedula(personaDTO.cedula());
            if (digitador.isEmpty()) {
                throw new Exception("La persona no fue encontrada");
            } else {
                if (obraBuscada.isEmpty()) {
                    throw new Exception("La obra no fue encontrada");
                } else {
                    obras.add(obraBuscada.get());
                    digitador.get().setObras(obras);
                    Digitador digitador1 = digitadorRepo.save(digitador.get());
                    retorno = digitador1.getCodigo();
                }
            }
        } else {
            if (obraBuscada.isEmpty()) {
                throw new Exception("La obra no fue encontrada");
            } else {
                obras.add(obraBuscada.get());
                ingenieroBuscado.get().setObras(obras);
                Ingeniero ingeniero = ingenieroRepo.save(ingenieroBuscado.get());
                retorno = ingeniero.getCodigo();
            }
        }

        return retorno;
    }

    @Override
    public int eliminarMuestra(int codigoMuestra) throws Exception {
        return 0;
    }

    @Override
    public List<IngenieroGetDTO> listarIngenieros() {
        List<Ingeniero> ingenieroList = ingenieroRepo.findAll();
        List<IngenieroGetDTO> ingenieroGetDTOS = new ArrayList<>();

        for (int i = 0; i < ingenieroList.size(); i++) {


            ingenieroGetDTOS.add(new IngenieroGetDTO(
                    ingenieroList.get(i).getCedula(),
                    ingenieroList.get(i).getNombre(),
                    ingenieroList.get(i).getCiudad().getNombre(),
                    ingenieroList.get(i).getTelefono(),
                    ingenieroList.get(i).getCorreo()
            ));
        }
        return ingenieroGetDTOS;
    }

    @Override
    public List<AdministradorGetDTO> listaradministradores() {
        List<Administrador> administradorList = administradorRepo.findAll();
        List<AdministradorGetDTO> administradorGetDTOS = new ArrayList<>();

        for (int i = 0; i < administradorList.size(); i++) {


            administradorGetDTOS.add(new AdministradorGetDTO(
                    administradorList.get(i).getCorreo()
            ));
        }
        return administradorGetDTOS;
    }
    @Override
    public List<EmpresaDTO> listarEmpresas() {
        List<Empresa> empresaList = empresaRepo.findAll();
        List<EmpresaDTO> empresaGetDTOS = new ArrayList<>();

        for (int i = 0; i < empresaList.size(); i++) {


            empresaGetDTOS.add(new EmpresaDTO(
                    empresaList.get(i).getNit(),
                    empresaList.get(i).getNombre(),
                    empresaList.get(i).getDireccion(),
                    empresaList.get(i).getTelefono()
            ));
        }
        return empresaGetDTOS;
    }


    @Override
    public List<SedeDTO> listarSedes() {
        List<Sede> sedeList = sedeRepo.findAll();
        List<SedeDTO> sedeDTOS = new ArrayList<>();

        for (int i = 0; i < sedeList.size(); i++) {


            sedeDTOS.add(new SedeDTO(
                    sedeList.get(i).getCiudad(),
                    sedeList.get(i).getDireccion(),
                    sedeList.get(i).getTelefono()
            ));
        }
        return sedeDTOS;
    }

    @Override
    public List<ClienteGetDTO> listarClientes() {
        List<Cliente> clienteList = clienteRepo.findAll();
        List<ClienteGetDTO> clienteDTOS = new ArrayList<>();

        for (int i = 0; i < clienteList.size(); i++) {
            clienteDTOS.add(new ClienteGetDTO(
                    clienteList.get(i).getCedula(),
                    clienteList.get(i).getNombre(),
                    clienteList.get(i).getCiudad().getNombre(),
                    clienteList.get(i).getTelefono(),
                    clienteList.get(i).getCorreo(),
                    clienteList.get(i).getCargo()
            ));
        }
        return clienteDTOS;
    }

    @Override
    public List<UsuarioDTO> listarDigitadores() {
        List<Digitador> digitador = digitadorRepo.findAll();
        List<UsuarioDTO> usuarioDTOS = new ArrayList<>();

        for (int i = 0; i < digitador.size(); i++) {
            usuarioDTOS.add(new UsuarioDTO(
                    digitador.get(i).getCedula(),
                    digitador.get(i).getNombre(),
                    digitador.get(i).getCiudad().getNombre(),
                    digitador.get(i).getTelefono(),
                    digitador.get(i).getPassword(),
                    digitador.get(i).getCorreo()
            ));
        }
        return usuarioDTOS;
    }

    @Override
    public int buscarObra(int codigoObra) throws Exception {
        return 0;
    }

    @Override
    public List<ObraDTO> listarObras() {
        List<Obra> obraList = obraRepo.findAll();
        List<ObraDTO> obraGetDTOS = new ArrayList<>();

        for (int i = 0; i < obraList.size(); i++) {

            obraGetDTOS.add(new ObraDTO(
                    obraList.get(i).getDireccion(),
                    obraList.get(i).getNombre(),
                    obraList.get(i).getTelefono(),
                    obraList.get(i).getCiudad().getNombre(),
                    obraList.get(i).getEmpresa().getNombre(),
                    obraList.get(i).getCR()
            ));
        }
        return obraGetDTOS;
    }

    @Override
    public DetallePersonaDTO detalleIngeniero(int codigoIngeniero) throws Exception {
        Optional<Ingeniero> ingenieroOptional = ingenieroRepo.findById(codigoIngeniero);

        Ingeniero ingeniero = ingenieroOptional.orElseThrow(() ->
                new ExpressionException("El ingeniero de código " + codigoIngeniero + " no existe")
        );

        List<String> obras = ingeniero.getObras().stream()
                .map(Obra::getCR)
                .collect(Collectors.toList());

        return new DetallePersonaDTO(
                ingeniero.getCedula(),
                ingeniero.getNombre(),
                ingeniero.getTelefono(),
                ingeniero.getCiudad().getNombre(),
                obras
        );
    }

    @Override
    public DetallePersonaDTO detalleDigitador(int codigoDigitador) throws Exception {
        Optional<Digitador> digitadorOptional = digitadorRepo.findById(codigoDigitador);

        Digitador digitador = digitadorOptional.orElseThrow(() ->
                new ExpressionException("El digitador de código " + codigoDigitador + " no existe")
        );

        List<String> obras = digitador.getObras().stream()
                .map(Obra::getCR)
                .collect(Collectors.toList());

        return new DetallePersonaDTO(
                digitador.getCedula(),
                digitador.getNombre(),
                digitador.getTelefono(),
                digitador.getCiudad().getNombre(),
                obras
        );
    }

    public boolean estaRepetidaCedula(String cedula) {
        Optional<Digitador> digitadorBuscado = digitadorRepo.findByCedula(cedula);
        if (!digitadorBuscado.isEmpty()) {
            if (!digitadorBuscado.get().isEstado()) {
                return false;
            }
            return true;
        }
        return digitadorRepo.existsByCedula(cedula);
    }

    public boolean estaRepetidoCorreo(String correo) {
        Optional<Cuenta> cuenta = cuentaRepo.findByCorreo(correo);
        return cuenta.isPresent(); // Devuelve true si la cuenta está presente (correo repetido), false si no está presente
    }
    public boolean estaRepetidaCiudad(String ciudad) {
        Ciudad ciudad1 = ciudadRepo.findByNombre(ciudad);

        return ciudad1 != null;
    }

    public void crearCiudad(String ciudad) throws Exception {

        if (estaRepetidaCiudad(ciudad)) {
            throw new Exception("La ciudad ya se encuentra registrada");
        }
        if (ciudad.isEmpty()) {
            throw new Exception("El campo se encuentra vacío");
        }
        Ciudad ciudad1 = new Ciudad();
        ciudad1.setNombre(ciudad);
        ciudadRepo.save(ciudad1);
    }

    public void eliminarCiudad(String ciudad) throws Exception {
        Ciudad ciudadEliminar = buscarCiudad(ciudad);

        try {
            ciudadRepo.deleteByNombre(ciudadEliminar.getNombre());
        } catch (DataIntegrityViolationException e) {
            throw new Exception("No se puede eliminar la ciudad '" + ciudad + "' porque está en uso.");
        }
    }

    public void eliminarAdministrador(String correo) throws Exception {
        Optional<Cuenta> administradorEliminar = buscarAdministrador(correo);
        try {
            cuentaRepo.deleteById(administradorEliminar.get().getCodigo());
        } catch (DataIntegrityViolationException e) {
            throw new Exception("No se puede eliminar el administrador '" + correo + "' porque está en uso.");
        }
    }

    public void eliminarDigitador(String correo) throws Exception {
        Optional<Cuenta> administradorEliminar = buscarAdministrador(correo);
        try {
            cuentaRepo.deleteById(administradorEliminar.get().getCodigo());
        } catch (DataIntegrityViolationException e) {
            throw new Exception("No se puede eliminar el digitador '" + correo + "' porque está en uso.");
        }
    }

    public void eliminarIngeniero(String correo) throws Exception {
        Optional<Cuenta> administradorEliminar = buscarAdministrador(correo);
        try {
            cuentaRepo.deleteById(administradorEliminar.get().getCodigo());
        } catch (DataIntegrityViolationException e) {
            throw new Exception("No se puede eliminar el ingeniero '" + correo + "' porque está en uso.");
        }
    }
    public void eliminarCompresionCilindro(int codigo) throws Exception {
        Optional<CompresionCilindros> buscarCilindro = buscarCilindro(codigo);
        try {
           compresionCilindrosRepo.deleteById(buscarCilindro.get().getCodigo());
        } catch (DataIntegrityViolationException e) {
            throw new Exception("No se puede eliminar la muestra porque está en uso.");
        }
    }

    @Override
    public void editarAdministrador(String correo) throws Exception {

    }

    public Ciudad buscarCiudad(String ciudad) throws Exception {
        Ciudad ciudadBuscada = ciudadRepo.findByNombre(ciudad);

        if (ciudadBuscada == null) {
            throw new Exception("No se ha encontrado la ciudad buscada");
        }
        return ciudadBuscada;
    }

    public Optional<Cuenta> buscarAdministrador(String correo) throws Exception {
        Optional<Cuenta> adminBuscado = cuentaRepo.findByCorreo(correo);
        if (adminBuscado.isEmpty()) {
            throw new Exception("No se ha encontrado el administrador buscado");
        }
        return adminBuscado;
    }

    public Optional<CompresionCilindros> buscarCilindro(int codigo) throws Exception{

        compresionCilindrosRepo.DeleteByCompresionCilindrosCodigo(codigo);

        Optional<CompresionCilindros> compresionCilindrosBuscado = compresionCilindrosRepo.findById(codigo);
        if(compresionCilindrosBuscado.isEmpty()){
throw new Exception("No se ha encontrado el cilindro buscado");
        }
        return compresionCilindrosBuscado;
    }
    @Override
    public List<TipoMuestraCilindro> listarSeccion() {
        List<TipoMuestraCilindro> secciones = Arrays.asList(TipoMuestraCilindro.values());
        return secciones;
    }

    @Override
    public List<EdadesDto> listarEdades(int id) {
        List<Cilindro> cilindro = cilindroRepo.buscarPorIdCompresion(id);
        List<EdadesDto> edades = new ArrayList<>();

        for (int i = 0; i<cilindro.size();i++){
            edades.add(new EdadesDto(
               cilindro.get(i).getEdad(),
               cilindro.get(i).getCompresionCilindros().getNumeroMuestra(),
               cilindro.get(i).getCompresionCilindros().getCodigo()
            ));
        }
        return edades;
    }

    public void eliminarEmpresa(String nombre) throws Exception {
        Optional<Empresa> empresa = empresaRepo.findByNombre(nombre);

        if (empresa != null) {
            empresaRepo.deleteByNombre(nombre);
        } else {
            throw new Exception("No se ha encontrado la empresa buscada");
        }
    }

    @Override
    public void eliminarSede(String ciudad) throws Exception {
        Sede sede = sedeRepo.findByCiudad(ciudad);

        if (sede != null) {
            sedeRepo.deleteByCiudad(ciudad);
        } else {
            throw new Exception("No se ha encontrado la sede buscada");
        }
    }


    @Override
    public Empresa buscarEmpresa(String nombre) throws Exception {
        Empresa empresa = empresaRepo.findByNit(nombre);
        if (empresa == null) {
            throw new Exception("No se ha encontrado la empresa con el nombre: " + nombre);
        }
        return empresa;
    }
    @Override
    public Ingeniero buscarIngenieroPorCedula(String cedula) throws Exception {
        Ingeniero ingeniero = ingenieroRepo.findBycedula(cedula);
        if (ingeniero == null) {
            throw new Exception("No se encontró ningún ingeniero con la cédula: " + cedula);
        }
        return ingeniero;
    }
    @Override
    public Digitador buscarDigitadorPorCedula(String cedula) throws Exception {
        Digitador digitador = digitadorRepo.findBycedula(cedula);
        if (digitador == null) {
            throw new Exception("No se encontró ningún ingeniero con la cédula: " + cedula);
        }
        return digitador;
    }

    @Override
    public Empresa editarEmpresa(Empresa empresa) throws Exception {
        int empresaId = empresa.getNit();
        Empresa empresaExistente = empresaRepo.findById(empresaId)
                .orElseThrow(() -> new Exception("Empresa no encontrada con ID: " + empresaId));

        empresaExistente.setNombre(empresa.getNombre());
        empresaExistente.setDireccion(empresa.getDireccion());
        empresaExistente.setTelefono(empresa.getTelefono());

        return empresaRepo.save(empresaExistente);
    }
    @Override
    public Ingeniero editarIngeniero(Ingeniero ingeniero) throws Exception {
        String ingenieroCedulaId = ingeniero.getCedula();

        // Busca el ingeniero por su cédula en la base de datos
        Ingeniero ingenieroExistente = ingenieroRepo.findBycedula(ingenieroCedulaId);

        if (ingenieroExistente == null) {
            throw new Exception("Ingeniero no encontrado con cédula: " + ingenieroCedulaId);
        }

        // Actualiza los campos del usuario existente (que es un Ingeniero) con los nuevos valores
        ingenieroExistente.setNombre(ingeniero.getNombre());
        ingenieroExistente.setTelefono(ingeniero.getTelefono());
        // No puedes setear 'correo' porque no está definido en Ingeniero
        // ingenieroExistente.setCorreo(ingeniero.getCorreo());

        // Guarda y devuelve el ingeniero actualizado
        return ingenieroRepo.save(ingenieroExistente);
    }

    @Override
    public Sede editarSede(Sede sede) {
        String sedeCiudad = sede.getCiudad();
        Sede sedeExistente = sedeRepo.findByCiudad(sedeCiudad);

        sedeExistente.setCiudad(sede.getCiudad());
        sedeExistente.setDireccion(sede.getDireccion());
        sedeExistente.setTelefono(sede.getTelefono());

        return sedeRepo.save(sedeExistente);
    }
    @Override
    public Sede buscarSede(String ciudad) throws Exception {
        Sede sede = sedeRepo.findByCiudad(ciudad);
        if (sede == null) {
            throw new Exception("No se ha encontrado la empresa con el nombre: " + ciudad);
        }
        return sede;
    }

    public String guardarEdades(List<EdadesDto> edadesDto) throws Exception{
        List<Cilindro> cilindrosBuscados = cilindroRepo.buscarPorIdCompresion(edadesDto.get(0).codigo());

        if(cilindrosBuscados.isEmpty()){
            new Exception("No se ha podido guardar la edad correctamente");
        }
    for (int i=0; i<edadesDto.size(); i++){
        cilindrosBuscados.get(i).setEdad(edadesDto.get(i).edad());
        LocalDate fechaToma = cilindrosBuscados.get(i).getCompresionCilindros().getFechaToma();
        int edad = edadesDto.get(i).edad();
        LocalDate fechaFalla = fechaToma.plusDays(edad);
        cilindrosBuscados.get(i).setFechaFalla(fechaFalla);
        cilindroRepo.save(cilindrosBuscados.get(i));
    }
    return "Se han cargado las edades correctamente";
    }
}