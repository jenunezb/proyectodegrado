package laboratorio.servicios.interfaces;

import laboratorio.dto.CilindroDTO;
import laboratorio.dto.CompresionCilindrosDTO;

import java.time.LocalDate;
import java.util.List;

public interface DigitadorServicio {
    int agregarMuestra(CompresionCilindrosDTO compresionCilindrosDTO);
    void agregarEnsayos(int codigoMuestra);
    void digitarInformeCompresion(int codigoEnsayo);
    void eliminarEnsayos(int codigoEnsayo);
    List<CilindroDTO> MostrarResultados(String cr, LocalDate fecha) throws Exception;

}
