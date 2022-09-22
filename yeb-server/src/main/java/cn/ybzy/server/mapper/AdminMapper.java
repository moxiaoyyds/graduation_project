package cn.ybzy.server.mapper;

import cn.ybzy.server.pojo.Admin;
import cn.ybzy.server.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.context.annotation.Lazy;
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
public interface AdminMapper extends BaseMapper<Admin> {


}
