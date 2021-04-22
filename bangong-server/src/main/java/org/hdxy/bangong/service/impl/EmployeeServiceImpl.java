package org.hdxy.bangong.service.impl;

import org.hdxy.bangong.pojo.Employee;
import org.hdxy.bangong.mapper.EmployeeMapper;
import org.hdxy.bangong.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LK
 * @since 2021-04-22
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
