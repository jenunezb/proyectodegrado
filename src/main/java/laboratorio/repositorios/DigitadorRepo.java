package laboratorio.repositorios;


import laboratorio.modelo.Digitador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DigitadorRepo extends JpaRepository<Digitador, Integer> {

    Digitador findByCorreo(String correo);

    Optional<Digitador> findByCedula(String cedula);

    boolean existsByCedula (String cedula);

}
