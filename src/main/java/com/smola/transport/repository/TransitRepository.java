package com.smola.transport.repository;

import com.smola.transport.model.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransitRepository extends JpaRepository<Transit, Long> {
    List<Transit> findByDateBetween(LocalDate startDate, LocalDate endDate);
}
