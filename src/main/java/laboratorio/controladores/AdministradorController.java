package laboratorio.controladores;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import laboratorio.dto.*;
import laboratorio.modelo.*;
import laboratorio.servicios.interfaces.AdministradorServicio;
import laboratorio.servicios.interfaces.DigitadorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/administrador")
@AllArgsConstructor

public class AdministradorController {


    private final AdministradorServicio administradorServicio;
    private final DigitadorServicio digitadorServicio;

    @PostMapping("/agregarDigitador")
    public ResponseEntity<MensajeDTO<String>> crearDigitador(@Valid @RequestBody UsuarioDTO usuarioDTO)throws Exception{
        administradorServicio.crearDigitador(usuarioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó el digitador correctamente"));
    }

    @PostMapping("/agregarIngeniero")
    public ResponseEntity<MensajeDTO<String>> crearIngeniero(@Valid @RequestBody UsuarioDTO usuarioDTO)throws Exception{
        administradorServicio.crearIngeniero(usuarioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó el ingeniero correctamente"));
    }
    @PostMapping("/agregarCliente")
    public ResponseEntity<MensajeDTO<String>> crearCliente(@Valid @RequestBody ClienteDTO clienteDTO)throws Exception{
        administradorServicio.crearCliente(clienteDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó el ingeniero correctamente"));
    }

    @PostMapping("/agregarEmpresa")
    public ResponseEntity<MensajeDTO<String>> crearEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO)throws Exception{
        administradorServicio.crearEmpresa(empresaDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó la empresa correctamente"));
    }
    @GetMapping("/listarEmpresas")
    public ResponseEntity<MensajeDTO<List<EmpresaDTO>>> listarEmpresas()throws Exception{
        System.out.println("pasa");
        List<EmpresaDTO> empresaGetDTOS = administradorServicio.listarEmpresas();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, empresaGetDTOS));
    }

    @GetMapping("/listarObras")
    public ResponseEntity<MensajeDTO<List<ObraDTO>>> listarObras()throws Exception{
        System.out.println("pasa");
        List<ObraDTO> obraDTO = administradorServicio.listarObras();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, obraDTO));
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

