package laboratorio.repositorios;

import laboratorio.modelo.Ciudad;
import laboratorio.modelo.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepo  extends JpaRepository<Sede, Integer> {
    @Query("SELECT e FROM Sede e WHERE e.ciudad = :ciudad")
    boolean findByCiudad(@Param("ciudad") String ciudad);

}
