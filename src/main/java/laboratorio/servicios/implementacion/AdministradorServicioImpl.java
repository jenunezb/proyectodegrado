package laboratorio.servicios.implementacion;

import laboratorio.Excepciones.Excepciones;
import laboratorio.dto.DigitadorDTO;
import laboratorio.dto.EmpresaDTO;
import laboratorio.dto.ObraDTO;
import laboratorio.dto.PersonaDTO;
import laboratorio.modelo.Digitador;
import laboratorio.modelo.Empresa;
import laboratorio.modelo.Ingeniero;
import laboratorio.modelo.Obra;
import laboratorio.repositorios.DigitadorRepo;
import laboratorio.repositorios.EmpresaRepo;
import laboratorio.repositorios.IngenieroRepo;
import laboratorio.repositorios.ObraRepo;
import laboratorio.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio {

    private final DigitadorRepo digitadorRepo;
    private final IngenieroRepo ingenieroRepo;
    private final EmpresaRepo empresaRepo;
    private final ObraRepo obraRepo;


    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public int crearDigitador(DigitadorDTO digitadorDTO) throws Exception {
        if (estaRepetidaCedula(digitadorDTO.cedula())) {
            throw new Excepciones("La cédula ya se encuentra registrada");
        }

        if (estaRepetidoCorreo(digitadorDTO.correo())) {
            throw new Excepciones("El correo ya se encuentra registrado");
        }

        Digitador digitador = new Digitador();
        digitador.setCedula(digitadorDTO.cedula());
        digitador.setNombre(digitadorDTO.nombre());
        digitador.setTelefono(digitadorDTO.telefono());
        digitador.setCiudad(digitadorDTO.ciudad());
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
            throw new Excepciones("La cédula ya se encuentra registrada");
        }

        if (estaRepetidoCorreo(digitadorDTO.correo())) {
            throw new Excepciones("El correo ya se encuentra registrado");
        }

        Ingeniero ingeniero = new Ingeniero();
        ingeniero.setCedula(digitadorDTO.cedula());
        ingeniero.setNombre(digitadorDTO.nombre());
        ingeniero.setTelefono(digitadorDTO.telefono());
        ingeniero.setCiudad(digitadorDTO.ciudad());
        ingeniero.setCorreo(digitadorDTO.correo());
        ingeniero.setEstado(true);
        String passwordEncriptada = passwordEncoder.encode(digitadorDTO.password());
        ingeniero.setPassword(passwordEncriptada);

        Ingeniero ingenieroNuevo = ingenieroRepo.save(ingeniero);

        return ingenieroNuevo.getCodigo();
    }

    @Override
    public int crearEmpresa(EmpresaDTO empresaDTO) throws Exception {
        if (empresaRepo.existsById(empresaDTO.nit())){
            throw new Excepciones("La empresa ya se encuentra registrada");
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
    public int crearObra(ObraDTO obraDTO) throws Exception{

        if (obraRepo.existsByCR(obraDTO.cr())){
            throw new Excepciones("Ya existe una obra con este CR: "+obraDTO.cr());
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
    public int asignarObra(PersonaDTO personaDTO) throws Exception{
        int retorno = 0;
        Optional<Ingeniero> ingenieroBuscado = ingenieroRepo.findByCedula(personaDTO.cedula());
        Optional<Obra> obraBuscada = obraRepo.findById(personaDTO.codigoObra());
        List<Obra> obras = new ArrayList<>();
        if (ingenieroBuscado.isEmpty()){
            Optional<Digitador> digitador = digitadorRepo.findByCedula(personaDTO.cedula());
            if(digitador.isEmpty()){
                throw new Excepciones("La persona no fue encontrada");
            }else {
                if(obraBuscada.isEmpty()){
                    throw new Excepciones("La obra no fue encontrada");
                }else {
                    obras.add(obraBuscada.get());
                    digitador.get().setObras(obras);
                    Digitador digitador1 = digitadorRepo.save(digitador.get());
                    retorno = digitador1.getCodigo();
                }
            }
        }else {
            if(obraBuscada.isEmpty()){
                throw new Excepciones("La obra no fue encontrada");
            }else {
                obras.add(obraBuscada.get());
                ingenieroBuscado.get().setObras(obras);
                Ingeniero ingeniero = ingenieroRepo.save(ingenieroBuscado.get());
                retorno = ingeniero.getCodigo();
            }
        }

        return retorno;
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
}
