package cn.ybzy.server.commen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * @author MengH
 * @date 2022/9/23 23:53
 * @JobNumber xxxxx
 */
public class CodeGenerator {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
    public static void main(String[] args) {
        //生成一个代码生成器对象
        AutoGenerator generator = new AutoGenerator();
        //配置策略
        //1.全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir("src/main/java");
        globalConfig.setAuthor("HM");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);//是否覆盖
        globalConfig.setServiceName("%sService");//去除service的i前缀
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setSwagger2(true);
        generator.setGlobalConfig(globalConfig);
        //2.数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/school?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&useSSL=true");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setDbType(DbType.MYSQL);
        generator.setDataSource(dataSourceConfig);
        //3.包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(scanner("模块名"));
        packageConfig.setParent("com.example.springbootproject")
                .setController("controller")
                .setService("service")
                .setServiceImpl("serviceImpl")
                .setMapper("mapper")
                .setEntity("entity");
        generator.setPackageInfo(packageConfig);
        //4.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);//自动lombok
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setVersionFieldName("version");
        generator.setStrategy(strategy);
        generator.execute();
    }
}
