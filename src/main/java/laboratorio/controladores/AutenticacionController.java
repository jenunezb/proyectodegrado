package laboratorio.controladores;


import jakarta.validation.Valid;
import laboratorio.dto.*;
import laboratorio.servicios.interfaces.AdministradorServicio;
import laboratorio.servicios.interfaces.AutenticacionServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/ciudades")
    public ResponseEntity<MensajeDTO<List<CiudadGetDTO>>>listarCiudades(){
        List<CiudadGetDTO> ciudadGetDTOS = autenticacionServicio.listarCiudades();
            return ResponseEntity.ok().body(new MensajeDTO<>(false, ciudadGetDTOS));
        }
    @GetMapping("/empresas")
    public ResponseEntity<MensajeDTO<List<EmpresaDTO>>>listarEmpresas(){
        List<EmpresaDTO> empresaDTOS = autenticacionServicio.listarEmpresas();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, empresaDTOS));
    }

}