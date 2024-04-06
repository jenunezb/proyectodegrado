package laboratorio.controladores;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import laboratorio.dto.*;
import laboratorio.modelo.Empresa;
import laboratorio.servicios.interfaces.AdministradorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @GetMapping("/listarEmpresas")
    public ResponseEntity<MensajeDTO<List<EmpresaDTO>>> listarEmpresas()throws Exception{
        List<EmpresaDTO> empresaGetDTOS = administradorServicio.listarEmpresas();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, empresaGetDTOS));
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
    public ResponseEntity<MensajeDTO<String>> crearCiudad(@RequestBody(required = false) String ciudad) throws Exception {
        if (ciudad == null || ciudad.isEmpty()) {
            return ResponseEntity.badRequest().body(new MensajeDTO<>(true, "El nombre de la ciudad no puede estar vacío"));
        }

        administradorServicio.crearCiudad(ciudad);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se agregó la ciudad correctamente"));
    }

    @DeleteMapping("/eliminarCiudad/{ciudad}")
    public ResponseEntity<MensajeDTO<String>> eliminarCiudad(@PathVariable String ciudad) throws Exception {
        administradorServicio.eliminarCiudad(ciudad);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se eliminó la ciudad correctamente"));
    }
    @DeleteMapping("/eliminarEmpresa/{nit}")
    public ResponseEntity<?> eliminarEmpresa(@PathVariable String nit) throws Exception {
        administradorServicio.eliminarEmpresa(nit);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se eliminó la empresa correctamente"));
    }
    @GetMapping("/buscarEmpresa/{nombre}")
    public ResponseEntity<MensajeDTO<Empresa>>  buscarEmpresa(@PathVariable String nombre) throws Exception {
          Empresa empresa=administradorServicio.buscarEmpresa(nombre);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, empresa));
        }
    }
