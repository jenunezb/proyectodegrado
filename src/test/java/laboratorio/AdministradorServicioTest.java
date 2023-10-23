package laboratorio;

import laboratorio.dto.DigitadorDTO;
import laboratorio.modelo.ensayo.enums.Ciudad;
import laboratorio.servicios.interfaces.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class AdministradorServicioTest {

    @Autowired
    AdministradorServicio administradorServicio;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearDigitador() throws Exception{

        DigitadorDTO digitadorDTO = new DigitadorDTO(
                "1094927538",
                "Julián Esteban Núñez Bejarano",
                Ciudad.ARMENIA,
                "3044883381",
                "12345",
                "juesnube@gmail.com"
        );

   int nuevo = administradorServicio.crearDigitador(digitadorDTO);
    System.out.println(digitadorDTO);
        Assertions.assertNotEquals(0, nuevo);

    }
}
