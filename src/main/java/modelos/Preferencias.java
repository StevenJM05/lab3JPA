/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author steve_y
 */
@Entity
@Table(name = "preferencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Preferencias.findAll", query = "SELECT p FROM Preferencias p"),
    @NamedQuery(name = "Preferencias.findById", query = "SELECT p FROM Preferencias p WHERE p.id = :id"),
    @NamedQuery(name = "Preferencias.findByFechaSeleccion", query = "SELECT p FROM Preferencias p WHERE p.fechaSeleccion = :fechaSeleccion")})
public class Preferencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha_seleccion")
    @Temporal(TemporalType.DATE)
    private Date fechaSeleccion;
    @JoinColumn(name = "id_libro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Libros idLibro;
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Usuarios usuarioId;

    public Preferencias() {
    }

    public Preferencias(Integer id) {
        this.id = id;
    }

    public Preferencias(Integer id, Date fechaSeleccion) {
        this.id = id;
        this.fechaSeleccion = fechaSeleccion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaSeleccion() {
        return fechaSeleccion;
    }

    public void setFechaSeleccion(Date fechaSeleccion) {
        this.fechaSeleccion = fechaSeleccion;
    }

    public Libros getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Libros idLibro) {
        this.idLibro = idLibro;
    }

    public Usuarios getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuarios usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Preferencias)) {
            return false;
        }
        Preferencias other = (Preferencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Preferencias[ id=" + id + " ]";
    }
    
}
