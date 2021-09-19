package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.CreditCart;
import ir.maktab.repository.CreditCardRepository;

import javax.persistence.EntityManager;

public class CreditCardRepositoryImpl extends BaseEntityRepositoryImpl<CreditCart, Long> implements CreditCardRepository {

    public CreditCardRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CreditCart> getEntityClass() {
        return CreditCart.class;
    }
}
