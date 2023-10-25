package laboratorio.repositorios;

import laboratorio.modelo.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministradorRepo extends JpaRepository<Administrador, Integer> {
}
