package com.codegym.test_module_3.repository;

import com.codegym.test_module_3.dto.CallCardDto;
import com.codegym.test_module_3.model.CallCard;

import java.util.List;

public interface ICallCardRepository {
    List<CallCardDto> showAll();
    List<CallCardDto> search(String keywordBookName, String keywordStudentName);
    boolean add(CallCard callCard);
    boolean returnBook(String callCardId);
}
