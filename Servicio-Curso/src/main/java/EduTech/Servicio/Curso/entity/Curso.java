package EduTech.Servicio.Curso.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Curso{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCurso;
    private String nombreCurso;

    public Curso() {

}

public Curso(Long idCurso, String nombreCurso) {
    this.idCurso=idCurso;
    this.nombreCurso=nombreCurso;
}

public Long getIdCurso() {
    return idCurso;
}

public void setIdCurso(Long idCurso) {
    this.idCurso = idCurso;
}

public String getNombreCurso() {
    return nombreCurso;
}

public void setNombreCurso(String nombreCurso) {
    this.nombreCurso = nombreCurso;
}

@Override
public String toString() {
    return "Curso [idCurso=" + idCurso + ", nombreCurso=" + nombreCurso + ", getIdCurso()=" + getIdCurso()
            + ", getNombreCurso()=" + getNombreCurso() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
            + ", toString()=" + super.toString() + "]";
}
}


