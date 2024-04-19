package laboratorio.servicios.interfaces;

import laboratorio.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface DigitadorServicio {
    int agregarMuestra(CompresionCilindrosDTO compresionCilindrosDTO) throws Exception;
    void agregarEnsayos(int codigoMuestra);
    void digitarInformeCompresion(int codigoEnsayo);
    void eliminarEnsayos(int codigoEnsayo);
    List<CilindroDTO> mostrarResultados(OrdenDTO ordenDTO) throws Exception;

    List<CilindrosList> listarCilindros() throws Exception;

}
