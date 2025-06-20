package laboratorio.controladores;


import jakarta.validation.Valid;
import laboratorio.dto.*;
import laboratorio.dto.suelos.SuelosDTO;
import laboratorio.servicios.interfaces.AdministradorServicio;
import laboratorio.servicios.interfaces.AutenticacionServicio;
import laboratorio.servicios.interfaces.DigitadorServicio;
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
    private final DigitadorServicio digitadorServicio;

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

    @GetMapping("/listarSedes")
    public ResponseEntity<MensajeDTO<List<SedeDTO>>>listarSedes(){
        List<SedeDTO> sedeDTOS = autenticacionServicio.listarSedes();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, sedeDTOS));
    }
    @GetMapping("/listarObras")
    public ResponseEntity<MensajeDTO<List<ObraDTO>>> listarObras(){
        System.out.println("pasa");
        List<ObraDTO> obraDTO = autenticacionServicio.listarObras();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, obraDTO));
    }
    @GetMapping("/listarCilindros")
    public ResponseEntity<MensajeDTO<List<CilindrosList>>> listarCilindros()throws Exception{
        List<CilindrosList> cilindros = digitadorServicio.listarCilindros();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, cilindros));
    }

    @GetMapping("/listarVigas")
    public ResponseEntity<MensajeDTO<List<CilindrosList>>> listarVigas()throws Exception{
        List<CilindrosList> cilindros = digitadorServicio.listarVigas();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, cilindros));
    }

    @GetMapping("/listarSuelos")
    public ResponseEntity<MensajeDTO<List<SuelosDTO>>> listarSuelos()throws Exception{
        List<SuelosDTO> seccion = administradorServicio.listarSuelos();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, seccion));
    }
    @PostMapping("/listarOrden")
    public ResponseEntity<MensajeDTO<List<CilindroDTO>>> listarOrden(@RequestBody OrdenDTO ordenDtos) throws Exception {
        List<CilindroDTO> cilindros = digitadorServicio.mostrarOden(ordenDtos);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, cilindros));
    }
    @PostMapping("/listarReportes")
    public ResponseEntity<MensajeDTO<List<ReporteDTO>>> listarReportes(@RequestBody FechasReporteDTO fechasReporte)throws Exception{
        List<ReporteDTO> reporte = administradorServicio.listarReportes(fechasReporte);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, reporte));
    }
}