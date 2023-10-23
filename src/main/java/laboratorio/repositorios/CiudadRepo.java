package laboratorio.repositorios;

import laboratorio.modelo.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepo  extends JpaRepository<Ciudad, Integer> {

}
