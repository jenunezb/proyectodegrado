package laboratorio.servicios.implementacion;

import laboratorio.Excepciones.Excepciones;
import laboratorio.dto.DigitadorDTO;
import laboratorio.modelo.Digitador;
import laboratorio.repositorios.DigitadorRepo;
import laboratorio.servicios.interfaces.AdministradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministradorServicioImpl implements AdministradorServicio {

    private final DigitadorRepo digitadorRepo;


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
