package cn.ybzy.server.service.impl;

import cn.ybzy.server.pojo.Oplog;
import cn.ybzy.server.mapper.OplogMapper;
import cn.ybzy.server.service.IOplogService;
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
public class OplogServiceImpl extends ServiceImpl<OplogMapper, Oplog> implements IOplogService {

}
