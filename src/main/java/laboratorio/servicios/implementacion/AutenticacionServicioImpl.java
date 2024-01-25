package laboratorio.servicios.implementacion;
import laboratorio.dto.LoginDTO;
import laboratorio.dto.TokenDTO;
import laboratorio.modelo.Cuenta;
import laboratorio.modelo.Digitador;
import laboratorio.modelo.Ingeniero;
import laboratorio.repositorios.CuentaRepo;
import laboratorio.servicios.interfaces.AutenticacionServicio;
import laboratorio.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final CuentaRepo cuentaRepo;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Cuenta> cuentaOptional = cuentaRepo.findByCorreo(loginDTO.email());
        if(cuentaOptional.isEmpty()){
            throw new Exception("No existe el correo ingresado");
        }
        Cuenta cuenta = cuentaOptional.get();
        if( !passwordEncoder.matches(loginDTO.password(), cuenta.getPassword()) ){
            throw new Exception("La contraseña ingresada es incorrecta");
        }
        return new TokenDTO( crearToken(cuenta) );
    }
    private String crearToken(Cuenta cuenta){
        String rol;
        String nombre;
        if( cuenta instanceof Ingeniero){
            rol = "ingeniero";
            nombre = ((Ingeniero) cuenta).getNombre();
        }else if( cuenta instanceof Digitador){
            rol = "digitador";
            nombre = ((Digitador) cuenta).getNombre();
        }else{
            rol = "administrador";
            nombre = "Administrador";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", cuenta.getCodigo());

        return jwtUtils.generarToken(cuenta.getCorreo(), map);
    }
}