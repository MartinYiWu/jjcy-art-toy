package com.designer.toys;

import com.designer.toys.common.generator.CodeGenerator;
import com.designer.toys.common.generator.config.GeneratorConfig;

public class QuickGeneratorStart {
    public static void main(String[] args) {
        GeneratorConfig config = new GeneratorConfig();

        // 数据库配置
        config.setDbUrl("jdbc:mysql://localhost:3306/jjcy_designer_toy");
        config.setDbUsername("root");
        config.setDbPassword("jjcy123456");

        // 包配置
        config.setPackageParent("com.designer.toys");
        config.setPackageModule("product");

        // 生成配置
        config.setIncludeTables(new String[]{"t_user"});
        // 表明前缀,生成实体时会去掉表前缀
        config.setTablePrefix(new String[]{"tbl_", "biz_", "t_"});
        //是否生成swagger注解
        config.setSwagger(true);
        //是否覆盖文件
        config.setFileOverride(true);
        config.setAuthor("Martin Chen");

        // 控制生成内容 对应生成Controller Service ServiceImpl Mapper
        config.setGenerateController(false);
        config.setGenerateService(false);
        config.setGenerateMapper(true);

        CodeGenerator generator = new CodeGenerator(config);
        generator.execute();

        System.out.println("代码生成完成！");
    }

}