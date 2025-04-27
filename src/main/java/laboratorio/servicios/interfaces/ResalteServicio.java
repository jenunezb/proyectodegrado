package laboratorio.servicios.interfaces;

import laboratorio.dto.suelos.ResaltesDTO;
import laboratorio.modelo.ensayo.Aceros.Resalte;
import laboratorio.repositorios.ensayo.ResalteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ResalteServicio {
    int crearResalte(ResaltesDTO resaltesDTO) throws  Exception;
    public List<ResaltesDTO> listarResaltes();
    public ResaltesDTO obtenerResalte(int codigoResalte) throws Exception;
    int actualizarResalte(int codigoResalte, ResaltesDTO resaltesDTO) throws Exception;
}

