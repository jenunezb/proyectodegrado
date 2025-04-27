package laboratorio.servicios.implementacion;
import laboratorio.dto.*;
import laboratorio.dto.suelos.SuelosDTO;
import laboratorio.modelo.*;
import laboratorio.modelo.ensayo.CompresionCilindros;
import laboratorio.modelo.ensayo.Muestra;
import laboratorio.repositorios.*;
import laboratorio.repositorios.ensayo.CompresionCilindrosRepo;
import laboratorio.repositorios.ensayo.SueloRepo;
import laboratorio.servicios.interfaces.AutenticacionServicio;
import laboratorio.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;


@Service
@RequiredArgsConstructor
public class AutenticacionServicioImpl implements AutenticacionServicio {
    private final CuentaRepo cuentaRepo;
    private final CiudadRepo ciudadRepo;
    private final EmpresaRepo empresaRepo;
    private final CompresionCilindrosRepo compresionCilindrosRepo;
    private final SedeRepo sedeRepo;
    private final SueloRepo sueloRepo;
    private final ObraRepo obraRepo;
    private final JWTUtils jwtUtils;


    @Override
    public TokenDTO login(LoginDTO loginDTO) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Optional<Cuenta> cuentaOptional = cuentaRepo.findByCorreo(loginDTO.email());
        if(cuentaOptional.isEmpty()){
            throw new Exception("No existe el correo ingresado");
        }
        Cuenta cuenta = cuentaOptional.get();
        if( !passwordEncoder.matches(loginDTO.password(), cuenta.getPassword()) ){
            throw new Exception("La contraseña ingresada es incorrecta");
        }
        return new TokenDTO( crearToken(cuenta) );
    }
    private String crearToken(Cuenta cuenta){
        String rol;
        String nombre;
        if( cuenta instanceof Ingeniero){
            rol = "ingeniero";
            nombre = ((Ingeniero) cuenta).getNombre();
        }else if( cuenta instanceof Digitador){
            rol = "digitador";
            nombre = ((Digitador) cuenta).getNombre();
        }else{
            rol = "administrador";
            nombre = "Administrador";
        }
        Map<String, Object> map = new HashMap<>();
        map.put("rol", rol);
        map.put("nombre", nombre);
        map.put("id", cuenta.getCodigo());

        return jwtUtils.generarToken(cuenta.getCorreo(), map);
    }

    public List<CiudadGetDTO> listarCiudades(){
        List<Ciudad> ciudadList = ciudadRepo.findAll();
        List<CiudadGetDTO> ciudadGetDTOS = new ArrayList<>();

        for (int i=0; i< ciudadList.size();i++){
            ciudadGetDTOS.add(new CiudadGetDTO(
                    ciudadList.get(i).getNombre()
            ));
        }
        return ciudadGetDTOS;
    }

    public List<EmpresaDTO> listarEmpresas(){
        List<Empresa> empresaList = empresaRepo.findAll();
        List<EmpresaDTO> empresaDTOS = new ArrayList<>();

        for (int i=0; i< empresaList.size();i++){
            empresaDTOS.add(new EmpresaDTO(
                    empresaList.get(i).getNit(),
                    empresaList.get(i).getNombre(),
                    empresaList.get(i).getDireccion(),
                    empresaList.get(i).getTelefono()

            ));
        }
        return empresaDTOS;
    }

    @Override
    public List<SedeDTO> listarSedes() {
        List<Sede> sedeList = sedeRepo.findAll();
        List<SedeDTO> sedeDTOS = new ArrayList<>();

        for (int i=0; i< sedeList.size();i++){
            sedeDTOS.add(new SedeDTO(
                    sedeList.get(i).getCiudad(),
                    sedeList.get(i).getDireccion(),
                    sedeList.get(i).getTelefono()

            ));
        }
        return sedeDTOS;
    }

    @Override
    public List<ObraDTO> listarObras() {
        List<Obra> obraList = obraRepo.findAll();
        List<ObraDTO> obraGetDTOS = new ArrayList<>();

        for (int i = 0; i < obraList.size(); i++) {

            obraGetDTOS.add(new ObraDTO(
                    obraList.get(i).getDireccion(),
                    obraList.get(i).getNombre(),
                    obraList.get(i).getTelefono(),
                    obraList.get(i).getCiudad().getNombre(),
                    obraList.get(i).getEmpresa().getNombre(),
                    obraList.get(i).getCR()
            ));
        }
        return obraGetDTOS;
    }
    @Override
    public List<CilindrosList> listarCilindros() throws Exception {
        List<CompresionCilindros> compresionCilindros = compresionCilindrosRepo.findAll();
        List<CilindrosList> cilindrosListsDTOS = new ArrayList<>();

        for (int i=0; i< compresionCilindros.size(); i++){
            cilindrosListsDTOS.add(new CilindrosList(
                    compresionCilindros.get(i).getObra().getCR(),
                    compresionCilindros.get(i).getNumeroMuestra(),
                    compresionCilindros.get(i).getObra().getNombre(),
                    compresionCilindros.get(i).getSeccion(),
                    compresionCilindros.get(i).getFechaToma(),
                    compresionCilindros.get(i).getEnsayo().getNombreLegible(),
                    compresionCilindros.get(i).getCodigo()
            ));
        }
        return cilindrosListsDTOS;
    }

    public List<SuelosDTO> listarSuelos(){
        List<Muestra> muestraSuelos = sueloRepo.findAll();
        List<SuelosDTO> listaregistroSuelosDto= new ArrayList<>();
        for (int i=0;i<muestraSuelos.size();i++){
            SuelosDTO registroSuelosDto = new SuelosDTO(
                    muestraSuelos.get(i).getObra().getCR(),
                    muestraSuelos.get(i).getCodigo(),
                    muestraSuelos.get(i).getObra().getNombre(),
                    muestraSuelos.get(i).getFechaRecibido());
            listaregistroSuelosDto.add(registroSuelosDto);
        }
        return listaregistroSuelosDto;
    }


}