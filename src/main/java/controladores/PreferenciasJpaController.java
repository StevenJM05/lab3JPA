/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Libros;
import modelos.Preferencias;
import modelos.Usuarios;

/**
 *
 * @author steve_y
 */
public class PreferenciasJpaController implements Serializable {

    public PreferenciasJpaController() {
        this.emf = Persistence.createEntityManagerFactory("PU");
    }

    public PreferenciasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Preferencias preferencias) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libros idLibro = preferencias.getIdLibro();
            if (idLibro != null) {
                idLibro = em.getReference(idLibro.getClass(), idLibro.getId());
                preferencias.setIdLibro(idLibro);
            }
            Usuarios usuarioId = preferencias.getUsuarioId();
            if (usuarioId != null) {
                usuarioId = em.getReference(usuarioId.getClass(), usuarioId.getId());
                preferencias.setUsuarioId(usuarioId);
            }
            em.persist(preferencias);
            if (idLibro != null) {
                idLibro.getPreferenciasCollection().add(preferencias);
                idLibro = em.merge(idLibro);
            }
            if (usuarioId != null) {
                usuarioId.getPreferenciasCollection().add(preferencias);
                usuarioId = em.merge(usuarioId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Preferencias preferencias) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preferencias persistentPreferencias = em.find(Preferencias.class, preferencias.getId());
            Libros idLibroOld = persistentPreferencias.getIdLibro();
            Libros idLibroNew = preferencias.getIdLibro();
            Usuarios usuarioIdOld = persistentPreferencias.getUsuarioId();
            Usuarios usuarioIdNew = preferencias.getUsuarioId();
            if (idLibroNew != null) {
                idLibroNew = em.getReference(idLibroNew.getClass(), idLibroNew.getId());
                preferencias.setIdLibro(idLibroNew);
            }
            if (usuarioIdNew != null) {
                usuarioIdNew = em.getReference(usuarioIdNew.getClass(), usuarioIdNew.getId());
                preferencias.setUsuarioId(usuarioIdNew);
            }
            preferencias = em.merge(preferencias);
            if (idLibroOld != null && !idLibroOld.equals(idLibroNew)) {
                idLibroOld.getPreferenciasCollection().remove(preferencias);
                idLibroOld = em.merge(idLibroOld);
            }
            if (idLibroNew != null && !idLibroNew.equals(idLibroOld)) {
                idLibroNew.getPreferenciasCollection().add(preferencias);
                idLibroNew = em.merge(idLibroNew);
            }
            if (usuarioIdOld != null && !usuarioIdOld.equals(usuarioIdNew)) {
                usuarioIdOld.getPreferenciasCollection().remove(preferencias);
                usuarioIdOld = em.merge(usuarioIdOld);
            }
            if (usuarioIdNew != null && !usuarioIdNew.equals(usuarioIdOld)) {
                usuarioIdNew.getPreferenciasCollection().add(preferencias);
                usuarioIdNew = em.merge(usuarioIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = preferencias.getId();
                if (findPreferencias(id) == null) {
                    throw new NonexistentEntityException("The preferencias with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Preferencias preferencias;
            try {
                preferencias = em.getReference(Preferencias.class, id);
                preferencias.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The preferencias with id " + id + " no longer exists.", enfe);
            }
            Libros idLibro = preferencias.getIdLibro();
            if (idLibro != null) {
                idLibro.getPreferenciasCollection().remove(preferencias);
                idLibro = em.merge(idLibro);
            }
            Usuarios usuarioId = preferencias.getUsuarioId();
            if (usuarioId != null) {
                usuarioId.getPreferenciasCollection().remove(preferencias);
                usuarioId = em.merge(usuarioId);
            }
            em.remove(preferencias);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Preferencias> findPreferenciasEntities() {
        return findPreferenciasEntities(true, -1, -1);
    }

    public List<Preferencias> findPreferenciasEntities(int maxResults, int firstResult) {
        return findPreferenciasEntities(false, maxResults, firstResult);
    }

    private List<Preferencias> findPreferenciasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Preferencias.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Preferencias findPreferencias(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Preferencias.class, id);
        } finally {
            em.close();
        }
    }

    public int getPreferenciasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Preferencias> rt = cq.from(Preferencias.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Preferencias> findPreferenciasByUsuarioId(Integer usuarioId) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery("SELECT p FROM Preferencias p WHERE p.usuarioId.id = :usuarioId", Preferencias.class);
            query.setParameter("usuarioId", usuarioId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> findMostPopularBooks(int limit) {
        EntityManager em = getEntityManager();
        try {
            // Consulta que cuenta las preferencias por cada libro y las ordena de manera descendente
            Query query = em.createQuery(
                    "SELECT p.idLibro, COUNT(p) FROM Preferencias p "
                    + "GROUP BY p.idLibro "
                    + "ORDER BY COUNT(p) DESC", Object[].class);

            // Establece un límite para la cantidad de resultados que deseas recuperar
            query.setMaxResults(limit);

            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Object[]> findPreferencesOverTimeForAllBooks() {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery(
                    "SELECT p.idLibro.id, p.idLibro.titulo, p.fechaSeleccion, COUNT(p) \n"
                    + "FROM Preferencias p \n"
                    + "GROUP BY p.idLibro.id, p.idLibro.titulo, p.fechaSeleccion \n"
                    + "ORDER BY p.idLibro.id, p.fechaSeleccion ASC", Object[].class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

}
