package laboratorio.repositorios;

import jakarta.transaction.Transactional;
import laboratorio.modelo.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AdministradorRepo extends JpaRepository<Administrador, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Administrador a WHERE a.correo = :correo")
    void deleteByCorreo(String correo);


    Optional<Administrador> findByCorreo(String correo);
}
