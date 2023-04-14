package uy.martinnavarrete.fibonacci.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FibonacciDto {

    private Long n;

    private Long fn;

    private Long count;
}
