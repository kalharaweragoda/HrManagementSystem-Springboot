package edu.icet.service.impl;

import edu.icet.dto.EmployeeDto;
import edu.icet.dto.LeaveDto;
import edu.icet.dto.LeaveSendDto;
import edu.icet.entity.LeaveEntity;
import edu.icet.repository.LeaveRepository;
import edu.icet.service.EmployeeService;
import edu.icet.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeService employeeService;
    private final ModelMapper modelMapper;

    @Override
    public List<LeaveSendDto> getAllLeaves() {
        List<LeaveEntity> allLeaves = leaveRepository.findAll();
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();
        List<LeaveSendDto> dtoList = new ArrayList<>();

        for (LeaveEntity leave : allLeaves) {
            LeaveDto leaveDto = modelMapper.map(leave, LeaveDto.class);
            for (EmployeeDto employee : allEmployees) {
                if (employee.getId().equals(leaveDto.getEmployeeId())) {
                    dtoList.add(getLeaveSendDto(leaveDto, employee));
                }
            }
        }
        return dtoList;
    }

    @Override
    public LeaveSendDto getLeaveById(Integer id) {
        Optional<LeaveEntity> leaveOpt = leaveRepository.findById(id);
        if (leaveOpt.isPresent()) {
            LeaveEntity entity = leaveOpt.get();
            LeaveDto leaveDto = modelMapper.map(entity, LeaveDto.class);
            leaveDto.setEmployeeId(entity.getEmployee().getId());
            EmployeeDto employeeDto = employeeService.getEmployeeById(entity.getEmployee().getId());
            return getLeaveSendDto(leaveDto, employeeDto);
        }
        return null;
    }

    @Override
    public LeaveSendDto addLeave(LeaveDto leaveDto) {
        System.out.println(leaveDto.toString());
        if (leaveDto.getId() == null) {
            LeaveDto saved = modelMapper.map(leaveRepository.save(modelMapper.map(leaveDto, LeaveEntity.class)), LeaveDto.class);
            EmployeeDto employee = employeeService.getEmployeeById(leaveDto.getEmployeeId());
            return getLeaveSendDto(saved, employee);
        }
        return null;
    }

    @Override
    public LeaveSendDto updateLeave(Integer id, LeaveDto leaveDto) {
        if (id == null || leaveDto.getId() == null) {
            return null;
        }
        if (leaveDto.getId().equals(id) && leaveRepository.existsById(id)) {
            LeaveDto saved = modelMapper.map(leaveRepository.save(modelMapper.map(leaveDto, LeaveEntity.class)), LeaveDto.class);
            EmployeeDto employee = employeeService.getEmployeeById(leaveDto.getEmployeeId());
            return getLeaveSendDto(saved, employee);
        }
        return null;
    }

    @Override
    public Boolean deleteLeave(Integer id) {
        if (leaveRepository.existsById(id)) {
            leaveRepository.deleteById(id);
            return !leaveRepository.existsById(id);
        }
        return false;
    }

    private LeaveSendDto getLeaveSendDto(LeaveDto leaveDto, EmployeeDto employeeDto) {
        return new LeaveSendDto(
                leaveDto.getId(),
                employeeDto,
                leaveDto.getLeaveType(),
                leaveDto.getStartDate(),
                leaveDto.getEndDate(),
                leaveDto.getReason(),
                leaveDto.getStatus(),
                leaveDto.getCreatedAt(),
                leaveDto.getUpdatedAt()
        );
    }
}
