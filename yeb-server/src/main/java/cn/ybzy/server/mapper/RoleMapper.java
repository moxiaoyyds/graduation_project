package cn.ybzy.server.mapper;

import cn.ybzy.server.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 王浩
 * @since 2022-08-20
 */
@Repository
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);
}
