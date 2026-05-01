package edu.icet.repository;

import edu.icet.entity.EmployeeEntity;
import edu.icet.util.DepartmentType;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    List<EmployeeEntity> findEmployeesByDepartmentType(@NotNull(message = "Department must be insert") DepartmentType departmentType);
}
