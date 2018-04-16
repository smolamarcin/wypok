package com.smola.transport.repository;

import com.smola.transport.model.Transit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransitRepository extends JpaRepository<Transit,Long> {
}
