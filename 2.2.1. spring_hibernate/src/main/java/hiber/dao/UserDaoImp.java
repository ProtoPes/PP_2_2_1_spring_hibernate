package hiber.dao;

import hiber.model.User;
import hiber.model.Car;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   public User getUserByCar(Car car) {
      Session session = sessionFactory.openSession();
      session.beginTransaction();
      Query query = session.createQuery("FROM User where car = :car");
      query.setParameter("car", car);
      User user =  (User) query.getSingleResult();
      return user;
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
