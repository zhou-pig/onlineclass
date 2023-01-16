/**
 * -*- codeing = utf-8 -*-
 *
 * @Time :2021/8/3 16:50
 * @Author :zhou_pig
 * @File :CodeGenerator.java
 * @Software :IntelliJ IDEA
 */
package com.graduation.onlineclass.utils;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {
    private static boolean needGenerate = true;

    public static void main(String[] args) {
        if (!needGenerate) {
            return;
        }
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");

        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("周富雄");
        gc.setFileOverride(false);
        gc.setOpen(false);
        gc.setServiceName("%sService");
        gc.setDateType(DateType.ONLY_DATE);
        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);//把全局配置放进去

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/online_class?useSSL=false&useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC&allowPublicKeyRetrieval=true");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("zfx666");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.graduation.onlineclass");
        pc.setController("controller");
        pc.setService("service");
        pc.setMapper("mapper");
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);
        //      策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
//        strategy.setLogicDeleteFieldName("")
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        mpg.execute();
    }

}
