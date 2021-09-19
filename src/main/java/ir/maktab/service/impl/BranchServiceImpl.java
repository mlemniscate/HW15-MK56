package ir.maktab.service.impl;

import ir.maktab.base.service.impl.BaseEntityServiceImpl;
import ir.maktab.domain.Branch;
import ir.maktab.repository.BranchRepository;
import ir.maktab.service.BranchService;

public class BranchServiceImpl extends BaseEntityServiceImpl<Branch, Long, BranchRepository> implements BranchService {

    public BranchServiceImpl(BranchRepository repository) {
        super(repository);
    }
}
