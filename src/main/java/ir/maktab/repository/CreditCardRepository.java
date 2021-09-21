package ir.maktab.repository;

import ir.maktab.base.repository.BaseEntityRepository;
import ir.maktab.domain.CreditCard;

public interface CreditCardRepository extends BaseEntityRepository<CreditCard, Long> {
    boolean existsByCardNumber(String destinationCartNum);

    CreditCard getByCardNumber(String cartNum);
}
