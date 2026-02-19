package Repository;


import Entity.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@ApplicationScoped
public class UserRepository {
    @PersistenceContext
    private EntityManager em;

    public User createUser(User user){
        em.persist(user);
        return user;
    }

    public User updateUser(User user){
        em.merge(user);
        return user;
    }

    public void softDelete(User user){
        em.find(User.class, user.getId());
        user.setActive(false);
        em.merge(user);
    }

    public void deleteUser(Long id){
        em.remove(em.find(User.class, id));
    }

    //finders
    public User findUserById(int id){
        User user = em.find(User.class, id);
        return user;
    }

    public User findUserByEmail(String email){
        TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public List<User> findUserByName(String name){
        TypedQuery<User> query = em.createNamedQuery("User.findByNameContaining", User.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<User> findActives(){
        TypedQuery<User> query = em.createNamedQuery("User.findActive", User.class);
        return query.getResultList();
    }

    public List<User> findAll(){
        TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
        return query.getResultList();
    }

    public boolean existsByEmail(String email){
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(u) FROM User WHERE u.email = :email", Long.class);
        query.setParameter("email", email);
        return query.getSingleResult() > 0;
    }
}
