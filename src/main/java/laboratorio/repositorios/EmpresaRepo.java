package laboratorio.repositorios;

import jakarta.transaction.Transactional;
import laboratorio.modelo.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepo extends JpaRepository<Empresa, Integer>
{

    @Query("SELECT e FROM Empresa e WHERE e.nombre = :nombre")
    Empresa findByNombre(@Param("nombre") String nombre);

    @Query("SELECT e FROM Empresa e WHERE e.nit = :nit")
    Empresa findByNit(@Param("nit") String nit);
    @Transactional
    @Modifying
    @Query("DELETE FROM Empresa e WHERE e.nombre = :nombreEmpresa")
    void deleteByNombre(String nombreEmpresa);
}

