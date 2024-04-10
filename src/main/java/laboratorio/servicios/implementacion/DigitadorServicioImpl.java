package laboratorio.servicios.implementacion;
import laboratorio.dto.CilindroDTO;
import laboratorio.dto.CompresionCilindrosDTO;
import laboratorio.dto.FormaFalla;
import laboratorio.modelo.ensayo.Cilindro;
import laboratorio.modelo.ensayo.CompresionCilindros;
import laboratorio.repositorios.ObraRepo;
import laboratorio.repositorios.ensayo.CilindroRepo;
import laboratorio.repositorios.ensayo.CompresionCilindrosRepo;
import laboratorio.servicios.interfaces.DigitadorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DigitadorServicioImpl implements DigitadorServicio {

    private final CompresionCilindrosRepo compresionCilindrosRepo;
    private final ObraRepo obraRepo;
    private final CilindroRepo cilindroRepo;

    @Override
    public int agregarMuestra(CompresionCilindrosDTO compresionCilindrosDTO) {
        CompresionCilindros compresionCilindros = new CompresionCilindros();

        compresionCilindros.setObra(obraRepo.findByCR(compresionCilindrosDTO.cr()));
        compresionCilindros.setEnsayo(compresionCilindrosDTO.tipoMuestraCilindro());
        compresionCilindros.setSeccion(compresionCilindrosDTO.seccion());
        compresionCilindros.setNumeroMuestra(compresionCilindrosDTO.numeroMuestra());
        compresionCilindros.setFechaToma(LocalDate.from(LocalDateTime.now()));
        compresionCilindros.setResistencia(compresionCilindrosDTO.resistencia());
        compresionCilindros.setCantMuestras(compresionCilindrosDTO.cantidad());
        compresionCilindros.setDescripcion(compresionCilindrosDTO.descripcion());
        compresionCilindros.setObservaciones(compresionCilindrosDTO.observaciones());

        compresionCilindrosRepo.save(compresionCilindros);

        for(int i=0;i< compresionCilindrosDTO.cantidad();i++){
            Cilindro cilindro = new Cilindro();
            cilindro.setCompresionCilindros(compresionCilindros);
            cilindroRepo.save(cilindro);
        }

        return compresionCilindros.getCodigo();
    }

    @Override
    public void agregarEnsayos(int codigoMuestra) {

    }

    @Override
    public void digitarInformeCompresion(int codigoEnsayo) {

    }

    @Override
    public void eliminarEnsayos(int codigoEnsayo) {

    }

    @Override
    public List<CilindroDTO> MostrarResultados(String cr, LocalDate fecha) throws Exception {

        if (!cr.isBlank()){
                List<Cilindro> compresionCilindros = cilindroRepo.findByCr(cr, fecha);
                if(compresionCilindros.isEmpty()){
                    throw new Exception("no existe el cr "+cr+" o pertenece a otra sucursal");
                }

                List<CilindroDTO> cilindroDTOS = new ArrayList<>();
            for (Cilindro cilindro: compresionCilindros) {
                cilindroDTOS.add( new CilindroDTO(cilindro.getCompresionCilindros().getObra().getCR(),
                        cilindro.getCompresionCilindros().getNumeroMuestra(),
                        cilindro.getCompresionCilindros().getSeccion(),
                        cilindro.getCompresionCilindros().getFechaToma(),
                        cilindro.getCompresionCilindros().getFechaToma().plusDays(cilindro.getEdad()),
                        cilindro.getEdad(),
                        cilindro.getPeso(),
                        cilindro.getCarga(),
                        FormaFalla.DOS));
            }
            return cilindroDTOS;
        }

        List<Cilindro> compresionCilindros = cilindroRepo.findByDate(fecha);
        List<CilindroDTO> cilindroDTOS = new ArrayList<>();
        for (Cilindro cilindro: compresionCilindros) {
            cilindroDTOS.add( new CilindroDTO(cilindro.getCompresionCilindros().getObra().getCR(),
                    cilindro.getCompresionCilindros().getNumeroMuestra(),
                    cilindro.getCompresionCilindros().getSeccion(),
                    cilindro.getCompresionCilindros().getFechaToma(),
                    cilindro.getCompresionCilindros().getFechaToma().plusDays(cilindro.getEdad()),
                    cilindro.getEdad(),
                    cilindro.getPeso(),
                    cilindro.getCarga(),
                    FormaFalla.DOS));
        }
        return cilindroDTOS;
    }
}