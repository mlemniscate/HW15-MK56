package ir.maktab.service.impl;


import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.CreditCard;
import ir.maktab.repository.CreditCardRepository;
import ir.maktab.service.CreditCardService;

public class CreditCardServiceImpl extends BaseEntityServiceImpl<CreditCard, Long, CreditCardRepository> implements CreditCardService {

    public CreditCardServiceImpl(CreditCardRepository repository) {
        super(repository);
    }

    @Override
    public boolean existsByCardNumber(String cardNum) {
        return repository.existsByCardNumber(cardNum);
    }

    @Override
    public CreditCard getByCardNumber(String cartNum) {
        return repository.getByCardNumber(cartNum);
    }
}
