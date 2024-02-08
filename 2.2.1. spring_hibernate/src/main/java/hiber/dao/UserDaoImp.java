package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
@Repository
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findUserByCar(String model, int series) {
        String hql = "FROM User WHERE car.model = :model AND car.series = :series";
        Query query = entityManager.createQuery(hql, User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return (User) query.getSingleResult();
    }
}