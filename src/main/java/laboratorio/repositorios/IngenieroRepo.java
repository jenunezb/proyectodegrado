package laboratorio.repositorios;

import laboratorio.modelo.Empresa;
import laboratorio.modelo.Ingeniero;
import laboratorio.modelo.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IngenieroRepo extends JpaRepository<Ingeniero, Integer> {

    Ingeniero findByCorreo(String correo);

    Optional<Ingeniero> findByCedula(String cedula);

    @Query("SELECT e FROM Ingeniero e WHERE e.cedula = :cedula")
    Ingeniero findBycedula(@Param("cedula") String cedula);

}
