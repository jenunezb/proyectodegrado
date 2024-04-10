package laboratorio.controladores;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import laboratorio.dto.*;
import laboratorio.modelo.Empresa;
import laboratorio.modelo.Sede;
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
    @GetMapping("/listarSedes")
    public ResponseEntity<MensajeDTO<List<SedeDTO>>> listarSedes()throws Exception{
        List<SedeDTO> sedeDTOS = administradorServicio.listarSedes();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, sedeDTOS));
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

    @GetMapping("/listaAdministradores")
    public ResponseEntity<MensajeDTO<List<AdministradorGetDTO>>> listarAdministradores(){
        List<AdministradorGetDTO> administradorGetDTOS = administradorServicio.listaradministradores();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, administradorGetDTOS));
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
    @DeleteMapping("/eliminarAdministrador/{correo}")
    public ResponseEntity<MensajeDTO<String>> eliminarAdministrador(@PathVariable String correo) throws Exception {
        administradorServicio.eliminarAdministrador(correo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se eliminó el administrador correctamente"));
    }

    @DeleteMapping("/eliminarEmpresa/{nombre}")
    public ResponseEntity<MensajeDTO<String>>eliminarEmpresa(@PathVariable String nombre) throws Exception {
        administradorServicio.eliminarEmpresa(nombre);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Se eliminó la empresa correctamente" ));
    }
    @GetMapping("/buscarEmpresa/{nit}")
    public ResponseEntity<?> buscarEmpresa(@PathVariable String nit) {
        try {
            Empresa empresaEncontrada = administradorServicio.buscarEmpresa(nit);
            if (empresaEncontrada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la empresa con el nit: " + nit);
            }
            return ResponseEntity.ok(empresaEncontrada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar la empresa: " + e.getMessage());
        }
    }

    @PutMapping("/editarEmpresa{nit}")
    public ResponseEntity<Empresa> editarEmpresa(@RequestBody Empresa empresa) {
        try {
            Empresa empresaEditada = administradorServicio.editarEmpresa(empresa);
            return new ResponseEntity<>(empresaEditada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/agregarSede")
    public ResponseEntity<MensajeDTO<String>> crearSede(@Valid @RequestBody SedeDTO sedeDTO)throws Exception{
        administradorServicio.crearSede(sedeDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó la sede correctamente"));
    }
    @DeleteMapping("/eliminarSede/{ciudad}")
    public ResponseEntity<MensajeDTO<String>>eliminarSede(@PathVariable String ciudad) throws Exception {
        administradorServicio.eliminarSede(ciudad);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Se eliminó la ciudad correctamente" ));
    }
    @GetMapping("/buscarSede/{ciudad}")
    public ResponseEntity<?> buscarSede(@PathVariable String ciudad) {
        try {
            Sede sedeEncontrada = administradorServicio.buscarSede(ciudad);
            if (sedeEncontrada == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la sede con la ciudad: " + ciudad);
            }
            return ResponseEntity.ok(sedeEncontrada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar la sede: " + e.getMessage());
        }
    }
    @PutMapping("/editarSede{ciudad}")
    public ResponseEntity<Sede> editarSede(@RequestBody Sede sede) {
        try {
            Sede sedeEditada = administradorServicio.editarSede(sede);
            return new ResponseEntity<>(sedeEditada, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
