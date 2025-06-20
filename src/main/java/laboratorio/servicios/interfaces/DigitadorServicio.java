package laboratorio.servicios.interfaces;

import laboratorio.dto.*;
import laboratorio.dto.vigas.VigasGetDTO;

import java.time.LocalDate;
import java.util.List;

public interface DigitadorServicio {
    int agregarMuestra(CompresionCilindrosDTO compresionCilindrosDTO) throws Exception;
    void agregarEnsayos(int codigoMuestra);
    void digitarInformeCompresion(int codigoEnsayo);
    void eliminarEnsayos(int codigoEnsayo);
    List<CilindroDTO> mostrarOden(OrdenDTO ordenDTO) throws Exception;
    List<CilindrosList> listarCilindros() throws Exception;
    List<CilindroDTO> listarResultados(OrdenDTO ordenDTO) throws Exception;
    List<CilindrosList> listarVigas() throws Exception;
    List<VigasGetDTO> mostrarOdenVigas(OrdenDTO ordenDTO) throws Exception;
    List<VigasGetDTO> listarResultadosVigas(OrdenDTO ordenDTO) throws Exception;
}
