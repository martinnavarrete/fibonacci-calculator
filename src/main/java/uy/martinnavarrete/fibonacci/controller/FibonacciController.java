package uy.martinnavarrete.fibonacci.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.martinnavarrete.fibonacci.dto.FibonacciDto;
import uy.martinnavarrete.fibonacci.service.FibonacciService;

import java.util.List;

@RestController
@RequestMapping("/fibonacci")
public class FibonacciController {

    private static final Logger logger = LoggerFactory.getLogger(FibonacciController.class);
    @Autowired
    private FibonacciService fibonacciService;

    @GetMapping("/{n}")
    @Operation(summary = "Get Fibonacci value for n")
    @ApiResponse(responseCode = "200", description = "Fibonacci value for n",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = Long.class))})
    public ResponseEntity<?> getFibonacci(@PathVariable @Parameter(description = "Fibonacci number n",
            example = "5", required = true)Long n) {
        if (n == null || n < 1) {
            return ResponseEntity.badRequest().body("n must be equal or greater than zero");
        }
        try {
            Long fibonacci = fibonacciService.getFibonacci(n);
            return ResponseEntity.ok(fibonacci);
        } catch (Exception e) {
            logger.error("[getFibonacci] Fibonacci n = " + n, e.getStackTrace());
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }

    @GetMapping()
    @Operation(summary = "Get most requested Fibonacci values")
    @ApiResponse(responseCode = "200", description = "List of most requested Fibonacci values",
            content = {@Content(mediaType = "application/json",
                    array = @ArraySchema( schema = @Schema(implementation = FibonacciDto.class)))})
    public ResponseEntity<?> getMostRequestedFibonacciValues() {
        try {
            List<FibonacciDto> fibonacci = fibonacciService.getMostRequestedFibonacciValues();
            return ResponseEntity.ok(fibonacci);
        } catch (Exception e) {
            logger.error("[getMostRequestedFibonacciValues]", e.getStackTrace());
            return ResponseEntity.internalServerError().body("Internal Server Error");
        }
    }

}
