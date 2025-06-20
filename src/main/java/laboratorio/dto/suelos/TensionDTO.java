package laboratorio.dto.suelos;

import java.math.BigDecimal;

public record TensionDTO(
        int codigo,
        BigDecimal longProbeta,
        BigDecimal pesoProbeta,
        BigDecimal diamInicial,
        BigDecimal diamFinal,
        BigDecimal areaInicial,
        BigDecimal areaFinal,
        BigDecimal longCalibradaInicial,
        BigDecimal longCalibradaFinal,
        BigDecimal cargaMax,
        BigDecimal cargaFluencia
) {}
