package ir.maktab.base.repository.impl;

import ir.maktab.base.domain.BaseEntity;
import ir.maktab.base.repository.BaseEntityRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class BaseEntityRepositoryImpl<E extends BaseEntity<ID>, ID extends Serializable> implements BaseEntityRepository<E, ID> {

    private final EntityManager entityManager;

    public BaseEntityRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public abstract Class<E> getEntityClass();

    @Override
    public E save(E e) {
        if(e.getId() == null) {
            entityManager.persist(e);
            return e;
        } else {
            return entityManager.merge(e);
        }
    }

    @Override
    public E findById(ID id) {
        return entityManager.find(getEntityClass(), id);
    }

    @Override
    public List<E> findAll() {
        return entityManager.createQuery(
                "from " + getEntityClass().getSimpleName(), getEntityClass())
                .getResultList();
    }

    @Override
    public void delete(E e) {
        entityManager.remove(e);
    }

    @Override
    public boolean existsById(ID id) {
        return entityManager.createQuery("from " + getEntityClass().getSimpleName() +
                " count(*) where id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult() > 0;
    }

    @Override
    public Long countAll() {
        return entityManager.createQuery("from " + getEntityClass().getSimpleName() +
                " count(*)", Long.class)
                .getSingleResult();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
