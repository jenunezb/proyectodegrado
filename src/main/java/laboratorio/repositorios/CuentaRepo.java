package laboratorio.repositorios;
import laboratorio.modelo.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepo extends JpaRepository<Cuenta, Integer> {

    @Query("select c from Cuenta c where c.correo=:correo")
    Optional<Cuenta> findByCorreo(@Param("correo")String correo);
}