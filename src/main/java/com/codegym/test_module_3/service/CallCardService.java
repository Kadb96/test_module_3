package com.codegym.test_module_3.service;

import com.codegym.test_module_3.dto.CallCardDto;
import com.codegym.test_module_3.model.CallCard;
import com.codegym.test_module_3.repository.CallCardRepository;
import com.codegym.test_module_3.repository.ICallCardRepository;

import java.util.List;

public class CallCardService implements ICallCardService {
    ICallCardRepository callCardRepository = new CallCardRepository();
    @Override
    public List<CallCardDto> showAll() {
        return callCardRepository.showAll();
    }

    @Override
    public List<CallCardDto> search(String keywordBookName, String keywordStudentName) {
        return callCardRepository.search(keywordBookName, keywordStudentName);
    }

    @Override
    public boolean add(CallCard callCard) {
        return callCardRepository.add(callCard);
    }

    @Override
    public boolean returnBook(String callCardId) {
        return callCardRepository.returnBook(callCardId);
    }
}
