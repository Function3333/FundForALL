package com.fundingForAll.www.User;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public class UserRepository {
    private EntityManager em;
    private JPAQueryFactory queryFactory;

    @Autowired
    public UserRepository(EntityManager em) {
        this.em = em;
        queryFactory = new JPAQueryFactory(this.em);

    }

    @Transactional
    public String save(User user) {
        em.persist(user);
        return user.getId();
    }

    @Transactional
    public void delete(User user) {
        em.remove(user);
    }

    public Optional<User> findById(String userId) {
        Optional<User> findUser = Optional.ofNullable(em.find(User.class, userId));
        return findUser;
    }

    public Optional<User> findByEmail(String email) {
        QUser qUser = QUser.user;

        User findUser = queryFactory.select(qUser)
                .from(qUser)
                .where(qUser.email.eq(email))
                .fetchFirst();

        return Optional.ofNullable(findUser);
    }

    public Optional<User> findByAccount(String account) {
        QUser qUser = QUser.user;

        User findUser = queryFactory
                .select(qUser)
                .from(qUser)
                .where(qUser.account.eq(account))
                .fetchFirst();

        return Optional.ofNullable(findUser);
    }
}
