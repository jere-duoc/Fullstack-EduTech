package EduTech.Servicio.Autorizacion.repository;

package EduTech.ServicioAutorizacion.repository;

import EduTech.ServicioAutorizacion.entity.Autorizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizacionRepository extends JpaRepository<Autorizacion, Long> {
    
}
