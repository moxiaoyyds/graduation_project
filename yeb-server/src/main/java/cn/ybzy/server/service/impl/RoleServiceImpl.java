package cn.ybzy.server.service.impl;

import cn.ybzy.server.pojo.Role;
import cn.ybzy.server.mapper.RoleMapper;
import cn.ybzy.server.service.IRoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
