package laboratorio.repositorios.ensayo;

import laboratorio.controladores.ImagenesController;
import laboratorio.modelo.ensayo.Cilindro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CilindroRepo extends JpaRepository<Cilindro, Integer> {

    @Query("select c from Cilindro c where c.compresionCilindros.obra.CR=:cr and c.fechaFalla<=:fecha")
    List<Cilindro> findByCr(String cr, LocalDate fecha);

    @Query("select c from Cilindro c where c.compresionCilindros.obra.CR=:cr")
    List<Cilindro> findByCrOnly(String cr);

    @Query("select c from Cilindro c where c.fechaFalla=:fecha")
    List<Cilindro> findByDate(LocalDate fecha);

    @Query("select c from Cilindro c where c.compresionCilindros.codigo=:id")
    List<Cilindro> buscarPorIdCompresion(int id);

    @Query("select c from Cilindro c where c.compresionCilindros.fechaToma>=:fechaInicio and " +
            "c.compresionCilindros.fechaToma<=:fechaFin and c.compresionCilindros.obra.CR=:cr")
    List<Cilindro> buscarPorIntervalo(LocalDate fechaInicio, LocalDate fechaFin, String cr);

    @Query("select c from Cilindro c where c.fechaFalla<=:fecha")
    List<Cilindro> BuscarHastaLaFecha(LocalDate fecha);

}