package com.tuoniao.db.generator;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class AppDaoGenerator {

    public static final String PACKAGE_PATH = "com.tuoniao.sijibao.";

    public static void main(String[] args) throws Exception {
        //初始化数据库
        //第一个参数是数据库版本号，第二个参数是为你生成的bean类所放的文件夹名
        Schema schema = new Schema(1, PACKAGE_PATH + "beans");
        //设置生成的三个java文件的目录
        schema.setDefaultJavaPackageDao(PACKAGE_PATH + "daos");
        initAppConfig(schema);
        initUserBeanDb(schema);
        //第二个参数是输出路径，由于已经在lib_db中的build.gradle中配置过了，所以这里不再配置,
        new DaoGenerator().generateAll(schema, args[0]); //"../app/src/main/java-gen"
    }

    /**
     * 构建应用数据库表+字段
     */
    private static void initAppConfig(Schema schema) {
        Entity entity = schema.addEntity("AppDataBean");
        entity.addStringProperty("appVersion");
        entity.addStringProperty("newVersion");
    }

    /**
     * 构建用户数据库表+字段
     */
    private static void initUserBeanDb(Schema schema) {
        Entity entity = schema.addEntity("UserDataBean");
        entity.addStringProperty("firstName");
        entity.addStringProperty("lastName");
    }

}
