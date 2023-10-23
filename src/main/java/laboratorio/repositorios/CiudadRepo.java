package laboratorio.repositorios;

import laboratorio.modelo.ensayo.enums.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepo  extends JpaRepository<Ciudad, Integer> {

}
