package ir.maktab.service;

import ir.maktab.base.service.BaseEntityService;
import ir.maktab.domain.CreditCard;

public interface CreditCardService extends BaseEntityService<CreditCard, Long> {
    boolean existsByCardNumber(String destinationCartNum);

    CreditCard getByCardNumber(String cartNum);
}
