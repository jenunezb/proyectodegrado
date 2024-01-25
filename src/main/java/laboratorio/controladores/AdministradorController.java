package laboratorio.controladores;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import laboratorio.Excepciones.Excepciones;
import laboratorio.dto.*;
import laboratorio.servicios.interfaces.AdministradorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/administrador")
@AllArgsConstructor

public class AdministradorController {

    private final AdministradorServicio administradorServicio;

    @PostMapping("/agregarDigitador")
    public ResponseEntity<MensajeDTO<String>> crearDigitador(@Valid @RequestBody DigitadorDTO digitadorDTO)throws Exception{
        administradorServicio.crearDigitador(digitadorDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó el digitador correctamente"));
    }

    @PostMapping("/agregarIngeniero")
    public ResponseEntity<MensajeDTO<String>> crearIngeniero(@Valid @RequestBody DigitadorDTO digitadorDTO)throws Exception{
        administradorServicio.crearIngeniero(digitadorDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó el ingeniero correctamente"));
    }

    @PostMapping("/agregarEmpresa")
    public ResponseEntity<MensajeDTO<String>> crearEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO)throws Exception{
        administradorServicio.crearEmpresa(empresaDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó la empresa correctamente"));
    }

    @PostMapping("/agregarObra")
    public ResponseEntity<MensajeDTO<String>> crearObra(@Valid @RequestBody ObraDTO obraDTO)throws Exception{
        administradorServicio.crearObra(obraDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó la obra correctamente"));
    }

    @GetMapping("/listaIngenieros")
    public ResponseEntity<MensajeDTO<List<IngenieroGetDTO>>> listarIngenieros()throws Exception{
        List<IngenieroGetDTO> ingenieroGetDTOS = administradorServicio.listarIngenieros();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, ingenieroGetDTOS));
    }

    @GetMapping("/detalleIngeniero/{codigoIngeniero}")
    public ResponseEntity<MensajeDTO<DetallePersonaDTO>> detalleIngeniero(@RequestParam int codigoIngeniero)throws Exception{
        DetallePersonaDTO detallePersonaDTO = administradorServicio.detalleIngeniero(codigoIngeniero);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, detallePersonaDTO));
    }

    @GetMapping("/detalleDigitador/{codigoDigitador}")
    public ResponseEntity<MensajeDTO<DetallePersonaDTO>> detalleDigitador(@RequestParam int codigoDigitador)throws Exception{
        DetallePersonaDTO detallePersonaDTO = administradorServicio.detalleDigitador(codigoDigitador);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, detallePersonaDTO));
    }

    @PostMapping("/agregarCiudad")
    public ResponseEntity<MensajeDTO<String>> crearCiudad(@Valid @RequestBody String ciudad) throws Excepciones {
        administradorServicio.crearCiudad(ciudad);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó la ciudad correctamente"));
    }
}