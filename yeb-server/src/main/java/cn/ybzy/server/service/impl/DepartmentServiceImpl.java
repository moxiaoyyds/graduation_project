package cn.ybzy.server.service.impl;

import cn.ybzy.server.pojo.Department;
import cn.ybzy.server.mapper.DepartmentMapper;
import cn.ybzy.server.service.IDepartmentService;
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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
