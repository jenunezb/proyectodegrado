package laboratorio.repositorios.ensayo;

import laboratorio.modelo.ensayo.Cilindro;
import laboratorio.modelo.ensayo.Viga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface VigaRepo extends JpaRepository<Viga, Integer> {
    @Query("select c from Viga c where c.compresionCilindros.obra.CR=:cr and c.fechaFalla=:fecha")
    List<Viga> findByCr(String cr, LocalDate fecha);

    @Query("select c from Viga c where c.fechaFalla=:fecha")
    List<Viga> findByDate(LocalDate fecha);

    @Query("select c from Viga c where c.compresionCilindros.codigo=:id")
    List<Viga> buscarPorIdCompresion(int id);

    @Query("select c from Viga c where c.compresionCilindros.fechaToma>=:fechaInicio and " +
            "c.compresionCilindros.fechaToma<=:fechaFin and c.compresionCilindros.obra.CR=:cr")
    List<Viga> buscarPorIntervalo(LocalDate fechaInicio, LocalDate fechaFin, String cr);

    @Query("select c from Viga c where c.fechaFalla<=:fecha")
    List<Viga> BuscarHastaLaFecha(LocalDate fecha);

}
