package laboratorio.servicios.implementacion;
import laboratorio.dto.*;
import laboratorio.modelo.*;
import laboratorio.repositorios.*;
import laboratorio.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.expression.ExpressionException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final AdministradorRepo administradorRepo;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public int crearAdministrador(AdministradorDTO administradorDTO) throws Exception{

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
    public int crearDigitador(DigitadorDTO digitadorDTO) throws Exception {
        if (estaRepetidaCedula(digitadorDTO.cedula())) {
            throw new Exception("La cédula ya se encuentra registrada");
        }

        if (estaRepetidoCorreo(digitadorDTO.correo())) {
            throw new Exception("El correo ya se encuentra registrado");
        }

        Digitador digitador = new Digitador();
        digitador.setCedula(digitadorDTO.cedula());
        digitador.setNombre(digitadorDTO.nombre());
        digitador.setTelefono(digitadorDTO.telefono());
        digitador.setCiudad(ciudadRepo.findById(digitadorDTO.ciudad()).get());
        digitador.setCorreo(digitadorDTO.correo());
        digitador.setEstado(true);
        String passwordEncriptada = passwordEncoder.encode(digitadorDTO.password());
        digitador.setPassword(passwordEncriptada);

        Digitador digitadorNuevo = digitadorRepo.save(digitador);

        return digitadorNuevo.getCodigo();
    }

    @Override
    // Como el digitador y el ingeniero comparten los mismos atributos, ingreso DigitadorDTO y no afecta en nada el método
    public int crearIngeniero(DigitadorDTO digitadorDTO) throws Exception {
        if (estaRepetidaCedula(digitadorDTO.cedula())) {
            throw new Exception("La cédula ya se encuentra registrada");
        }

        if (estaRepetidoCorreo(digitadorDTO.correo())) {
            throw new Exception("El correo ya se encuentra registrado");
        }

        Ingeniero ingeniero = new Ingeniero();
        ingeniero.setCedula(digitadorDTO.cedula());
        ingeniero.setNombre(digitadorDTO.nombre());
        ingeniero.setTelefono(digitadorDTO.telefono());
        ingeniero.setCiudad(ciudadRepo.findById(digitadorDTO.ciudad()).get());
        ingeniero.setCorreo(digitadorDTO.correo());
        ingeniero.setEstado(true);
        String passwordEncriptada = passwordEncoder.encode(digitadorDTO.password());
        ingeniero.setPassword(passwordEncriptada);

        Ingeniero ingenieroNuevo = ingenieroRepo.save(ingeniero);

        return ingenieroNuevo.getCodigo();
    }

    @Override
    public int crearEmpresa(EmpresaDTO empresaDTO) throws Exception {
        if (empresaRepo.existsById(empresaDTO.nit())) {
            throw new Exception("La empresa ya se encuentra registrada");
        }

        Empresa empresa = new Empresa();
        empresa.setNit(empresaDTO.nit());
        empresa.setDireccion(empresaDTO.direccion());
        empresa.setNombre(empresaDTO.nombre());
        empresa.setTelefono(empresaDTO.telefono());

        Empresa empresaNueva = empresaRepo.save(empresa);

        return empresaNueva.getNit();
    }

    @Override
    public int crearObra(ObraDTO obraDTO) throws Exception {

        if (obraRepo.existsByCR(obraDTO.cr())) {
            throw new Exception("Ya existe una obra con este CR: " + obraDTO.cr());
        }

        Obra obra = new Obra();
        Optional<Empresa> empresa = empresaRepo.findById(obraDTO.empresa().getNit());
        obra.setEmpresa(empresa.get());
        obra.setDireccion(obraDTO.direccion());
        obra.setFecha_inicio(obraDTO.fechaInicio());
        obra.setNombre(obraDTO.nombre());
        obra.setTelefono(obraDTO.telefono());
        obra.setCiudad(obraDTO.ciudad());
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
                    ingenieroList.get(i).getCiudad(),
                    ingenieroList.get(i).getTelefono(),
                    ingenieroList.get(i).getCorreo()
            ));
        }
        return ingenieroGetDTOS;
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
    public List<DetallePersonaDTO> listarDigitadores() {
        List<Digitador> digitadorList = digitadorRepo.findAll();
        List<DetallePersonaDTO> personaDTOS = new ArrayList<>();

        for (Digitador digitador : digitadorList) {
            List<String> obras = digitador.getObras().stream()
                    .map(Obra::getCR)
                    .collect(Collectors.toList());

            personaDTOS.add(new DetallePersonaDTO(
                    digitador.getCedula(),
                    digitador.getNombre(),
                    digitador.getTelefono(),
                    digitador.getCiudad().getNombre(),
                    obras
            ));
        }
        return personaDTOS;
    }

    @Override
    public int buscarObra(int codigoObra) throws Exception {
        return 0;
    }

    @Override
    public List<ObraDTO> listarObras() {
        return null;
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
        Digitador digitador = digitadorRepo.findByCorreo(correo);

        return digitador != null;
    }

    public boolean estaRepetidaCiudad(String ciudad){
        Ciudad ciudad1 = ciudadRepo.findByNombre(ciudad);

        return ciudad1 !=null;
    }

    public void crearCiudad(String ciudad) throws Exception{

        if(estaRepetidaCiudad(ciudad)){
            throw new Exception("La ciudad ya se encuentra registrada");
        }
        if(ciudad.isEmpty()){
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
            // Si se produce una excepción de violación de integridad de datos (como la restricción de clave externa)
            // Manejamos el error proporcionando un mensaje significativo.
            throw new Exception("No se puede eliminar la ciudad '" + ciudad + "' porque está en uso.");
        }
    }

    public Ciudad buscarCiudad(String ciudad) throws Exception{
        Ciudad ciudadBuscada = ciudadRepo.findByNombre(ciudad);

        if(ciudadBuscada==null){
            throw new Exception("No se ha encontrado la ciudad buscada");
        }
        return ciudadBuscada;
    }

    public void eliminarEmpresa(String nombre) throws Exception {
        Empresa empresaEliminar = buscarEmpresa(nombre);

        try {
            empresaRepo.deleteByNombre(empresaEliminar.getNombre());
        } catch (DataIntegrityViolationException e) {
            throw new Exception("No se puede eliminar la empresa '" + nombre + "' porque está en uso.");
        }
    }

    public Empresa buscarEmpresa(String nombre) throws Exception {
        Empresa empresaBuscada = empresaRepo.findByNombre(nombre);

        if (empresaBuscada == null) {
            throw new Exception("No se ha encontrado la empresa buscada con nombre: " + nombre);
        }

        return empresaBuscada;
    }
}
