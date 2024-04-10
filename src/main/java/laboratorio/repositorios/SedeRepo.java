package laboratorio.repositorios;

import jakarta.transaction.Transactional;
import laboratorio.modelo.Ciudad;
import laboratorio.modelo.Empresa;
import laboratorio.modelo.Sede;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SedeRepo extends JpaRepository<Sede, Integer> {

    @Query("SELECT e FROM Sede e WHERE e.ciudad = :nombreCiudad")
    Sede findByCiudad(@Param("nombreCiudad") String nombreCiudad);

    // Eliminar sedes por nombre de ciudad
    @Transactional
    @Modifying
    @Query("DELETE FROM Sede e WHERE e.ciudad = :nombreCiudad")
    void deleteByCiudad(String nombreCiudad);
}

