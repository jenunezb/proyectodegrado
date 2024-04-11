package laboratorio.repositorios;

import laboratorio.modelo.Digitador;
import laboratorio.modelo.Ingeniero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DigitadorRepo extends JpaRepository<Digitador, Integer> {

    Digitador findByCorreo(String correo);

    Optional<Digitador> findByCedula(String cedula);

    boolean existsByCedula (String cedula);

    @Query("SELECT e FROM Digitador e WHERE e.cedula = :cedula")
    Digitador findBycedula(@Param("cedula") String cedula);

}
