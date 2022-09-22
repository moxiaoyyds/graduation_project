package cn.ybzy.server.service.impl;

import cn.ybzy.server.pojo.Employee;
import cn.ybzy.server.mapper.EmployeeMapper;
import cn.ybzy.server.service.IEmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 王浩
 * @since 2022-08-20
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
