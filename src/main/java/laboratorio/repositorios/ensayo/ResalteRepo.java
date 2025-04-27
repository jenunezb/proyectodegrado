package laboratorio.repositorios.ensayo;


import laboratorio.modelo.ensayo.Aceros.Resalte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface ResalteRepo extends JpaRepository<Resalte, Integer> {
    // Aquí puedes agregar métodos personalizados si los necesitas
    // Ejemplo: List<Resalte> findByObraId(int obraId);

    List<Resalte> findByObra_Id(int obraId);

}