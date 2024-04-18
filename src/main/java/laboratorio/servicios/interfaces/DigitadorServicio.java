package laboratorio.servicios.interfaces;

import laboratorio.dto.CilindroDTO;
import laboratorio.dto.CilindrosList;
import laboratorio.dto.CompresionCilindrosDTO;
import laboratorio.dto.CompresionCilindrosGetDTO;

import java.time.LocalDate;
import java.util.List;

public interface DigitadorServicio {
    int agregarMuestra(CompresionCilindrosDTO compresionCilindrosDTO) throws Exception;
    void agregarEnsayos(int codigoMuestra);
    void digitarInformeCompresion(int codigoEnsayo);
    void eliminarEnsayos(int codigoEnsayo);
    List<CilindroDTO> mostrarResultados(String cr, LocalDate fecha) throws Exception;

    List<CilindrosList> listarCilindros() throws Exception;

}
