package laboratorio.servicios.implementacion;
import laboratorio.dto.suelos.ResaltesDTO;
import laboratorio.modelo.Obra;
import laboratorio.modelo.ensayo.Aceros.Resalte;
import laboratorio.modelo.ensayo.Muestra;
import laboratorio.repositorios.MuestraRepo;
import laboratorio.repositorios.ObraRepo;
import laboratorio.repositorios.ensayo.ResalteRepo;
import laboratorio.servicios.interfaces.ResalteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResalteServicioImpl implements ResalteServicio {

    private final ResalteRepo resalteRepo;
    private final MuestraRepo muestraRepo;
    private final ObraRepo obraRepo;

    @Override
    public int crearResalte(ResaltesDTO resaltesDTO) throws Exception {
        if (resaltesDTO == null) {
            throw new IllegalArgumentException("El objeto resalteDTO no puede ser nulo");
        }

        Muestra muestra = muestraRepo.findById(resaltesDTO.muestra())
                .orElseThrow(() -> new Exception("Muestra no encontrada"));

        Obra obra = obraRepo.findByCR(resaltesDTO.obra());

        Resalte resalte = new Resalte();
        resalte.setCodigo(resaltesDTO.codigo());
        resalte.setNorma(resaltesDTO.norma());
        resalte.setFechaMedicion(resaltesDTO.fechaMedicion());
        resalte.setAltura(resaltesDTO.altura());
        resalte.setEspaciamiento(resaltesDTO.espaciamiento());
        resalte.setEspaciamientoLongitudinalGrafiles(resaltesDTO.espaciamientoLongitudinalGrafiles());
        resalte.setSeparacion(resaltesDTO.separacion());
        resalte.setAngulo(resaltesDTO.angulo());
        resalte.setResaltesDireccionInversa(resaltesDTO.resaltesDireccionInversa());
        resalte.setLongitudProbeta(resaltesDTO.longitudProbeta());
        resalte.setPesoProbeta(resaltesDTO.pesoProbeta());
        resalte.setMuestra(muestra);
        resalte.setObra(obra);

        Resalte resalteGuardado = resalteRepo.save(resalte);

        return resalteGuardado.getCodigo();
    }

    @Override
    public List<ResaltesDTO> listarResaltes() {
        List<Resalte> resalteList = resalteRepo.findAll();
        List<ResaltesDTO> resalteDTOs = new ArrayList<>();

        for (Resalte resalte : resalteList) {
            resalteDTOs.add(new ResaltesDTO(
                    resalte.getCodigo(),
                    resalte.getNorma(),
                    resalte.getFechaMedicion(),
                    resalte.getAltura(),
                    resalte.getEspaciamiento(),
                    resalte.isEspaciamientoLongitudinalGrafiles(),
                    resalte.getSeparacion(),
                    resalte.getAngulo(),
                    resalte.isResaltesDireccionInversa(),
                    resalte.getLongitudProbeta(),
                    resalte.getPesoProbeta(),
                    resalte.getMuestra().getCodigo(),
                    resalte.getObra().getCR())
            );
        }
        return resalteDTOs;
    }

    @Override
    public ResaltesDTO obtenerResalte(int codigoResalte) throws Exception {
        Resalte resalte = resalteRepo.findById(codigoResalte)
                .orElseThrow(() -> new Exception("Resalte con código " + codigoResalte + " no encontrado"));

        return new ResaltesDTO(
                resalte.getCodigo(),
                resalte.getNorma(),
                resalte.getFechaMedicion(),
                resalte.getAltura(),
                resalte.getEspaciamiento(),
                resalte.isEspaciamientoLongitudinalGrafiles(),
                resalte.getSeparacion(),
                resalte.getAngulo(),
                resalte.isResaltesDireccionInversa(),
                resalte.getLongitudProbeta(),
                resalte.getPesoProbeta(),
                resalte.getMuestra().getCodigo(), // Asegúrate de usar el código adecuado
                resalte.getObra().getCR() // Asegúrate de usar el código adecuado
        );
    }

    @Override
    public int actualizarResalte(int codigoResalte, ResaltesDTO resaltesDTO) throws Exception {
        if (resaltesDTO == null) {
            throw new IllegalArgumentException("El objeto resalteDTO no puede ser nulo");
        }

        // Obtener el resalte existente
        Resalte resalteExistente = resalteRepo.findById(codigoResalte)
                .orElseThrow(() -> new Exception("Resalte con código " + codigoResalte + " no encontrado"));

        // Actualizar los valores del resalte con los nuevos datos
        resalteExistente.setNorma(resaltesDTO.norma());
        resalteExistente.setFechaMedicion(resaltesDTO.fechaMedicion());
        resalteExistente.setAltura(resaltesDTO.altura());
        resalteExistente.setEspaciamiento(resaltesDTO.espaciamiento());
        resalteExistente.setEspaciamientoLongitudinalGrafiles(resaltesDTO.espaciamientoLongitudinalGrafiles());
        resalteExistente.setSeparacion(resaltesDTO.separacion());
        resalteExistente.setAngulo(resaltesDTO.angulo());
        resalteExistente.setResaltesDireccionInversa(resaltesDTO.resaltesDireccionInversa());
        resalteExistente.setLongitudProbeta(resaltesDTO.longitudProbeta());
        resalteExistente.setPesoProbeta(resaltesDTO.pesoProbeta());

        // Actualizar muestra y obra
        Muestra muestra = muestraRepo.findById(resaltesDTO.muestra())
                .orElseThrow(() -> new Exception("Muestra no encontrada"));
        resalteExistente.setMuestra(muestra);

        Obra obra = obraRepo.findByCR(resaltesDTO.obra());
        resalteExistente.setObra(obra);

        // Guardar el resalte actualizado
        Resalte resalteActualizado = resalteRepo.save(resalteExistente);

        return resalteActualizado.getCodigo();
    }
}
