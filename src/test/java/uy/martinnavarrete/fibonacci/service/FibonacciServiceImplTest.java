package uy.martinnavarrete.fibonacci.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import uy.martinnavarrete.fibonacci.dto.FibonacciDto;
import uy.martinnavarrete.fibonacci.model.Fibonacci;
import uy.martinnavarrete.fibonacci.repository.FibonacciRepository;

import java.util.Arrays;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class FibonacciServiceImplTest {

    @Mock
    private FibonacciRepository fibonacciRepository;

    @InjectMocks
    private FibonacciServiceImpl fibonacciService;

    @Test
    public void testGetFibonacciWithExistingResult() throws Exception {
        Long n = 5L;
        Long expectedResult = 5L;
        Mockito.when(fibonacciRepository.findResultByN(n)).thenReturn(expectedResult);
        Long result = fibonacciService.getFibonacci(n);
        Mockito.verify(fibonacciRepository).incrementCountByN(n);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testGetFibonacciWithNewResult() throws Exception {
        Long n = 5L;
        Long expectedResult = 5L;
        Mockito.when(fibonacciRepository.findResultByN(n)).thenReturn(null);
        Mockito.when(fibonacciRepository.findResultByN(n-1)).thenReturn(3L);
        Mockito.when(fibonacciRepository.findResultByN(n-2)).thenReturn(2L);
        Mockito.when(fibonacciRepository.save(Mockito.any(Fibonacci.class))).thenReturn(new Fibonacci(n, expectedResult, 0L));
        Long result = fibonacciService.getFibonacci(n);
        Mockito.verify(fibonacciRepository).incrementCountByN(n);
        Mockito.verify(fibonacciRepository).save(Mockito.any(Fibonacci.class));
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testGetFibonacciWithNewResultWhenNIsLessThan2() throws Exception {
        Long n = 1L;
        Long expectedResult = 1L;
        Mockito.when(fibonacciRepository.findResultByN(n)).thenReturn(null);
        Mockito.when(fibonacciRepository.save(Mockito.any(Fibonacci.class))).thenReturn(new Fibonacci(n, expectedResult, 0L));
        Long result = fibonacciService.getFibonacci(n);
        Mockito.verify(fibonacciRepository).incrementCountByN(n);
        Mockito.verify(fibonacciRepository).save(Mockito.any(Fibonacci.class));
        Assert.assertEquals(expectedResult, result);
    }

    @Test(expected = Exception.class)
    public void testGetFibonacciWithError() throws Exception {
        Long n = 5L;
        Mockito.when(fibonacciRepository.findResultByN(n)).thenThrow(new Exception());
        fibonacciService.getFibonacci(n);
    }

    @Test
    public void testGetMostRequestedFibonacciValues() throws Exception {
        List<Fibonacci> mostRequestedNumbers = Arrays.asList(new Fibonacci(1L, 1L, 3L), new Fibonacci(2L, 1L, 2L));
        Mockito.when(fibonacciRepository.findByCountGreaterThanOrderByCountDesc(0L)).thenReturn(mostRequestedNumbers);
        List<FibonacciDto> expectedResult = Arrays.asList(new FibonacciDto(1L, 1L, 3L), new FibonacciDto(2L, 1L, 2L));
        List<FibonacciDto> result = fibonacciService.getMostRequestedFibonacciValues();
        Assert.assertEquals(expectedResult, result);
    }

    @Test(expected = Exception.class)
    public void testGetMostRequestedFibonacciValuesWithError() throws Exception {
        Mockito.when(fibonacciRepository.findByCountGreaterThanOrderByCountDesc(0L)).thenThrow(new Exception());
        fibonacciService.getMostRequestedFibonacciValues();
    }
}
