package laboratorio.servicios.interfaces;


import laboratorio.dto.UsuarioDTO;
import laboratorio.dto.UsuarioGetDTO;
import laboratorio.modelo.Usuario;

import java.util.List;

public interface UsuarioServicio {

    int crearUsuario(UsuarioDTO usuarioDTO)  throws Exception;

    UsuarioGetDTO actualizarUsuario(int codigoUsuario, UsuarioDTO usuarioDTO) throws Exception;

    int eliminarUsuario(int codigoUsuario) throws Exception;

    UsuarioGetDTO obtenerUsuario(int codigoUsuario) throws Exception;

    Usuario obtener(int codigoUsuario) throws Exception;

     List<UsuarioGetDTO> listarTodos();

    int cedulaUsuario(String cedulaUsuario)throws Exception;

}
