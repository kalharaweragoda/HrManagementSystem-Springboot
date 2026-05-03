package edu.icet.service.impl;

import edu.icet.dto.EmployeeDto;
import edu.icet.dto.PayrollDto;
import edu.icet.dto.PayrollSendDto;
import edu.icet.entity.PayrollEntity;
import edu.icet.service.EmployeeService;
import edu.icet.service.PayrollService;
import org.modelmapper.ModelMapper;

public class PayrollServiceImpl implements PayrollService {

    private final PayrollRepository payrollRepository;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Override
    public PayrollSendDto getPayrollById(Integer id) {
        if (payrollRepository.existsById(id)) {
            PayrollDto payrollDto = modelMapper.map(payrollRepository.findById(id), PayrollDto.class);
            EmployeeDto employee = employeeService.getEmployeeById(payrollDto.getEmployeeId());
            return getPayrollSendDto(payrollDto, employee);
        }
        return null;
    }

    @Override
    public PayrollSendDto addPayroll(PayrollDto payrollDto) {
        if (payrollDto.getId() == null) {
            PayrollDto saved = modelMapper.map(payrollRepository.save(modelMapper.map(payrollDto, PayrollEntity.class)), PayrollDto.class);
            EmployeeDto employee = employeeService.getEmployeeById(payrollDto.getEmployeeId());
            return getPayrollSendDto(saved, employee);
        }
        return null;
    }


    @Override
    public PayrollSendDto updatePayroll(Integer id, PayrollDto payrollDto) {
        if (payrollRepository.existsById(id) && id.equals(payrollDto.getId())) {
            PayrollDto updated = modelMapper.map(payrollRepository.save(modelMapper.map(payrollDto, PayrollEntity.class)), PayrollDto.class);
            EmployeeDto employee = employeeService.getEmployeeById(payrollDto.getEmployeeId());
            return getPayrollSendDto(updated, employee);
        }
        return null;
    }

}
