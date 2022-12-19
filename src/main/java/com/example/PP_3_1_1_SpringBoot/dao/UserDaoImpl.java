package com.example.PP_3_1_1_SpringBoot.dao;

import com.example.PP_3_1_1_SpringBoot.models.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(findById(id));
    }


    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        return entityManager.createQuery("SELECT u FROM User u JOIN FETCH u.roles WHERE u.email = :email", User.class)
                .setParameter("email", email)
                .getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

}