package laboratorio.repositorios.ensayo;

import laboratorio.modelo.ensayo.Muestra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SueloRepo extends JpaRepository<Muestra, Integer> {

}
