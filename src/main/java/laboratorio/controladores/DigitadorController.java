package laboratorio.controladores;


import jakarta.validation.Valid;
import laboratorio.dto.CompresionCilindrosDTO;
import laboratorio.dto.MensajeDTO;
import laboratorio.servicios.interfaces.DigitadorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/digitador")
@AllArgsConstructor
public class DigitadorController {

    private final DigitadorServicio digitadorServicio;

    @PostMapping("/agregarCilindros")
    public ResponseEntity<MensajeDTO<String>> agregarMuestra(@Valid @RequestBody CompresionCilindrosDTO compresionCilindrosDTO){
        digitadorServicio.agregarMuestra(compresionCilindrosDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "se agregó la muestra correctamente"));
    }
}