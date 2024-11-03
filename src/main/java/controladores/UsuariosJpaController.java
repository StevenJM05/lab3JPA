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
import javax.persistence.TypedQuery;
import modelos.Usuarios;

/**
 *
 * @author steve_y
 */
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController() {
        this.emf=Persistence.createEntityManagerFactory("PU");

    }
    
    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) {
        if (usuarios.getPreferenciasCollection() == null) {
            usuarios.setPreferenciasCollection(new ArrayList<Preferencias>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Preferencias> attachedPreferenciasCollection = new ArrayList<Preferencias>();
            for (Preferencias preferenciasCollectionPreferenciasToAttach : usuarios.getPreferenciasCollection()) {
                preferenciasCollectionPreferenciasToAttach = em.getReference(preferenciasCollectionPreferenciasToAttach.getClass(), preferenciasCollectionPreferenciasToAttach.getId());
                attachedPreferenciasCollection.add(preferenciasCollectionPreferenciasToAttach);
            }
            usuarios.setPreferenciasCollection(attachedPreferenciasCollection);
            em.persist(usuarios);
            for (Preferencias preferenciasCollectionPreferencias : usuarios.getPreferenciasCollection()) {
                Usuarios oldUsuarioIdOfPreferenciasCollectionPreferencias = preferenciasCollectionPreferencias.getUsuarioId();
                preferenciasCollectionPreferencias.setUsuarioId(usuarios);
                preferenciasCollectionPreferencias = em.merge(preferenciasCollectionPreferencias);
                if (oldUsuarioIdOfPreferenciasCollectionPreferencias != null) {
                    oldUsuarioIdOfPreferenciasCollectionPreferencias.getPreferenciasCollection().remove(preferenciasCollectionPreferencias);
                    oldUsuarioIdOfPreferenciasCollectionPreferencias = em.merge(oldUsuarioIdOfPreferenciasCollectionPreferencias);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getId());
            Collection<Preferencias> preferenciasCollectionOld = persistentUsuarios.getPreferenciasCollection();
            Collection<Preferencias> preferenciasCollectionNew = usuarios.getPreferenciasCollection();
            List<String> illegalOrphanMessages = null;
            for (Preferencias preferenciasCollectionOldPreferencias : preferenciasCollectionOld) {
                if (!preferenciasCollectionNew.contains(preferenciasCollectionOldPreferencias)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Preferencias " + preferenciasCollectionOldPreferencias + " since its usuarioId field is not nullable.");
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
            usuarios.setPreferenciasCollection(preferenciasCollectionNew);
            usuarios = em.merge(usuarios);
            for (Preferencias preferenciasCollectionNewPreferencias : preferenciasCollectionNew) {
                if (!preferenciasCollectionOld.contains(preferenciasCollectionNewPreferencias)) {
                    Usuarios oldUsuarioIdOfPreferenciasCollectionNewPreferencias = preferenciasCollectionNewPreferencias.getUsuarioId();
                    preferenciasCollectionNewPreferencias.setUsuarioId(usuarios);
                    preferenciasCollectionNewPreferencias = em.merge(preferenciasCollectionNewPreferencias);
                    if (oldUsuarioIdOfPreferenciasCollectionNewPreferencias != null && !oldUsuarioIdOfPreferenciasCollectionNewPreferencias.equals(usuarios)) {
                        oldUsuarioIdOfPreferenciasCollectionNewPreferencias.getPreferenciasCollection().remove(preferenciasCollectionNewPreferencias);
                        oldUsuarioIdOfPreferenciasCollectionNewPreferencias = em.merge(oldUsuarioIdOfPreferenciasCollectionNewPreferencias);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getId();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    public Usuarios findUsuarioByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Usuarios> query = em.createNamedQuery("Usuarios.findByEmail", Usuarios.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (Exception e) {
            return null; 
        } finally {
            em.close();
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Preferencias> preferenciasCollectionOrphanCheck = usuarios.getPreferenciasCollection();
            for (Preferencias preferenciasCollectionOrphanCheckPreferencias : preferenciasCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuarios (" + usuarios + ") cannot be destroyed since the Preferencias " + preferenciasCollectionOrphanCheckPreferencias + " in its preferenciasCollection field has a non-nullable usuarioId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
