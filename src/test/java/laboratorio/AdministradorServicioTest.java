package laboratorio;

import laboratorio.dto.DigitadorDTO;
import laboratorio.dto.EmpresaDTO;
import laboratorio.dto.ObraDTO;
import laboratorio.dto.PersonaDTO;
import laboratorio.modelo.Empresa;
import laboratorio.modelo.ensayo.enums.Ciudad;
import laboratorio.repositorios.CiudadRepo;
import laboratorio.repositorios.EmpresaRepo;
import laboratorio.servicios.interfaces.AdministradorServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional
@SpringBootTest
public class AdministradorServicioTest {

    @Autowired
    AdministradorServicio administradorServicio;
    @Autowired
    CiudadRepo ciudadRepo;
    @Autowired
    EmpresaRepo empresaRepo;

    @Test
    @Sql("classpath:dataset.sql")
    public void crearDigitador() throws Exception{

        DigitadorDTO digitadorDTO = new DigitadorDTO(
                "1094927538",
                "Julián Esteban Núñez Bejarano",
                ciudadRepo.getById(1),
                "3044883381",
                "12345",
                "juesnube@gmail.com"
        );

   int nuevo = administradorServicio.crearDigitador(digitadorDTO);
    System.out.println(digitadorDTO);
        Assertions.assertNotEquals(0, nuevo);

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearIngeniero() throws Exception{

        DigitadorDTO ingenieroDTO = new DigitadorDTO(

                "1094927538",
                "Julián Esteban Núñez Bejarano",
                ciudadRepo.getById(1),
                "3044883381",
                "12345",
                "juesnube@gmail.com"
        );

        int nuevo = administradorServicio.crearIngeniero(ingenieroDTO);
        System.out.println(ingenieroDTO);
        Assertions.assertNotEquals(0, nuevo);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void crearEmpresa() throws Exception{

        EmpresaDTO empresaDTO = new EmpresaDTO(
              12345,
              "Barrio tal",
              "Morelco",
              "3154544545"
        );

        int nuevo = administradorServicio.crearEmpresa(empresaDTO);
        System.out.println(empresaDTO);
        Assertions.assertNotEquals(0,nuevo);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void crearObra() throws Exception
    {
        Ciudad ciudad = ciudadRepo.getById(1);
        Empresa empresa = empresaRepo.getById(1);
        ObraDTO obraDTO = new ObraDTO(
                "Direccion de la obra",
                LocalDate.of(2023,10,22),
                "Nombre de la obra nueva",
                "Telefono de la obra nueva",
                ciudad,
                empresa,
                "06195"
        );

        int nuevo = administradorServicio.crearObra(obraDTO);

        Assertions.assertNotEquals(0,nuevo);
    }
    @Test
    @Sql("classpath:dataset.sql")
    public void asignarObra()throws Exception{
        PersonaDTO personaDTO = new PersonaDTO(
                "1",
                1
        );

        int nuevo = administradorServicio.asignarObra(personaDTO);

        Assertions.assertNotEquals(0,nuevo);
    }

}
