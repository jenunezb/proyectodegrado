package laboratorio.servicios.interfaces;

import laboratorio.modelo.ensayo.aceros.Tension;

import java.util.List;
import java.util.Optional;

public interface TensionService {
    Tension guardar(Tension tension);
    Optional<Tension> buscarPorId(int codigo);
    List<Tension> listarTodos();
    void eliminarPorId(int codigo);
    Tension actualizar(int codigo, Tension tensionActualizada);
}
