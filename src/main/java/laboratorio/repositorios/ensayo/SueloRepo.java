package laboratorio.repositorios.ensayo;

import laboratorio.modelo.ensayo.MuestraSuelos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SueloRepo extends JpaRepository<MuestraSuelos, Integer> {

}
