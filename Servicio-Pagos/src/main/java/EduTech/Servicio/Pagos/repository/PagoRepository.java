package EduTech.Servicio.Pagos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import EduTech.Servicio.Pagos.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Long> {


}
