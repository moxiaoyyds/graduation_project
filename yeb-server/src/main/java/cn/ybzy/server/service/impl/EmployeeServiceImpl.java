package cn.ybzy.server.service.impl;

import cn.ybzy.server.mapper.MailLogMapper;
import cn.ybzy.server.pojo.*;
import cn.ybzy.server.mapper.EmployeeMapper;
import cn.ybzy.server.service.IEmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 王浩
 * @since 2022-08-20
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MailLogMapper mailLogMapper;

    /**
     * 获取所有员工(分页)
     *
     * @param currentPage
     * @param size
     * @param employee
     * @param beginDateScope
     * @return
     */
    @Override
    public RespPageBean getEmployeeByPage(Integer currentPage, Integer size,
                                          Employee employee, LocalDate[] beginDateScope) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeByPage = employeeMapper.getEmployeeByPage(page, employee, beginDateScope);
        RespPageBean respPageBean = new RespPageBean(employeeByPage.getTotal(), employeeByPage.getRecords());
        return respPageBean;
    }

    /**
     * 获取工号
     * @return
     */
    @Override
    public RespBean maxWorkID() {
        // 查询最大的字段
        List<Map<String, Object>> maps = employeeMapper.selectMaps(new QueryWrapper<Employee>().select("max(workID)"));
        // 转换为int加1再转换成字符串后返回respBean
        return RespBean.success("最新工号", String.format("%08d", Integer.parseInt(maps.get(0).get("max(workID)").toString())+1));
    }

    /**
     * 添加员工
     * @param employee
     * @return
     */
    @Override
    public RespBean addEmp(Employee employee) {
        // 获取合同开始时间
        LocalDate beginContract = employee.getBeginContract();
        // 获取合同结束时间
        LocalDate endContract = employee.getEndContract();
        // 计算合同一共是多少天
        long days = beginContract.until(endContract, ChronoUnit.DAYS);
        // 处理合同期限，保留两位小数
        DecimalFormat decimalFormat = new DecimalFormat("##.00");
        // 将合同天数转为年
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(days/365.00)));
        // 插入到数据库中并判断数据库受影响的行数，如果为1表示插入成功
        if (1 == employeeMapper.insert(employee)) {
            // 获取完整的员工对象
            Employee emp = employeeMapper.getEmployee(employee.getId()).get(0);
            // 数据库记录发送的消息
            String msgId = UUID.randomUUID().toString();
            MailLog mailLog = new MailLog();
            mailLog.setMsgId(msgId);
            mailLog.setEid(employee.getId());
            // 设置状态
            mailLog.setStatus(0);
            // 设置路由键
            mailLog.setRouteKey(MailConstants.MAIL_ROUTING_KEY_NAME);
            // 设置交换机
            mailLog.setExchange(MailConstants.MAIL_EXCHANGE_NAME);
            // 设置重试次数
            mailLog.setCount(0);
            // 设置重试时间 在当前时间基础上再推一分钟
            mailLog.setTryTime(LocalDateTime.now().plusMinutes(MailConstants.MSG_TIMEOUT));
            // 设置创建时间
            mailLog.setCreateTime(LocalDateTime.now());
            // 设置更新时间
            mailLog.setUpdateTime(LocalDateTime.now());
            mailLogMapper.insert(mailLog);
            // 发送信息
            rabbitTemplate.convertAndSend(MailConstants.MAIL_EXCHANGE_NAME,
                    MailConstants.MAIL_ROUTING_KEY_NAME, emp, new CorrelationData(msgId));

            return RespBean.success("添加成功！");
        }
        return RespBean.error("添加失败！");
    }

    /**
     * 查询员工
     * @param id
     * @return
     */
    @Override
    public List<Employee> getEmployee(Integer id) {
        return employeeMapper.getEmployee(id);
    }

    /**
     * 获取所有员工账套
     * @param currentPage
     * @param size
     * @return
     */
    @Override
    public RespPageBean getEmployeeWithSalary(Integer currentPage, Integer size) {
        // 开启分页
        Page<Employee> page = new Page<>(currentPage, size);
        IPage<Employee> employeeIPage = employeeMapper.getEmployeeWithSalary(page);
        RespPageBean respPageBean = new RespPageBean(employeeIPage.getTotal(),employeeIPage.getRecords());
        return respPageBean;
    }
}
