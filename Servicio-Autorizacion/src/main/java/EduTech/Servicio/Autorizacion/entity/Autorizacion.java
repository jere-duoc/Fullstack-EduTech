package EduTech.Servicio.Autorizacion.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType.IDENTITY;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Autorizacion {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long idLoggin;
    private String estadoLoggin;
}
