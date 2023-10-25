package laboratorio.repositorios;

import laboratorio.modelo.Obra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ObraRepo extends JpaRepository<Obra, Integer> {

    boolean existsByCR (String CR);

}
