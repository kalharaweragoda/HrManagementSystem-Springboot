package edu.icet.repository;

import edu.icet.entity.PayrollEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollRepository extends JpaRepository<PayrollEntity, Integer> {
}
