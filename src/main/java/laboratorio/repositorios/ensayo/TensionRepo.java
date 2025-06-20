package laboratorio.repositorios.ensayo;

import laboratorio.modelo.ensayo.aceros.Tension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TensionRepo extends JpaRepository<Tension, Integer> {
    // Puedes agregar métodos personalizados aquí si los necesitas
}
