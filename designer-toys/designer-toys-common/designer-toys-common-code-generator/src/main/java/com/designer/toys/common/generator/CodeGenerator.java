package com.designer.toys.common.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.designer.toys.common.generator.config.GeneratorConfig;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Collections;
import java.util.Scanner;
/**
 * @author Martin Chen
 * @version 1.0
 * @className CodeGenerator (此处以class为例)
 * @date 2025/11/19/周三
 * @description 自定义代码生成器
 */
@Slf4j
public class CodeGenerator {
    private GeneratorConfig config;

    public CodeGenerator() {
        this.config = new GeneratorConfig();
    }

    public CodeGenerator(GeneratorConfig config) {
        this.config = config;
    }

    /**
     * 执行代码生成
     */
    public void execute() {
        // 创建输出目录
        createOutputDirs();

        FastAutoGenerator.create(config.getDbUrl(), config.getDbUsername(), config.getDbPassword())
                // 全局配置
                .globalConfig(builder -> {
                    builder.author(config.getAuthor()) // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .dateType(DateType.ONLY_DATE) // 时间策略
                            .commentDate("yyyy-MM-dd") // 注释日期
                            .outputDir(config.getOutputDir()) // 输出目录
                            .fileOverride() // 覆盖文件
                            .disableOpenDir(); // 禁止打开输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent(config.getPackageParent()) // 设置父包名
                            .moduleName(config.getPackageModule()) // 设置父包模块名
                            .entity(config.getPackageEntity())
                            .mapper(config.getPackageMapper())
                            .service(config.getPackageService())
                            .serviceImpl(config.getPackageServiceImpl())
                            .controller(config.getPackageController())
                            .pathInfo(Collections.singletonMap(OutputFile.xml, config.getXmlOutputDir())); // 设置mapperXml生成路径
                })
                // 策略配置
                .strategyConfig(builder -> {
                    if (config.getIncludeTables() != null && config.getIncludeTables().length > 0) {
                        builder.addInclude(config.getIncludeTables()); // 设置需要生成的表名
                    }
                    if (config.getTablePrefix() != null && config.getTablePrefix().length > 0) {
                        builder.addTablePrefix(config.getTablePrefix()); // 设置过滤表前缀
                    }
                    if (config.getExcludeTables() != null && config.getExcludeTables().length > 0) {
                        builder.addExclude(config.getExcludeTables()); // 设置排除表名
                    }

                    // Entity 策略配置
                    builder.entityBuilder()
                            .enableLombok() // 开启 Lombok
                            .enableTableFieldAnnotation() // 开启字段注解
                            .naming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                            .columnNaming(NamingStrategy.underline_to_camel) // 数据库表字段映射到实体的命名策略
                            .formatFileName("%sEntity") // 格式化文件名称
                            .enableChainModel(); // 链式模型

                    // Controller 策略配置
                    if (config.isGenerateController()) {
                        builder.controllerBuilder()
                                .enableHyphenStyle() // 开启驼峰转连字符
                                .enableRestStyle(); // 开启生成@RestController 控制器
                    }

                    // Service 策略配置
                    if (config.isGenerateService()) {
                        builder.serviceBuilder()
                                .formatServiceFileName("%sService") // 格式化 service 接口文件名称
                                .formatServiceImplFileName("%sServiceImpl"); // 格式化 service 实现类文件名称
                    }

                    // Mapper 策略配置
                    if (config.isGenerateMapper()) {
                        builder.mapperBuilder()
                                .enableMapperAnnotation() // 开启 @Mapper 注解
                                .enableBaseResultMap() // 启用 BaseResultMap 生成
                                .enableBaseColumnList(); // 启用 BaseColumnList
                    }
                })
                // 模板引擎配置
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();

        System.out.println("代码生成完成！");
        System.out.println("生成路径: " + config.getOutputDir());
        System.out.println("XML路径: " + config.getXmlOutputDir());
    }

    /**
     * 创建输出目录
     */
    private void createOutputDirs() {
        File javaDir = new File(config.getOutputDir());
        File xmlDir = new File(config.getXmlOutputDir());

        if (!javaDir.exists()) {
            javaDir.mkdirs();
            System.out.println("创建Java目录: " + javaDir.getAbsolutePath());
        }

        if (!xmlDir.exists()) {
            xmlDir.mkdirs();
            System.out.println("创建XML目录: " + xmlDir.getAbsolutePath());
        }
    }

    /**
     * 交互式生成代码
     */
    public static void interactiveExecute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== MyBatis-Plus 代码生成器 ===");
        System.out.println("生成路径: D:/generator_code/");

        // 数据库配置
        System.out.print("数据库URL (默认: jdbc:mysql://localhost:3306/test): ");
        String dbUrl = scanner.nextLine();
        if (dbUrl.isEmpty()) {
            dbUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        }

        System.out.print("数据库用户名: ");
        String username = scanner.nextLine();

        System.out.print("数据库密码: ");
        String password = scanner.nextLine();

        // 包配置
        System.out.print("父包名 (默认: com.example): ");
        String parentPackage = scanner.nextLine();
        if (parentPackage.isEmpty()) {
            parentPackage = "com.example";
        }

        System.out.print("模块名 (默认: system): ");
        String moduleName = scanner.nextLine();
        if (moduleName.isEmpty()) {
            moduleName = "system";
        }

        System.out.print("作者 (默认: generator): ");
        String author = scanner.nextLine();
        if (author.isEmpty()) {
            author = "generator";
        }

        System.out.println("请输入要生成的表名（多个表用逗号分隔，全部表输入all）：");
        String tableName = scanner.nextLine();

        GeneratorConfig config = new GeneratorConfig();
        config.setDbUrl(dbUrl);
        config.setDbUsername(username);
        config.setDbPassword(password);
        config.setPackageParent(parentPackage);
        config.setPackageModule(moduleName);
        config.setAuthor(author);

        if (!"all".equalsIgnoreCase(tableName) && !tableName.isEmpty()) {
            config.setIncludeTables(tableName.split(","));
        }

        CodeGenerator generator = new CodeGenerator(config);
        generator.execute();

        scanner.close();
    }

    /**
     * 快速生成示例
     */
    public static void quickGenerate(String[] tables) {
        GeneratorConfig config = new GeneratorConfig();
        config.setIncludeTables(tables);
        config.setPackageModule("demo");
        config.setAuthor("quick-generator");

        CodeGenerator generator = new CodeGenerator(config);
        generator.execute();
    }

    /**
     * 自定义生成
     */
    public static void customGenerate(String dbUrl, String username, String password,
                                      String packageParent, String packageModule,
                                      String[] tables) {
        GeneratorConfig config = new GeneratorConfig();
        config.setDbUrl(dbUrl);
        config.setDbUsername(username);
        config.setDbPassword(password);
        config.setPackageParent(packageParent);
        config.setPackageModule(packageModule);
        config.setIncludeTables(tables);

        CodeGenerator generator = new CodeGenerator(config);
        generator.execute();
    }

    public static void main(String[] args) {
        if (args.length > 0) {
            // 命令行参数方式
            quickGenerate(args);
        } else {
            // 交互式方式
            interactiveExecute();
        }
    }
}
