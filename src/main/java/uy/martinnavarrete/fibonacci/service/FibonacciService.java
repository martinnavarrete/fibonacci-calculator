package uy.martinnavarrete.fibonacci.service;

import uy.martinnavarrete.fibonacci.dto.FibonacciDto;

import java.util.List;

public interface FibonacciService {

    Long getFibonacci(Long n) throws Exception;

    List<FibonacciDto> getMostRequestedFibonacciValues() throws Exception;
}
