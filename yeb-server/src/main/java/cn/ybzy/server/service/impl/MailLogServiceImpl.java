package cn.ybzy.server.service.impl;

import cn.ybzy.server.pojo.MailLog;
import cn.ybzy.server.mapper.MailLogMapper;
import cn.ybzy.server.service.IMailLogService;
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
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
