package laboratorio.servicios.implementacion;


import laboratorio.dto.SesionDTO;
import laboratorio.dto.TokenDTO;
import laboratorio.seguridad.modelo.UserDetailsImpl;
import laboratorio.seguridad.servicios.JwtService;
import laboratorio.servicios.interfaces.SesionServicio;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SesionServicioImpl implements SesionServicio {

    @Autowired
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public TokenDTO login(SesionDTO sesionDTO) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        sesionDTO.getEmail(),
                        sesionDTO.getPassword())
        );

        UserDetails user = (UserDetailsImpl) authentication.getPrincipal();
        String jwtToken = jwtService.generateToken(user);
        return new TokenDTO(jwtToken);
    }
}
