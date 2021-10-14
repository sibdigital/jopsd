package ru.sibdigital.jopsd.service;

import ru.sibdigital.jopsd.dto.ContractDto;
import ru.sibdigital.jopsd.model.opsd.*;

import java.util.List;

public interface ContractService {
    Contract saveContract(ContractDto contractDto);
}
