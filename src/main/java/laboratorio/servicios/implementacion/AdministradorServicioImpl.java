package laboratorio.servicios.implementacion;
import laboratorio.dto.*;
import laboratorio.dto.suelos.GradacionDTO;
import laboratorio.dto.suelos.RegistroSuelosDto;
import laboratorio.dto.suelos.SuelosDTO;
import laboratorio.dto.vigas.VigasGetDTO;
import laboratorio.modelo.*;
import laboratorio.modelo.ensayo.*;
import laboratorio.repositorios.*;
import laboratorio.repositorios.ensayo.*;
import laboratorio.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private final SueloRepo sueloRepo;
    private final GradacionRepo gradacionRepo;
    private final MuestraRepo muestraRepo;
    private final TamicesMasasRepo tamicesMasasRepo;
    private final VigaRepo vigaRepo;
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

        Empresa empresa = empresaRepo.findByNombre(obraDTO.empresa())
                .orElseThrow(() -> new RuntimeException("La empresa '" + obraDTO.empresa() + "' no fue encontrada"));

        obra.setEmpresa(empresa);
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
    public String asignarObra(AsignarObrasRequestDTO asignarObrasRequestDTO) throws Exception {
        System.out.println(asignarObrasRequestDTO);
        Optional<Ingeniero> ingenieroBuscado = ingenieroRepo.findById(asignarObrasRequestDTO.codigoUsuario());
        List<Ingeniero> ingenieroList = new ArrayList<>();

        if (ingenieroBuscado.isEmpty()) {
            throw new Exception("El usuario no fue encontrado");
        } else {
            ingenieroList.add(ingenieroBuscado.get());
        }

        List<Obra> obras = new ArrayList<>();

        for (ObraDTO obraDTO : asignarObrasRequestDTO.listaObras()) {
            Obra obra = obraRepo.findByCR(obraDTO.cr());

            if (obra == null) {
                throw new Exception("La obra con CR " + obraDTO.cr() + " no fue encontrada");
            }

            System.out.println("pasa asignando la obra");
            obra.setIngenieros(ingenieroList);
            obraRepo.save(obra);
            obras.add(obra);
        }

        ingenieroBuscado.get().setObras(obras);
        ingenieroRepo.save(ingenieroBuscado.get());

        return "Se han asignado las obras correctamente";
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
                    ingenieroList.get(i).getCorreo(),
                    ingenieroList.get(i).getCodigo()
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
    public boolean buscarObra(String cr) throws Exception{
        Obra obraBuscada = obraRepo.findByCR(cr);
        if (obraBuscada == null || obraBuscada.getNombre() == null) {
            throw new Exception("No se ha encontrado la obra con el CR: " + cr);
        }
        return true;
    }

    @Override
    public ObraDTO buscarObraa(String cr) throws Exception{
        Obra obraBuscada = obraRepo.findByCR(cr);
        if (obraBuscada == null) {
            throw new Exception("No se ha encontrado la obra con el CR: " + cr);
        }
        ObraDTO obraDTO = new ObraDTO(obraBuscada.getDireccion(), obraBuscada.getNombre(), obraBuscada.getTelefono(),
                obraBuscada.getCiudad().getNombre(),obraBuscada.getEmpresa().getNombre(),obraBuscada.getCR());

        return obraDTO;
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
throw new Exception("No se ha encontrado la muestra buscada");
        }
        return compresionCilindrosBuscado;
    }

    @Override
    public List<TipoMuestraCilindro> listarSeccion() {
        List<TipoMuestraCilindro> secciones = Arrays.asList(TipoMuestraCilindro.values());
        return secciones;
    }

    public List<SuelosDTO> listarSuelos(){
        List<MuestraSuelos> muestraSuelos = sueloRepo.findAll();
        List<SuelosDTO> listaregistroSuelosDto= new ArrayList<>();
        for (int i=0;i<muestraSuelos.size();i++){
            SuelosDTO registroSuelosDto = new SuelosDTO(
                    muestraSuelos.get(i).getObra().getCR(),
                    muestraSuelos.get(i).getCodigo(),
                    muestraSuelos.get(i).getObra().getNombre(),
                    muestraSuelos.get(i).getFechaRecibido());
            listaregistroSuelosDto.add(registroSuelosDto);
        }
        return listaregistroSuelosDto;
    }

    @Override
    public List<EdadesDto> listarEdades(int id) {
        Optional<CompresionCilindros> compresionCilindros = compresionCilindrosRepo.findById(id);
        List<EdadesDto> edades = new ArrayList<>();

        if(compresionCilindros.get().getEnsayo().getNombreLegible().equals("Compresión de 6")){
            List<Cilindro> cilindro = cilindroRepo.buscarPorIdCompresion(id);
            for (int i = 0; i<cilindro.size();i++){
                edades.add(new EdadesDto(
                        cilindro.get(i).getEdad(),
                        cilindro.get(i).getCompresionCilindros().getNumeroMuestra(),
                        cilindro.get(i).getCompresionCilindros().getCodigo()
                ));
            }
        }
        else{
            List<Viga> vigas = vigaRepo.buscarPorIdCompresion(id);
            for (int i = 0; i<vigas.size();i++){
                edades.add(new EdadesDto(
                        vigas.get(i).getEdad(),
                        vigas.get(i).getCompresionCilindros().getNumeroMuestra(),
                        vigas.get(i).getCompresionCilindros().getCodigo()
                ));
            }
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
    public IngenieroGetDTO buscarIngeniero(int id) throws Exception {
        Optional<Ingeniero> ingeniero = ingenieroRepo.findById(id);
        if (ingeniero.isEmpty()) {
            throw new Exception("No se encontró ningún ingeniero con la cédula: " + id);
        }
        IngenieroGetDTO ingenieroGetDTO = new IngenieroGetDTO(ingeniero.get().getCedula(),
                ingeniero.get().getNombre(), ingeniero.get().getCiudad().getNombre(), ingeniero.get().getTelefono(), ingeniero.get().getTelefono(), ingeniero.get().getCodigo());
        return ingenieroGetDTO;
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

    @Override
    public String guardarEdades(List<EdadesDto> edadesDto) throws Exception{
        List<Cilindro> cilindrosBuscados = cilindroRepo.buscarPorIdCompresion(edadesDto.get(0).codigo());
        List<Viga> vigasBuscadas = vigaRepo.buscarPorIdCompresion(edadesDto.get(0).codigo());
        if(cilindrosBuscados.isEmpty()){
            if(vigasBuscadas.isEmpty()){
                throw new Exception("No se ha podido guardar la edad correctamente");
            }
            for (int i=0; i<edadesDto.size(); i++){
                vigasBuscadas.get(i).setEdad(edadesDto.get(i).edad());
                LocalDate fechaToma = vigasBuscadas.get(i).getCompresionCilindros().getFechaToma();
                int edad = edadesDto.get(i).edad();
                LocalDate fechaFalla = fechaToma.plusDays(edad);
                vigasBuscadas.get(i).setFechaFalla(fechaFalla);
                vigaRepo.save(vigasBuscadas.get(i));
            }
        }else{
            for (int i=0; i<edadesDto.size(); i++){
                cilindrosBuscados.get(i).setEdad(edadesDto.get(i).edad());
                LocalDate fechaToma = cilindrosBuscados.get(i).getCompresionCilindros().getFechaToma();
                int edad = edadesDto.get(i).edad();
                LocalDate fechaFalla = fechaToma.plusDays(edad);
                cilindrosBuscados.get(i).setFechaFalla(fechaFalla);
                cilindroRepo.save(cilindrosBuscados.get(i));
            }
        }

    return "Se han cargado las edades correctamente";
    }

    @Override
    public String subirResultados(List<CilindroDTO> cilindroDTOList) throws Exception {
        for (CilindroDTO cilindroDTO : cilindroDTOList) {
            Optional<Cilindro> cilindroOptional = cilindroRepo.findById(cilindroDTO.id());
            if (cilindroOptional.isPresent()) {
                Cilindro cilindro = cilindroOptional.get();
                cilindro.setCarga(cilindroDTO.carga());
                cilindro.setPeso(cilindroDTO.peso());
                cilindro.setFormaFalla(FormaFalla.desdeValor(cilindroDTO.formaFalla()));
                // Guardar la obra asociada al cilindro
                Obra obra = cilindro.getCompresionCilindros().getObra();
                obraRepo.save(obra);
            } else {
                throw new Exception("No se encontró el cilindro con ID: " + cilindroDTO.id());
            }
        }
        return "Se han cargado los resultados exitosamente";
    }

    @Override
    public List<ReporteDTO> listarReportes(FechasReporteDTO fechasReporteDTO) throws Exception {

        if(buscarObra(fechasReporteDTO.cr())){
            List<Cilindro> cilindrosBuscados = cilindroRepo.buscarPorIntervalo(fechasReporteDTO.fechaInicio(), fechasReporteDTO.fechaFin(), fechasReporteDTO.cr());
            List<ReporteDTO> reporteDTOS = new ArrayList<>();

            double volumen = 1645;

            for(int i=0; i< cilindrosBuscados.size();i++){

                double h=0;
                double h1=0;
                double d=0;

                if ("Nucleo de 4".equals(cilindrosBuscados.get(i).getCompresionCilindros().getEnsayo().getNombreLegible())) {
                    System.out.println("pasa por compresion de 4");
                    h=2;
                    h1=2;
                    d=2;
                }

                double densidad= cilindrosBuscados.get(i).getPeso() / volumen;
                double esfuerzo = (cilindrosBuscados.get(i).getCarga() * 1000) / 79/10-2; // Convertir kN a kg y calcular el esfuerzo en kg/cm²
                double psi = esfuerzo * 14.272;
                double mpa = psi / 145;
                double desarrollo = mpa/cilindrosBuscados.get(i).getCompresionCilindros().getResistencia()*100;
                String obs = "B";
                if(desarrollo<70){
                    obs = "N";
                }
                reporteDTOS.add(new ReporteDTO(
                        cilindrosBuscados.get(i).getCompresionCilindros().getObra().getCR(),
                        cilindrosBuscados.get(i).getCompresionCilindros().getNumeroMuestra(),
                        cilindrosBuscados.get(i).getCompresionCilindros().getEnsayo().getNombreLegible(),
                        cilindrosBuscados.get(i).getCompresionCilindros().getFechaToma(),
                        cilindrosBuscados.get(i).getFechaFalla(),
                        cilindrosBuscados.get(i).getEdad(),
                        cilindrosBuscados.get(i).getPeso(),
                        cilindrosBuscados.get(i).getCarga(),
                        cilindrosBuscados.get(i).getCompresionCilindros().getObra().getNombre(),
                        cilindrosBuscados.get(i).getCodigo(),
                        densidad,
                        esfuerzo,
                        psi,
                        mpa,
                        desarrollo,
                        obs,
                        cilindrosBuscados.get(i).getCompresionCilindros().getDescripcion(),
                        cilindrosBuscados.get(i).getCompresionCilindros().getResistencia(),
                        cilindrosBuscados.get(i).getH(),
                        cilindrosBuscados.get(i).getH1(),
                        cilindrosBuscados.get(i).getD()));
            }
            return reporteDTOS;
        }
        return null;
    }

    private FormaFalla obtenerFormaFalla(int valor) throws Exception {
        for (FormaFalla formaFalla : FormaFalla.values()) {
            if (formaFalla.getValor() == valor) {
                return formaFalla;
            }
        }
        throw new Exception("Valor de FormaFalla no válido: " + valor);
    }

    @Override
    public  String registrarSuelo(RegistroSuelosDto registroSuelosDto) throws Exception{

        if(buscarObra(registroSuelosDto.cr())){
            MuestraSuelos muestraSuelos = new MuestraSuelos();
            muestraSuelos.setObra(obraRepo.findByCR(registroSuelosDto.cr()));
            muestraSuelos.setMaterial(registroSuelosDto.material());
            muestraSuelos.setLocalizacion(registroSuelosDto.localizacion());
            muestraSuelos.setCantera(registroSuelosDto.cantera());
            muestraSuelos.setFechaToma(registroSuelosDto.fechaToma());
            muestraSuelos.setFechaRecibido(registroSuelosDto.fechaRecibido());
            muestraSuelos.setDescripcion(registroSuelosDto.descripcion());
            sueloRepo.save(muestraSuelos);

            Gradacion gradacion = new Gradacion();
            gradacion.setMuestraSuelos(muestraSuelos);
            gradacionRepo.save(gradacion);

            return "La muestra se ha agregado correctamente";
        }else{
            throw new Exception ("El CR ingresado no se encuentra registrado o pertenece a otra sede");
        }
    }

    @Override
    public String subirGranulometria(GradacionDTO granulometriaDTO) throws Exception {
        // Verificar si la obra existe
        Obra obra = obraRepo.findByCR(granulometriaDTO.cr());
        if (obra == null) {
            throw new Exception("No se encontró la obra con el CR especificado");
        }

        MuestraSuelos muestraSuelos = muestraRepo.getById(granulometriaDTO.codigoMuestra());

        if (muestraSuelos == null) {
            throw new Exception("No se encontró la obra con el la muestra especificada");
        }

        Gradacion gradacion = new Gradacion();
        gradacion.setFechaFalla(granulometriaDTO.fechaEnsayo());
        gradacion.setMuestraSuelos(muestraSuelos);

        gradacionRepo.save(gradacion);

        List<TamicesMasas> tamicesMasasList = new ArrayList<>();

        // Iterar sobre los resultados y los tamices del DTO
        List<Double> resultados = granulometriaDTO.resultados();
        List<String> tamices = granulometriaDTO.tamices();
        for (int i = 0; i < resultados.size() && i < tamices.size(); i++) {
            TamicesMasas tamicesMasas = new TamicesMasas();
            // Redondear a 4 decimales
            float masaRedondeada = Math.round(resultados.get(i) * 10000.0f) / 10000.0f;
            tamicesMasas.setMasaRetenida(masaRedondeada);
            tamicesMasas.setTamiz(tamices.get(i));
            tamicesMasas.setGradacion(gradacion);

            tamicesMasasList.add(tamicesMasas);
            tamicesMasasRepo.save(tamicesMasas);
        }


        gradacion.setTamicesMasasList(tamicesMasasList);

        gradacionRepo.save(gradacion);

        return "La granulometría ha sido subida exitosamente";
    }

    @Override
    public GradacionDTO mostrarGranulometria(int codigo) throws Exception{
        Optional<Gradacion> gradacionBuscada = gradacionRepo.findById(codigo);
        if(gradacionBuscada.isEmpty()){
            throw new Exception("No se ha encontrado la gradación buscada");
        }

        List<Double> resultados = new ArrayList<>();
        List<String> tamices = new ArrayList<>();

        for (int i=0; i< gradacionBuscada.get().getTamicesMasasList().size();i++){
            resultados.add(gradacionBuscada.get().getTamicesMasasList().get(i).getMasaRetenida());
            tamices.add(gradacionBuscada.get().getTamicesMasasList().get(i).getTamiz());
        }
        GradacionDTO gradacionDTO = new GradacionDTO(gradacionBuscada.get().getMuestraSuelos().getObra().getCR(),
                gradacionBuscada.get().getMuestraSuelos().getCodigo(),gradacionBuscada.get().getFechaFalla(), resultados,tamices);

        return gradacionDTO;
    }

    @Override
    public String guardarEdadesVigas(List<EdadesDto> edadesDto) throws Exception{
        List<Viga> vigasBuscadas = vigaRepo.buscarPorIdCompresion(edadesDto.get(0).codigo());

        if(vigasBuscadas.isEmpty()){
            new Exception("No se ha podido guardar la edad correctamente");
        }
        for (int i=0; i<edadesDto.size(); i++){
            vigasBuscadas.get(i).setEdad(edadesDto.get(i).edad());
            LocalDate fechaToma = vigasBuscadas.get(i).getCompresionCilindros().getFechaToma();
            int edad = edadesDto.get(i).edad();
            LocalDate fechaFalla = fechaToma.plusDays(edad);
            vigasBuscadas.get(i).setFechaFalla(fechaFalla);
            vigaRepo.save(vigasBuscadas.get(i));
        }
        return "Se han cargado las edades correctamente";
    }

    @Override
    public String subirResultadosVigas(List<VigasGetDTO> vigasGetDTOList) throws Exception {
        for (VigasGetDTO vigasGetDTO : vigasGetDTOList){
            Optional<Viga> vigaOptional = vigaRepo.findById(vigasGetDTO.id());
            if(vigaOptional.isPresent()){
                Viga viga = vigaOptional.get();
                viga.setCarga(vigasGetDTO.carga());
                viga.setA(vigasGetDTO.a());
                viga.setL(vigasGetDTO.l());
                viga.setAncho(vigasGetDTO.ancho());
                viga.setFondo(vigasGetDTO.fondo());

                // Guardar la obra asociada al cilindro
                Obra obra = viga.getCompresionCilindros().getObra();
                obraRepo.save(obra);
            }else {
                throw new Exception("No se encontró la viga con ID: " + vigasGetDTO.id());
            }
        }
        return "Se han cargado los resultados exitosamente";
    }
}