package uy.martinnavarrete.fibonacci.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uy.martinnavarrete.fibonacci.dto.FibonacciDto;
import uy.martinnavarrete.fibonacci.service.FibonacciService;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class FibonacciControllerTest {

    @Mock
    private FibonacciService fibonacciService;

    @InjectMocks
    private FibonacciController fibonacciController;

    @Test
    public void getFibonacci_shouldReturnBadRequest_whenNIsZero() {
        Long n = 0L;
        ResponseEntity<?> responseEntity = fibonacciController.getFibonacci(n);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("n must be equal or greater than zero", responseEntity.getBody());
    }

    @Test
    public void getFibonacci_shouldReturnBadRequest_whenNIsNegative() {
        Long n = -5L;
        ResponseEntity<?> responseEntity = fibonacciController.getFibonacci(n);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("n must be equal or greater than zero", responseEntity.getBody());
    }

    @Test
    public void getFibonacci_shouldReturnFibonacciNumber() throws Exception {
        Long n = 10L;
        Long fibonacciNumber = 55L;
        Mockito.when(fibonacciService.getFibonacci(n)).thenReturn(fibonacciNumber);
        ResponseEntity<?> responseEntity = fibonacciController.getFibonacci(n);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(fibonacciNumber, responseEntity.getBody());
    }

    @Test
    public void testGetFibonacciWithError() throws Exception {
        Long n = 5L;
        Mockito.when(fibonacciService.getFibonacci(n)).thenThrow(new Exception());
        ResponseEntity<?> response = fibonacciController.getFibonacci(n);
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertEquals("Internal Server Error", response.getBody());
    }


    @Test
    public void getMostRequestedFibonacciValues_shouldReturnMostRequestedValues() throws Exception {
        List<FibonacciDto> fibonacciList = Arrays.asList(new FibonacciDto(1L, 1L, 10L), new FibonacciDto(2L, 1L, 8L));
        Mockito.when(fibonacciService.getMostRequestedFibonacciValues()).thenReturn(fibonacciList);
        ResponseEntity<?> responseEntity = fibonacciController.getMostRequestedFibonacciValues();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(fibonacciList, responseEntity.getBody());
    }

    @Test
    public void testGetMostRequestedFibonacciValuesWithError() throws Exception {
        Mockito.when(fibonacciService.getMostRequestedFibonacciValues()).thenThrow(new Exception());
        ResponseEntity<?> response = fibonacciController.getMostRequestedFibonacciValues();
        Assert.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        Assert.assertEquals("Internal Server Error", response.getBody());
    }
}

