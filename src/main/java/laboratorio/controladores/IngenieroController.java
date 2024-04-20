package laboratorio.controladores;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import laboratorio.dto.MensajeDTO;
import laboratorio.dto.ObraDTO;
import laboratorio.servicios.interfaces.AdministradorServicio;
import laboratorio.servicios.interfaces.DigitadorServicio;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("api/ingeniero")
@AllArgsConstructor
public class IngenieroController {

    private final AdministradorServicio administradorServicio;
    private final DigitadorServicio digitadorServicio;
    @GetMapping("/listarObras")
    public ResponseEntity<MensajeDTO<List<ObraDTO>>> listarObras()throws Exception{
        System.out.println("pasa");
        List<ObraDTO> obraDTO = administradorServicio.listarObras();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, obraDTO));
    }
}
