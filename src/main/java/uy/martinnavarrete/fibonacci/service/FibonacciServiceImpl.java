package uy.martinnavarrete.fibonacci.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.martinnavarrete.fibonacci.dto.FibonacciDto;
import uy.martinnavarrete.fibonacci.mapper.FibonacciMapper;
import uy.martinnavarrete.fibonacci.model.Fibonacci;
import uy.martinnavarrete.fibonacci.repository.FibonacciRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FibonacciServiceImpl implements FibonacciService {

    @Autowired
    private FibonacciRepository fibonacciRepository;

    @Override
    public Long getFibonacci(Long n) throws Exception {
        Long fibonacci = getOrCalculateFibonacci(n);
        fibonacciRepository.incrementCountByN(n);
        return fibonacci;
    }

    @Override
    public List<FibonacciDto> getMostRequestedFibonacciValues() throws Exception {
        List<Fibonacci> mostRequestedNumbers = fibonacciRepository.findByCountGreaterThanOrderByCountDesc(0L);
        return FibonacciMapper.mapModelListToDtoList(mostRequestedNumbers);
    }

    private Long getOrCalculateFibonacci(Long n){
        Long fibonacci = fibonacciRepository.findResultByN(n);
        if (fibonacci == null) {
            fibonacci = calculateFibonacci(n);
            fibonacciRepository.save(new Fibonacci(n, fibonacci, 0L));
        }
        return fibonacci;
    }
    private Long calculateFibonacci(Long n) {
        if (n <= 2) {
            return 1L;
        }
        return getOrCalculateFibonacci(n - 1) + getOrCalculateFibonacci(n - 2);
    }

}
