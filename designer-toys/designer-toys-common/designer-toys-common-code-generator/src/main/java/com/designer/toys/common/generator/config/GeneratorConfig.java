package com.designer.toys.common.generator.config;

import lombok.Data;

/**
 * @author Martin Chen
 * @version 1.0
 * @className GeneratorConfig (此处以class为例)
 * @date 2025/11/19/周三
 * @description
 */
@Data
public class GeneratorConfig {
    /**
     * 数据库配置
     * 根据数据库连接信息进行修改
     */
    private String dbUrl = "jdbc:mysql://localhost:3306/your_database?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
    private String dbUsername = "your_username";
    private String dbPassword = "your_password";
    private String dbDriver = "com.mysql.cj.jdbc.Driver";
    /**
     * 包配置 按照你的项目需求进行修改
     * 这里的包的基本路径就是com.designer.toys
     * 如果模块名称为product，那么包路径就是com.designer.toys.product
     */
    private String packageParent = "com.designer.toys";
    private String packageModule = "product";
    private String packageEntity = "entity";
    private String packageMapper = "mapper";
    private String packageService = "service";
    private String packageServiceImpl = "service.impl";
    private String packageController = "controller";

    /**
     * 路径配置 - 修改为D盘generator_code文件夹
     */
    private String outputDir = "D:/generator_code/src/main/java";
    private String xmlOutputDir = "D:/generator_code/src/main/resources/mapper";

    /**
     * 作者
     */
    private String author = "code-generator";

    /**
     * 表前缀
     */
    private String[] tablePrefix = {"t_", "sys_"};

    /**
     * 需要生成的表名
     */
    private String[] includeTables;

    /**
     * 排除生成的表名
     */
    private String[] excludeTables;

    /**
     * 是否生成 Swagger 注解
     */
    private boolean swagger = true;

    /**
     * 是否覆盖已有文件
     */
    private boolean fileOverride = true;

    /**
     * 是否生成 Controller
     */
    private boolean generateController = true;

    /**
     * 是否生成 Service
     */
    private boolean generateService = true;

    /**
     * 是否生成 Mapper
     */
    private boolean generateMapper = true;
}
