package EduTech.Servicio.Usuario.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
   @Column( unique = true, 
            length = 12,
            nullable = false)

    private String run;
    @Column(nullable = false)

    private String nombre;
    @Column(nullable = false)

    private String password;
    @Column(nullable = false)

    private Integer edad;
    public Integer getId() {
        return id;
    }


    public Usuario(){

    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRun() {
        return run;
    }
    public void setRun(String run) {
        this.run = run;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public Integer getEdad() {
        return edad;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((run == null) ? 0 : run.hashCode());
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((edad == null) ? 0 : edad.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (run == null) {
            if (other.run != null)
                return false;
        } else if (!run.equals(other.run))
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (password == null){
            if (other.password != null)
            return false;
        } else if (!password.equals(other.password))
            return false;
        if (edad == null) {
            if (other.edad != null)
                return false;
        } else if (!edad.equals(other.edad))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Usuario [id=" + id + ", run=" + run + ", nombre=" + nombre + ", edad=" + edad + "]";
    }
    

    public Usuario(Integer id, String run, String nombre, String password, Integer edad) {
        this.id = id;
        this.run = run;
        this.nombre = nombre;
        this.password = password;
        this.edad = edad;
    }

    
    

}
