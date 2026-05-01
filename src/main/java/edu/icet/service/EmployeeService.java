package edu.icet.service;

import edu.icet.dto.EmployeeDto;
import edu.icet.dto.LeaveDto;
import edu.icet.dto.LeaveSendDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    EmployeeDto getEmployeeById(Integer id);

    EmployeeDto addEmployee(EmployeeDto employeeDto);

    EmployeeDto updateEmployee(Integer id, EmployeeDto employeeDto);

    Boolean deleteEmployee(Integer id);
}
