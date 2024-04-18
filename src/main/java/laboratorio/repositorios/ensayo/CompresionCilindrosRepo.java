package laboratorio.repositorios.ensayo;

import jakarta.transaction.Transactional;
import laboratorio.modelo.ensayo.CompresionCilindros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompresionCilindrosRepo extends JpaRepository<CompresionCilindros, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Cilindro e WHERE e.compresionCilindros.codigo = :codigo")
    void DeleteByCompresionCilindrosCodigo(int codigo);

}
