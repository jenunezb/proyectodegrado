package laboratorio;

import laboratorio.dto.CompresionCilindrosDTO;
import laboratorio.modelo.TipoMuestraCilindro;
import laboratorio.servicios.interfaces.DigitadorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
@SpringBootTest
public class DigitadorServicioTest {

    @Autowired
    DigitadorServicio digitadorServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void agregarMuestraCilindros(){
        CompresionCilindrosDTO compresionCilindrosDTO = new CompresionCilindrosDTO(
                "06192",
                TipoMuestraCilindro.COMPRESION_6,
                "6C",
                "001",
                LocalDate.of(2023,10,24),
                21,
                6,
                "APT 106, ASENTAMIENTO Y OTRAS VAINAS",
                ""
        );

        digitadorServicio.agregarMuestra(compresionCilindrosDTO);

        int nuevo =digitadorServicio.agregarMuestra(compresionCilindrosDTO);
        System.out.println(compresionCilindrosDTO);
        Assertions.assertNotEquals(0, nuevo);
    }
}