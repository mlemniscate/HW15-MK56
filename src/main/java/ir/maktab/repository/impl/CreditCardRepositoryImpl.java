package ir.maktab.repository.impl;

import ir.maktab.base.repository.impl.BaseEntityRepositoryImpl;
import ir.maktab.domain.CreditCard;
import ir.maktab.repository.CreditCardRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;

public class CreditCardRepositoryImpl extends BaseEntityRepositoryImpl<CreditCard, Long> implements CreditCardRepository {

    public CreditCardRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<CreditCard> getEntityClass() {
        return CreditCard.class;
    }

    @Override
    public boolean existsByCardNumber(String cardNum) {
        return getEntityManager().createQuery("select count(*) from CreditCard c where c.number = :number", Long.class)
                .setParameter("number", cardNum)
                .getResultList().size() > 0;
    }

    @Override
    public CreditCard getByCardNumber(String cardNum) {
        List<CreditCard> creditCards = getEntityManager().createQuery(" from CreditCard c where c.number = :number", CreditCard.class)
                .setParameter("number", cardNum)
                .getResultList();
        if (!Objects.isNull(creditCards)) return creditCards.get(0);
        else return null;
    }
}
