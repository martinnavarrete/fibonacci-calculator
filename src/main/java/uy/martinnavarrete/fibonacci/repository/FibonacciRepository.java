package uy.martinnavarrete.fibonacci.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uy.martinnavarrete.fibonacci.model.Fibonacci;

import java.util.List;

@Repository
@Transactional
public interface FibonacciRepository extends JpaRepository<Fibonacci, Long> {

    @Query("SELECT f.result FROM Fibonacci f WHERE f.id = :n")
    Long findResultByN(@Param("n") Long n);

    @Modifying
    @Query("UPDATE Fibonacci f SET f.count = f.count + 1 WHERE f.id = :n")
    void incrementCountByN(@Param("n") Long n);

    List<Fibonacci> findByCountGreaterThanOrderByCountDesc(Long count);
}
