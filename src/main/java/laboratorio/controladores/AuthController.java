package laboratorio.controladores;

import jakarta.validation.Valid;
import laboratorio.dto.MensajeDTO;
import laboratorio.dto.SesionDTO;
import laboratorio.dto.UsuarioDTO;
import laboratorio.servicios.interfaces.SesionServicio;
import laboratorio.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@AllArgsConstructor
public class AuthController {

    private final UsuarioServicio usuarioServicio;
    private final SesionServicio sesionServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO> login(@Valid @RequestBody SesionDTO loginUser) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false,
                sesionServicio.login(loginUser)) );
    }

    @PostMapping("/registro")
    public ResponseEntity<MensajeDTO> registrarUsuario(@Valid @RequestBody UsuarioDTO usuario) throws Exception {
        usuarioServicio.crearUsuario(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MensajeDTO(HttpStatus.CREATED,
                false, "Usuario creado correctamente"));
    }

}
