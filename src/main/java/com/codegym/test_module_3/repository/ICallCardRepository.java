package com.codegym.test_module_3.repository;

import com.codegym.test_module_3.model.CallCard;

import java.util.List;

public interface ICallCardRepository {
    List<CallCard> showAll();
    boolean add(CallCard callCard);
}
