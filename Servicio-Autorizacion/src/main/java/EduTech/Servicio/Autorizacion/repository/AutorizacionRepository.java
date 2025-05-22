package EduTech.Servicio.Autorizacion.repository;

import EduTech.Servicio.Autorizacion.entity.Autorizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorizacionRepository extends JpaRepository<Autorizacion, Long> {

}
