package laboratorio.servicios.implementacion;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import laboratorio.servicios.interfaces.CloudinaryServicio;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServicioImpl implements CloudinaryServicio {

    private final Cloudinary cloudinary;

    public CloudinaryServicioImpl(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dl2h9wlm3");
        config.put("api_key", "145922535687464");
        config.put("api_secret", "y-ERrv3N_tpHMLvHYlNiRhnBUUs");
        cloudinary = new Cloudinary(config);
    }
    @Override
    public Map subirImagen(File file, String carpeta) throws Exception{
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder",
                String.format("uniquindio/proyecto/%s", carpeta)));

    }
    @Override
    public Map eliminarImagen(String idImagen) throws Exception{
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }
    @Override
    public File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        System.out.println(imagen.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
