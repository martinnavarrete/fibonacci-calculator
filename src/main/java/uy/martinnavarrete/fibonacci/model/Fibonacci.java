package uy.martinnavarrete.fibonacci.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "fibonacci")
public class Fibonacci {

    @Id
    private Long id;

    private Long result;

    private Long count;

}
