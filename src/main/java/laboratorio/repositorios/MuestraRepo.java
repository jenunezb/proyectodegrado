package laboratorio.repositorios;

import laboratorio.modelo.ensayo.Muestra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuestraRepo extends JpaRepository<Muestra, Integer> {

}