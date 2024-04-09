package laboratorio.repositorios;

import laboratorio.modelo.Ingeniero;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IngenieroRepo extends JpaRepository<Ingeniero, Integer> {

    Ingeniero findByCorreo(String correo);

    Optional<Ingeniero> findByCedula(String cedula);

    boolean existsByCedula (String cedula);
}
