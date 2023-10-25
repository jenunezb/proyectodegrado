package laboratorio.repositorios;

import laboratorio.modelo.Digitador;
import laboratorio.modelo.Ingeniero;
import laboratorio.modelo.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IngenieroRepo extends JpaRepository<Ingeniero, Integer> {

    Ingeniero findByCorreo(String correo);

    Optional<Ingeniero> findByCedula(String cedula);

    boolean existsByCedula (String cedula);
}
