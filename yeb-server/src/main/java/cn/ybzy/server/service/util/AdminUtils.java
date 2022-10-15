package cn.ybzy.server.service.util;

import cn.ybzy.server.pojo.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author wangHao
 * @Description 操作员工具类
 * @createTime 2022-10-15 11:19
 **/
public class AdminUtils {

    /**
     * 获取当前登陆操作员
     *
     * @return
     */
    public static Admin getCurrentAdmin() {
        return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
