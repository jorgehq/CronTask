
package Domain.Colaborador.MedioDeContacto;

import jakarta.persistence.*;

@Entity
@Table(name = "MediosContactos")
public  class Mediodecontacto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String contacto;
    @Enumerated(EnumType.STRING)
    InstantMessageApp proveedor;
    public Mediodecontacto() {

    }
    public Mediodecontacto(String contacto, InstantMessageApp proveedor) {
        this.contacto = contacto;
        this.proveedor = proveedor;
    }

    public long getId() {
        return id;
    }

    public void enviarMensaje(String cuerpo){
        new ControladorMensajeria().sendMessage(proveedor,contacto,cuerpo);
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }
}

