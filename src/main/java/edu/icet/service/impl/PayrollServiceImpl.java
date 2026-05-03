package edu.icet.service.impl;

import edu.icet.service.EmployeeService;
import edu.icet.service.PayrollService;
import org.modelmapper.ModelMapper;

public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;
}
