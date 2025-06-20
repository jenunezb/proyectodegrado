package laboratorio.servicios.implementacion;

import laboratorio.modelo.ensayo.aceros.Tension;
import laboratorio.repositorios.ensayo.TensionRepo;
import laboratorio.servicios.interfaces.TensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TensionServiceImpl implements TensionService {

    private final TensionRepo tensionRepository;

    @Autowired
    public TensionServiceImpl(TensionRepo tensionRepository) {
        this.tensionRepository = tensionRepository;
    }

    @Override
    public Tension guardar(Tension tension) {
        return tensionRepository.save(tension);
    }

    @Override
    public Optional<Tension> buscarPorId(int codigo) {
        return tensionRepository.findById(codigo);
    }

    @Override
    public List<Tension> listarTodos() {
        return tensionRepository.findAll();
    }

    @Override
    public void eliminarPorId(int codigo) {
        tensionRepository.deleteById(codigo);
    }

    @Override
    public Tension actualizar(int codigo, Tension tensionActualizada) {
        return tensionRepository.findById(codigo)
                .map(tensionExistente -> {
                    tensionExistente.setLongProbeta(tensionActualizada.getLongProbeta());
                    tensionExistente.setPesoProbeta(tensionActualizada.getPesoProbeta());
                    tensionExistente.setDiamInicial(tensionActualizada.getDiamInicial());
                    tensionExistente.setDiamFinal(tensionActualizada.getDiamFinal());
                    tensionExistente.setAreaInicial(tensionActualizada.getAreaInicial());
                    tensionExistente.setAreaFinal(tensionActualizada.getAreaFinal());
                    tensionExistente.setLongCalibradaInicial(tensionActualizada.getLongCalibradaInicial());
                    tensionExistente.setLongCalibradaFinal(tensionActualizada.getLongCalibradaFinal());
                    tensionExistente.setCargaMax(tensionActualizada.getCargaMax());
                    tensionExistente.setCargaFluencia(tensionActualizada.getCargaFluencia());
                    return tensionRepository.save(tensionExistente);
                })
                .orElseThrow(() -> new RuntimeException("Tensión no encontrada con código: " + codigo));
    }
}
