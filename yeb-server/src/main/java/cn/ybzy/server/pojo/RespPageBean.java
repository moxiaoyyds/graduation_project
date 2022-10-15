package cn.ybzy.server.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wangHao
 * @Description 分页公共返回对象
 * @createTime 2022-10-15 15:05
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespPageBean {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据
     */
    private List<?> date;
}
