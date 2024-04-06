package laboratorio.repositorios;

import jakarta.transaction.Transactional;
import laboratorio.modelo.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepo extends JpaRepository<Empresa, Integer>
{
    Empresa findByNombre(String nombre);

    @Transactional
    @Modifying
    @Query("DELETE FROM Empresa e WHERE e.nombre = :nombreEmpresa")
    void deleteByNombre(String nombreEmpresa);
}

