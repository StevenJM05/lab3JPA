/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import controladores.exceptions.IllegalOrphanException;
import controladores.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelos.Preferencias;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelos.Libros;

/**
 *
 * @author steve_y
 */
public class LibrosJpaController implements Serializable {

    public LibrosJpaController() {
        this.emf=Persistence.createEntityManagerFactory("PU");
    }
    

    public LibrosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Libros libros) {
        if (libros.getPreferenciasCollection() == null) {
            libros.setPreferenciasCollection(new ArrayList<Preferencias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Preferencias> attachedPreferenciasCollection = new ArrayList<Preferencias>();
            for (Preferencias preferenciasCollectionPreferenciasToAttach : libros.getPreferenciasCollection()) {
                preferenciasCollectionPreferenciasToAttach = em.getReference(preferenciasCollectionPreferenciasToAttach.getClass(), preferenciasCollectionPreferenciasToAttach.getId());
                attachedPreferenciasCollection.add(preferenciasCollectionPreferenciasToAttach);
            }
            libros.setPreferenciasCollection(attachedPreferenciasCollection);
            em.persist(libros);
            for (Preferencias preferenciasCollectionPreferencias : libros.getPreferenciasCollection()) {
                Libros oldIdLibroOfPreferenciasCollectionPreferencias = preferenciasCollectionPreferencias.getIdLibro();
                preferenciasCollectionPreferencias.setIdLibro(libros);
                preferenciasCollectionPreferencias = em.merge(preferenciasCollectionPreferencias);
                if (oldIdLibroOfPreferenciasCollectionPreferencias != null) {
                    oldIdLibroOfPreferenciasCollectionPreferencias.getPreferenciasCollection().remove(preferenciasCollectionPreferencias);
                    oldIdLibroOfPreferenciasCollectionPreferencias = em.merge(oldIdLibroOfPreferenciasCollectionPreferencias);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Libros libros) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libros persistentLibros = em.find(Libros.class, libros.getId());
            Collection<Preferencias> preferenciasCollectionOld = persistentLibros.getPreferenciasCollection();
            Collection<Preferencias> preferenciasCollectionNew = libros.getPreferenciasCollection();
            List<String> illegalOrphanMessages = null;
            for (Preferencias preferenciasCollectionOldPreferencias : preferenciasCollectionOld) {
                if (!preferenciasCollectionNew.contains(preferenciasCollectionOldPreferencias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Preferencias " + preferenciasCollectionOldPreferencias + " since its idLibro field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Preferencias> attachedPreferenciasCollectionNew = new ArrayList<Preferencias>();
            for (Preferencias preferenciasCollectionNewPreferenciasToAttach : preferenciasCollectionNew) {
                preferenciasCollectionNewPreferenciasToAttach = em.getReference(preferenciasCollectionNewPreferenciasToAttach.getClass(), preferenciasCollectionNewPreferenciasToAttach.getId());
                attachedPreferenciasCollectionNew.add(preferenciasCollectionNewPreferenciasToAttach);
            }
            preferenciasCollectionNew = attachedPreferenciasCollectionNew;
            libros.setPreferenciasCollection(preferenciasCollectionNew);
            libros = em.merge(libros);
            for (Preferencias preferenciasCollectionNewPreferencias : preferenciasCollectionNew) {
                if (!preferenciasCollectionOld.contains(preferenciasCollectionNewPreferencias)) {
                    Libros oldIdLibroOfPreferenciasCollectionNewPreferencias = preferenciasCollectionNewPreferencias.getIdLibro();
                    preferenciasCollectionNewPreferencias.setIdLibro(libros);
                    preferenciasCollectionNewPreferencias = em.merge(preferenciasCollectionNewPreferencias);
                    if (oldIdLibroOfPreferenciasCollectionNewPreferencias != null && !oldIdLibroOfPreferenciasCollectionNewPreferencias.equals(libros)) {
                        oldIdLibroOfPreferenciasCollectionNewPreferencias.getPreferenciasCollection().remove(preferenciasCollectionNewPreferencias);
                        oldIdLibroOfPreferenciasCollectionNewPreferencias = em.merge(oldIdLibroOfPreferenciasCollectionNewPreferencias);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = libros.getId();
                if (findLibros(id) == null) {
                    throw new NonexistentEntityException("The libros with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Libros libros;
            try {
                libros = em.getReference(Libros.class, id);
                libros.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The libros with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Preferencias> preferenciasCollectionOrphanCheck = libros.getPreferenciasCollection();
            for (Preferencias preferenciasCollectionOrphanCheckPreferencias : preferenciasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Libros (" + libros + ") cannot be destroyed since the Preferencias " + preferenciasCollectionOrphanCheckPreferencias + " in its preferenciasCollection field has a non-nullable idLibro field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(libros);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Libros> findLibrosEntities() {
        return findLibrosEntities(true, -1, -1);
    }

    public List<Libros> findLibrosEntities(int maxResults, int firstResult) {
        return findLibrosEntities(false, maxResults, firstResult);
    }

    private List<Libros> findLibrosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Libros.class));
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

    public Libros findLibros(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Libros.class, id);
        } finally {
            em.close();
        }
    }

    public int getLibrosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Libros> rt = cq.from(Libros.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
