package laboratorio.repositorios;


import laboratorio.modelo.Digitador;
import laboratorio.modelo.ensayo.Cilindro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DigitadorRepo extends JpaRepository<Digitador, Integer> {

    Digitador findByCorreo(String correo);

    Optional<Digitador> findByCedula(String cedula);

    boolean existsByCedula (String cedula);

}
