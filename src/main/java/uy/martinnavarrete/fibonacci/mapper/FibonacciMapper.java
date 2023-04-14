package uy.martinnavarrete.fibonacci.mapper;

import uy.martinnavarrete.fibonacci.dto.FibonacciDto;
import uy.martinnavarrete.fibonacci.model.Fibonacci;

import java.util.List;
import java.util.stream.Collectors;

public class FibonacciMapper {

    public static List<FibonacciDto> mapModelListToDtoList(List<Fibonacci> model) {
        return model.stream().map(m -> new FibonacciDto(m.getId(), m.getResult(), m.getCount())).collect(Collectors.toList());
    }
}