    @GetMapping("/listarDigitadores")
    public ResponseEntity<MensajeDTO<List<UsuarioDTO>>> listarDigitadores(){
        List<UsuarioDTO> digitadores = administradorServicio.listarDigitadores();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, digitadores));
    }
    @GetMapping("/listarClientes")
    public ResponseEntity<MensajeDTO<List<ClienteGetDTO>>> listarClientes(){
        List<ClienteGetDTO> clientes = administradorServicio.listarClientes();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, clientes));
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
    @DeleteMapping("/eliminarDigitador/{correo}")
    public ResponseEntity<MensajeDTO<String>> eliminarDigitador(@PathVariable String correo) throws Exception {
        administradorServicio.eliminarDigitador(correo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se eliminó el digitador correctamente"));
    }
    @DeleteMapping("/eliminarAdministrador/{correo}")
    public ResponseEntity<MensajeDTO<String>> eliminarAdministrador(@PathVariable String correo) throws Exception {
        administradorServicio.eliminarAdministrador(correo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se eliminó el administrador correctamente"));
    }
    @DeleteMapping("/eliminarIngeniero{correo}")
    public ResponseEntity<MensajeDTO<String>> eliminarIngeniero(@PathVariable String correo) throws Exception {
        administradorServicio.eliminarIngeniero(correo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se eliminó el ingeniero correctamente"));
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
    @GetMapping("/buscarIngenieros/{id}")
    public ResponseEntity<IngenieroGetDTO> buscarIngenieroPorCedula(@PathVariable int id) throws Exception{
            IngenieroGetDTO ingeniero = administradorServicio.buscarIngeniero(id);
        return ResponseEntity.ok().body(ingeniero);
    }

    @GetMapping("/buscarDigitador/{cedula}")
    public ResponseEntity<?> buscarDigitadorPorCedula(@PathVariable String cedula) {
        try {
            Digitador digitador = administradorServicio.buscarDigitadorPorCedula(cedula);
            return ResponseEntity.ok(digitador);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
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

    @DeleteMapping("/eliminarCilindro/{codigo}")
    public ResponseEntity<MensajeDTO<String>>eliminarCilindro(@PathVariable int codigo) throws Exception {
        administradorServicio.eliminarCompresionCilindro(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false,"Se eliminó el cilindro correctamente" ));
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
    @GetMapping("/buscarAdministrador/{correo}")
    public ResponseEntity<?> buscarAdministrador(@PathVariable String correo) {
        try {
            Optional<Cuenta> adminBuscado = administradorServicio.buscarAdministrador(correo);

            if (adminBuscado.isPresent()) {
                return ResponseEntity.ok(adminBuscado.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el administrador con correo: " + correo);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar el administrador: " + e.getMessage());
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
    @PutMapping("/editarIngeniero/{cedula}")
    public ResponseEntity<Ingeniero> editarIngeniero(@PathVariable String cedula, @RequestBody Ingeniero ingeniero) {
        try {
            Ingeniero ingenieroActualizado = administradorServicio.editarIngeniero(ingeniero);
            return ResponseEntity.ok(ingenieroActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/editarAdministrador")
    public ResponseEntity<String> editarAdministrador(@RequestBody String correo) {
        try {
            administradorServicio.editarAdministrador(correo);
            return ResponseEntity.ok("{\"message\": \"Administrador actualizado correctamente\"}");
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/agregarCilindros")
    public ResponseEntity<MensajeDTO<String>> agregarMuestra(@Valid @RequestBody CompresionCilindrosDTO compresionCilindrosDTO) throws Exception{
        digitadorServicio.agregarMuestra(compresionCilindrosDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó la muestra correctamente"));
    }

    @GetMapping("/listarCilindros")
    public ResponseEntity<MensajeDTO<List<CilindrosList>>> listarCilindros()throws Exception{
        List<CilindrosList> cilindros = digitadorServicio.listarCilindros();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, cilindros));
    }

    @GetMapping("/listarSeccion")
    public ResponseEntity<MensajeDTO<List<TipoMuestraCilindro>>> listarSeccion()throws Exception{
        List<TipoMuestraCilindro> seccion = administradorServicio.listarSeccion();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, seccion));
    }

    @GetMapping("/listarEdades/{id}")
    public ResponseEntity<MensajeDTO<List<EdadesDto>>> listarEdades(@PathVariable int id)throws Exception{
        List<EdadesDto> seccion = administradorServicio.listarEdades(id);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, seccion));
    }
    @PostMapping("/guardarEdades")
    public ResponseEntity<MensajeDTO<String>> guardarEdades(@RequestBody List<EdadesDto> listaEdades) throws Exception {
        String seccion = administradorServicio.guardarEdades(listaEdades);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, seccion));
    }

    @PostMapping("/listarOrden")
    public ResponseEntity<MensajeDTO<List<CilindroDTO>>> listarOrden(@RequestBody OrdenDTO ordenDtos) throws Exception {
        List<CilindroDTO> cilindros = digitadorServicio.mostrarOden(ordenDtos);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, cilindros));
    }

    @PostMapping("/asignarObras")
    public ResponseEntity<MensajeDTO<String>> asignarObras(@RequestBody AsignarObrasRequestDTO asignarObrasRequestDTO) throws Exception {
        String seccion = administradorServicio.asignarObra(asignarObrasRequestDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, seccion));
    }
    @PostMapping("/listarResultados")
    public ResponseEntity<MensajeDTO<List<CilindroDTO>>> listarResultados(@RequestBody OrdenDTO ordenDtos) throws Exception {
        List<CilindroDTO> cilindros = digitadorServicio.listarResultados(ordenDtos);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, cilindros));
    }

    @PostMapping("/subirResultados")
    public ResponseEntity<MensajeDTO<String>> subirRultados(@RequestBody List<CilindroDTO> cilindroDTOList) throws Exception {
        String mensaje = administradorServicio.subirResultados(cilindroDTOList);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, mensaje));
    }
    @PostMapping("/listarReportes")
    public ResponseEntity<MensajeDTO<List<ReporteDTO>>> listarReportes(@RequestBody FechasReporteDTO fechasReporte)throws Exception{
        List<ReporteDTO> reporte = administradorServicio.listarReportes(fechasReporte);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, reporte));
    }
}
