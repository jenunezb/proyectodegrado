package laboratorio.controladores;

import jakarta.validation.Valid;
import laboratorio.dto.MensajeDTO;
import laboratorio.dto.UsuarioDTO;
import laboratorio.dto.UsuarioGetDTO;
import laboratorio.modelo.Ciudad;
import laboratorio.servicios.interfaces.UsuarioServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@AllArgsConstructor
public class UsuarioControlador {

    private final UsuarioServicio usuarioServicio;

    //   ACTUALIZAR LA INFORMACIÓN DE UN USUARIO DESDE EL ADMIN
//    @PutMapping("/actualizar/{codigoUsuario}")
//    public ResponseEntity<MensajeDTO> actualizarUsuario(@PathVariable int codigoUsuario, @RequestBody UsuarioDTO usuarioDTO) throws Exception{
//        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, usuarioServicio.actualizarUsuario(codigoUsuario, usuarioDTO)));
//    }

    // ELIMINAR UN USUARIO DESDE EL ADMIN (OJO QUE LA ELIMINACIÓN COMO ES EN CASCADA PUEDE FALLAR)
//    @DeleteMapping("/eliminar/{codigoUsuario}")
//    public ResponseEntity<MensajeDTO> eliminarUsuario(@PathVariable int codigoUsuario) throws Exception {
//        usuarioServicio.eliminarUsuario(codigoUsuario);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(new MensajeDTO(HttpStatus.OK, false, "Usuario eliminado correctamente"));
//    }

    // HACER UNA BUSQUEDA DADO EL CODIGO
//    @GetMapping("/{codigoUsuario}")
//    public ResponseEntity<MensajeDTO> obtenerUsuario(@PathVariable int codigoUsuario) throws Exception{
//        return ResponseEntity.status(HttpStatus.OK).body( new MensajeDTO(HttpStatus.OK, false, usuarioServicio.obtenerUsuario(codigoUsuario)));
//    }

    // LISTAR TODOS LOS USUSARIOS
//    @GetMapping()
//    public List<UsuarioGetDTO> listar()
//    {
//        return  usuarioServicio.listarTodos();
//    }

    // LISTAR LAS CIUDADES NO ES NECESARIO AUN
//    @GetMapping("/ciudades")
//    public ResponseEntity<Ciudad[]> getCiudades() {
//        return ResponseEntity.status(HttpStatus.OK).body( Ciudad.values());
//    }

}
