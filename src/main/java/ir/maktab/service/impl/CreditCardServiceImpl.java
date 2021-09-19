package ir.maktab.service.impl;


import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.CreditCart;
import ir.maktab.repository.CreditCardRepository;
import ir.maktab.service.CreditCardService;

public class CreditCardServiceImpl extends BaseEntityServiceImpl<CreditCart, Long, CreditCardRepository> implements CreditCardService {

    public CreditCardServiceImpl(CreditCardRepository repository) {
        super(repository);
    }
}
