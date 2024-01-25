package laboratorio.controladores;


import jakarta.validation.Valid;
import laboratorio.dto.*;
import laboratorio.servicios.interfaces.AdministradorServicio;
import laboratorio.servicios.interfaces.AutenticacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionController {

    private final AutenticacionServicio autenticacionServicio;
    private final AdministradorServicio administradorServicio;

    @PostMapping("/login")
    public ResponseEntity<MensajeDTO<TokenDTO>> login(@Valid @RequestBody LoginDTO loginDTO)
            throws Exception {
        TokenDTO tokenDTO = autenticacionServicio.login(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }
    @PostMapping("/crearAdministrador")
    public ResponseEntity<MensajeDTO<String>> crearAdministrador(@Valid @RequestBody AdministradorDTO administradorDTO)throws Exception{
        administradorServicio.crearAdministrador(administradorDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó el administrador correctamente"));
    }
}