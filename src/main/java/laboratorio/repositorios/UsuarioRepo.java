package laboratorio.repositorios;

import laboratorio.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer> {

    @Query("select u from Usuario u where u.email = :email")
   Optional <Usuario> findByEmail(@Param("email") String email);

    @Query("select u from Usuario u")
    List<Usuario> listarUsuarios();

}
