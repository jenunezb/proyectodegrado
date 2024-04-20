package laboratorio.controladores;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import laboratorio.dto.CilindroDTO;
import laboratorio.dto.CompresionCilindrosDTO;
import laboratorio.dto.MensajeDTO;
import laboratorio.dto.OrdenDTO;
import laboratorio.servicios.interfaces.DigitadorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/digitador")
@AllArgsConstructor
public class DigitadorController {

    private final DigitadorServicio digitadorServicio;

    @PostMapping("/agregarCilindros")
    public ResponseEntity<MensajeDTO<String>> agregarMuestra(@Valid @RequestBody CompresionCilindrosDTO compresionCilindrosDTO) throws Exception{
        digitadorServicio.agregarMuestra(compresionCilindrosDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó la muestra correctamente"));
    }

    @PostMapping("/listarOrden")
    public ResponseEntity<MensajeDTO<List<CilindroDTO>>> listarOrden(@RequestBody OrdenDTO ordenDtos) throws Exception {
        List<CilindroDTO> cilindros = digitadorServicio.mostrarOden(ordenDtos);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, cilindros));
    }

    @PostMapping("/listarResultados")
    public ResponseEntity<MensajeDTO<List<CilindroDTO>>> listarResultados(@RequestBody OrdenDTO ordenDtos) throws Exception {
        List<CilindroDTO> cilindros = digitadorServicio.listarResultados(ordenDtos);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, cilindros));
    }

}