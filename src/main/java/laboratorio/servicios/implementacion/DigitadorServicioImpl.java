package laboratorio.servicios.implementacion;
import laboratorio.dto.*;
import laboratorio.modelo.ensayo.Cilindro;
import laboratorio.modelo.ensayo.CompresionCilindros;
import laboratorio.modelo.ensayo.Viga;
import laboratorio.repositorios.ObraRepo;
import laboratorio.repositorios.ensayo.CilindroRepo;
import laboratorio.repositorios.ensayo.CompresionCilindrosRepo;
import laboratorio.repositorios.ensayo.VigaRepo;
import laboratorio.servicios.interfaces.DigitadorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DigitadorServicioImpl implements DigitadorServicio {

    private final CompresionCilindrosRepo compresionCilindrosRepo;
    private final ObraRepo obraRepo;
    private final CilindroRepo cilindroRepo;
    private final VigaRepo vigaRepo;

    @Override
    public int agregarMuestra(CompresionCilindrosDTO compresionCilindrosDTO) throws Exception{
        CompresionCilindros compresionCilindros = new CompresionCilindros();
        if (obraRepo.findByCR(compresionCilindrosDTO.cr()) == null) {
            throw new Exception("El CR ingresado no existe");
        }

        compresionCilindros.setObra(obraRepo.findByCR(compresionCilindrosDTO.cr()));
        compresionCilindros.setEnsayo(compresionCilindrosDTO.ensayo());
        compresionCilindros.setSeccion(compresionCilindrosDTO.seccion());
        compresionCilindros.setNumeroMuestra(compresionCilindrosDTO.numeroMuestra());
        compresionCilindros.setFechaToma(LocalDate.from(compresionCilindrosDTO.fechaToma()));
        compresionCilindros.setResistencia(compresionCilindrosDTO.resistencia());
        compresionCilindros.setCantMuestras(compresionCilindrosDTO.cantidad());
        compresionCilindros.setDescripcion(compresionCilindrosDTO.descripcion());
        compresionCilindros.setObservaciones(compresionCilindrosDTO.observaciones());
        compresionCilindrosRepo.save(compresionCilindros);

        System.out.println(compresionCilindrosDTO.ensayo());
        //Si la muestra es cilindro de 6"
        if(compresionCilindros.getEnsayo().getNombreLegible().equals("Compresión de 6")){
            for(int i=0;i< compresionCilindrosDTO.cantidad();i++){
                Cilindro cilindro = new Cilindro();
                cilindro.setCompresionCilindros(compresionCilindros);
                cilindro.setH(0f);
                cilindro.setH1(0f);
                cilindro.setD(0f);
                cilindroRepo.save(cilindro);
            }
        }
        else if(compresionCilindros.getEnsayo().getNombreLegible().equals("Flexión")){
            for(int i=0;i< compresionCilindrosDTO.cantidad();i++){
                Viga viga = new Viga();
                viga.setCompresionCilindros(compresionCilindros);
                vigaRepo.save(viga);
            }
        }
        else {
            throw new Exception("no funciona el tipo de muestra");
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
    public List<CilindroDTO> mostrarOden(OrdenDTO ordenDTO) throws Exception {

        if (!ordenDTO.cr().isBlank()){
            if(cilindroRepo.findByCrOnly(ordenDTO.cr()).isEmpty()){
                throw new Exception("no existe el cr "+ordenDTO.cr()+" o pertenece a otra sucursal");
            }
                List<Cilindro> compresionCilindros = cilindroRepo.findByCr(ordenDTO.cr(), ordenDTO.fecha());
                if(compresionCilindros.isEmpty()){
                    throw new Exception("No hay ordenes pendientes para el CR: "+ ordenDTO.cr());
                }
                List<CilindroDTO> cilindroDTOS = new ArrayList<>();
            for (Cilindro cilindro: compresionCilindros) {
                cilindroDTOS.add( new CilindroDTO(cilindro.getCompresionCilindros().getObra().getCR(),
                        cilindro.getCompresionCilindros().getNumeroMuestra(),
                        cilindro.getCompresionCilindros().getEnsayo().getNombreLegible(),
                        cilindro.getCompresionCilindros().getFechaToma(),
                        cilindro.getCompresionCilindros().getFechaToma().plusDays(cilindro.getEdad()),
                        cilindro.getEdad(),
                        cilindro.getPeso(),
                        cilindro.getCarga(),
                        cilindro.getCompresionCilindros().getObra().getNombre(),
                        cilindro.getCodigo(),
                        cilindro.getD(),
                        cilindro.getH1(),
                        cilindro.getD()));
            }
            return cilindroDTOS;
        }

        List<Cilindro> compresionCilindros = cilindroRepo.findByDate(ordenDTO.fecha());
        List<CilindroDTO> cilindroDTOS = new ArrayList<>();
        for (Cilindro cilindro: compresionCilindros) {
            cilindroDTOS.add( new CilindroDTO(cilindro.getCompresionCilindros().getObra().getCR(),
                    cilindro.getCompresionCilindros().getNumeroMuestra(),
                    cilindro.getCompresionCilindros().getEnsayo().getNombreLegible(),
                    cilindro.getCompresionCilindros().getFechaToma(),
                    cilindro.getCompresionCilindros().getFechaToma().plusDays(cilindro.getEdad()),
                    cilindro.getEdad(),
                    cilindro.getPeso(),
                    cilindro.getCarga(),
                    cilindro.getCompresionCilindros().getObra().getNombre(),cilindro.getCodigo(),
                    cilindro.getD(),
                    cilindro.getH1(),
                    cilindro.getD()));
        }
        return cilindroDTOS;
    }

    @Override
    public List<CilindrosList> listarCilindros() throws Exception {
        List<CompresionCilindros> compresionCilindros = compresionCilindrosRepo.findAll();
        List<CilindrosList> cilindrosListsDTOS = new ArrayList<>();

        for (CompresionCilindros cilindros : compresionCilindros) {
            if (cilindros.getEnsayo().getNombreLegible().equals("Compresión de 6")) {
                cilindrosListsDTOS.add(new CilindrosList(
                        cilindros.getObra().getCR(),
                        cilindros.getNumeroMuestra(),
                        cilindros.getObra().getNombre(),
                        cilindros.getSeccion(),
                        cilindros.getFechaToma(),
                        cilindros.getEnsayo().getNombreLegible(),
                        cilindros.getCodigo()
                ));
            }
        }
            return cilindrosListsDTOS;
        }

    @Override
    public List<CilindrosList> listarVigas() throws Exception {
        List<CompresionCilindros> compresionCilindros = compresionCilindrosRepo.findAll();
        List<CilindrosList> cilindrosListsDTOS = new ArrayList<>();


        for (CompresionCilindros compresionCilindro : compresionCilindros) {
            if (compresionCilindro.getEnsayo().getNombreLegible().equals("Flexión")) {
                cilindrosListsDTOS.add(new CilindrosList(
                        compresionCilindro.getObra().getCR(),
                        compresionCilindro.getNumeroMuestra(),
                        compresionCilindro.getObra().getNombre(),
                        compresionCilindro.getSeccion(),
                        compresionCilindro.getFechaToma(),
                        compresionCilindro.getEnsayo().getNombreLegible(),
                        compresionCilindro.getCodigo()
                ));
            }
        }
        return cilindrosListsDTOS;
    }

    @Override
    public List<CilindroDTO> listarResultados(OrdenDTO ordenDTO) throws Exception {
        if (!ordenDTO.cr().isBlank()){
            List<Cilindro> compresionCilindros = cilindroRepo.buscarResultados(ordenDTO.cr(), ordenDTO.fecha());
            if(compresionCilindros.isEmpty()){
                throw new Exception("no existe el cr "+ordenDTO.cr()+" o pertenece a otra sucursal");
            }
            List<CilindroDTO> cilindroDTOS = new ArrayList<>();
            for (Cilindro cilindro: compresionCilindros) {
                cilindroDTOS.add( new CilindroDTO(cilindro.getCompresionCilindros().getObra().getCR(),
                        cilindro.getCompresionCilindros().getNumeroMuestra(),
                        cilindro.getCompresionCilindros().getEnsayo().getNombreLegible(),
                        cilindro.getCompresionCilindros().getFechaToma(),
                        cilindro.getCompresionCilindros().getFechaToma().plusDays(cilindro.getEdad()),
                        cilindro.getEdad(),
                        cilindro.getPeso(),
                        cilindro.getCarga(),
                        cilindro.getCompresionCilindros().getObra().getNombre(),cilindro.getCodigo(),
                        cilindro.getD(),
                        cilindro.getH1(),
                        cilindro.getD()));
            }
            return cilindroDTOS;
        }

        List<Cilindro> compresionCilindros = cilindroRepo.BuscarHastaLaFecha(ordenDTO.fecha());
        List<CilindroDTO> cilindroDTOS = new ArrayList<>();
        for (Cilindro cilindro: compresionCilindros) {
            cilindroDTOS.add( new CilindroDTO(cilindro.getCompresionCilindros().getObra().getCR(),
                    cilindro.getCompresionCilindros().getNumeroMuestra(),
                    cilindro.getCompresionCilindros().getEnsayo().getNombreLegible(),
                    cilindro.getCompresionCilindros().getFechaToma(),
                    cilindro.getCompresionCilindros().getFechaToma().plusDays(cilindro.getEdad()),
                    cilindro.getEdad(),
                    cilindro.getPeso(),
                    cilindro.getCarga(),
                    cilindro.getCompresionCilindros().getObra().getNombre(),
                    cilindro.getCodigo(),
                    cilindro.getD(),
                    cilindro.getH1(),
                    cilindro.getD()));
        }
        return cilindroDTOS;
    }

    public  String nombreObra(String cr) throws Exception{
        String nombreObra = obraRepo.findByCR(cr).getNombre();
        return nombreObra;
    }
}