package edu.icet.service;

import edu.icet.dto.PayrollDto;
import edu.icet.dto.PayrollSendDto;

public interface PayrollService {
    PayrollSendDto getPayrollById(Integer id);

    PayrollSendDto updatePayroll(Integer id, PayrollDto payrollDto);
}
