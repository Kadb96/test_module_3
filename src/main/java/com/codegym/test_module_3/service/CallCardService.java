package com.codegym.test_module_3.service;

import com.codegym.test_module_3.model.CallCard;
import com.codegym.test_module_3.repository.CallCardRepository;
import com.codegym.test_module_3.repository.ICallCardRepository;

import java.util.List;

public class CallCardService implements ICallCardService {
    ICallCardRepository callCardRepository = new CallCardRepository();
    @Override
    public List<CallCard> showAll() {
        return callCardRepository.showAll();
    }

    @Override
    public boolean add(CallCard callCard) {
        return callCardRepository.add(callCard);
    }
}
