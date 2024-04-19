package laboratorio.repositorios.ensayo;

import laboratorio.modelo.ensayo.Cilindro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CilindroRepo extends JpaRepository<Cilindro, Integer> {

    @Query("select c from Cilindro c where c.compresionCilindros.obra.CR=:cr and c.compresionCilindros.fechaToma=:fecha")
    List<Cilindro> findByCr(String cr, LocalDate fecha);

    @Query("select c from Cilindro c where c.fechaFalla=:fecha")
    List<Cilindro> findByDate(LocalDate fecha);

    @Query("select c from Cilindro c where c.compresionCilindros.codigo=:id")
    List<Cilindro> buscarPorIdCompresion(int id);
}