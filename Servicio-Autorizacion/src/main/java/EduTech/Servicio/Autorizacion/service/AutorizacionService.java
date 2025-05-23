package EduTech.Servicio.Autorizacion.service;

import EduTech.Servicio.Autorizacion.entity.Autorizacion;
import EduTech.Servicio.Autorizacion.repository.AutorizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorizacionService {

    @Autowired
    private AutorizacionRepository autorizacionRepository;

    public Autorizacion iniciarSesion(Autorizacion autorizacion) {
        
        autorizacion.setEstadoLoggin("activo"); 
        return autorizacionRepository.save(autorizacion);
    }
    
    public Autorizacion registrarUsuario(Autorizacion autorizacion) {
        
        autorizacion.setEstadoLoggin("inactivo"); 
        return autorizacionRepository.save(autorizacion);
    }
}
