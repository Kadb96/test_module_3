package com.codegym.test_module_3.service;

import com.codegym.test_module_3.model.CallCard;

import java.util.List;

public interface ICallCardService {
    List<CallCard> showAll();
    boolean add(CallCard callCard);
}
