package com.rapidboot;

import com.alibaba.fastjson.JSON;
import com.rapidboot.generator.dao.DbAccess;
import com.rapidboot.generator.pojo.ColumnInfo;
import com.rapidboot.generator.pojo.ProjectInfo;
import com.rapidboot.generator.pojo.TableInfo;
import com.rapidboot.generator.utils.StringUtils;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.java.Log;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 */
@Log
public class App {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/yoma?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";


    //  Database credentials
    static final String USER = "root";
    static final String PASS = "";


    public static void main(String[] args) {
        System.out.println("Hello World!");
//        DbAccess.getTables();

        generateByDirectory("template/default");
//        DbAccess.getTableInfo("t_car_price_his");

//        genTableConfig("t_login_app_history");
    }

    private static final Pattern packagePattern = Pattern.compile("package(.*?);");
    private static final Pattern classPattern = Pattern.compile(" (class|interface|enum) (.*?) ");
    private static final Pattern mapperPattern = Pattern.compile("<(.*?)mapper(.*?)namespace(.*?)=(.*?)\"(.*?)\"(.*?)>");
    private static final Pattern outputPattern = Pattern.compile("<!--(.*?)output:(.*?)-->");
    private static final String outputDir = "/data/github/rapid-boot/rapid-boot/rapid-springboot-demo/src/main/";

    private static void save(String content) {
        String packageName = null;
        String className = null;
        String mapper = null;
        String output = null;


        Matcher m = packagePattern.matcher(content);
        if (m.find()) {
            packageName = m.group(1).trim();
        }
        m = classPattern.matcher(content);
        if (m.find()) {
            className = m.group(2).trim();
        }
        m = mapperPattern.matcher(content);
        if (m.find()) {
            mapper = m.group(5).trim();
        }
        m = outputPattern.matcher(content);
        if (m.find()) {
            output = m.group(2).trim();
        }


        if (StringUtils.isNotBlank(packageName) && StringUtils.isNotBlank(className)) {
            try {
                File dir = new File(outputDir + "java" + File.separator + packageName.replace('.', File.separatorChar));
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                Files.write(Paths.get(dir.getAbsolutePath(), className + ".java"), content.getBytes(StandardCharsets.UTF_8));

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (StringUtils.isNotBlank(mapper)) {
            try {
                //是 mapper 文件，写到 resource 下面。
                mapper = mapper.substring(mapper.lastIndexOf('.') + 1);
                File dir = new File(outputDir + File.separator + "resources/mapper");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                Files.write(Paths.get(dir.getAbsolutePath(), mapper + ".xml"), content.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (StringUtils.isNotBlank(output)) {
            try {
                //是 output 文件，写到模板配置的文件下面。
                File dir = new File(outputDir + output);
                if (!dir.getParentFile().exists()) {
                    dir.getParentFile().mkdirs();
                }
                Files.write(Paths.get(outputDir + output), content.getBytes(StandardCharsets.UTF_8));
            } catch (Exception e) {
                e.printStackTrace();
            }


        } else {
            log.warning("没有配置输出文件，所以打印出来：" + content);
            System.out.println(content);
        }

        System.out.println("exit IDEA，and ");
        System.out.println("you can format the generated file by command: ");
        System.out.println("/Applications/IntelliJ\\ IDEA.app/Contents/bin/format.sh -r " + outputDir + "**/*.java");


    }


    public static void generateByDirectory(String templateDirectory) {
        try {
            String json = new String(Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("table_generator_config.json").toURI())));


            TableInfo tableInfo = JSON.parseObject(json, TableInfo.class);

            //开始生成 tableinfo
            tableInfo = new TableInfo();

            ProjectInfo projectInfo = new ProjectInfo();
//            private String packageName;
//            private Boolean queryHasBigDecimal;
//            private Boolean auto;
//            private String date;
//            private String author;

            projectInfo.setPackageName("com.rapidboot.demo");
            projectInfo.setQueryHasBigDecimal(true);
            projectInfo.setAuto(true);
            projectInfo.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date()));
            projectInfo.setAuthor("广峰 <gf@gfzj.us>");

            tableInfo.setTableName("t_login_app_history");
            tableInfo.setHasTimestamp(false);
            tableInfo.setHasDateAnnotation(false);
            tableInfo.setHasQuery(true);
            tableInfo.setHasDict(false);
            tableInfo.setHasBigDecimal(false);
            tableInfo.setApiAlias("这个是很牛逼的接口，其实直接引用 table 的介绍就行了,先偷懒");

            tableInfo.setPackageName(projectInfo.getPackageName());
            tableInfo.setQueryHasBigDecimal(projectInfo.getQueryHasBigDecimal());
            tableInfo.setAuto(projectInfo.getAuto());
            tableInfo.setDate(projectInfo.getDate());
            tableInfo.setAuthor(projectInfo.getAuthor());

            //从 columns 获取信息
            tableInfo.setQueryColumns(new LinkedList<>());
            tableInfo.setIsNotNullColumns(new LinkedList<>());
            tableInfo.setBetweens(new LinkedList<>());
            tableInfo.setDicts(new LinkedList<>());


            List<ColumnInfo> columnInfos = DbAccess.getColumnInfo(tableInfo.getTableName());
            tableInfo.setColumns(columnInfos);
            int index = 0;
            for (ColumnInfo columnInfo : columnInfos) {
                columnInfo.setColumnType(StringUtils.getJavaTypeFromColumnType(columnInfo.getColumnType()));
                columnInfo.setQueryType(StringUtils.getQueryTypeFromColumnType(columnInfo.getColumnType()));
                columnInfo.setChangeColumnName(StringUtils.toCamelCase(columnInfo.getColumnName()));
                columnInfo.setFormShow(true);
                columnInfo.setFormType("Input");
                columnInfo.setColumnShow(true);

                if ("PRI".equals(columnInfo.getColumnKey())) {
                    tableInfo.setPkChangeColName(columnInfo.getColumnName());
                }
                if (columnInfo.getNotNull()) {
                    tableInfo.getIsNotNullColumns().add(StringUtils.getColumnsRef(index));
                }
                if ("BetWeen".equalsIgnoreCase(columnInfo.getQueryType())) {
                    tableInfo.getBetweens().add(StringUtils.getColumnsRef(index));
                }

                if (StringUtils.isNotBlank(columnInfo.getQueryType())) {
                    tableInfo.getQueryColumns().add(StringUtils.getColumnsRef(index));
                }

                index++;
            }

            tableInfo.setClassName(StringUtils.getClassNameFromTableName(tableInfo.getTableName()));
            tableInfo.setChangeClassName(StringUtils.getInstanceNameFromTableName(tableInfo.getTableName()));


//            System.out.println(JSON.toJSONString(tableInfo, true));

            Map<String, Object> tableMap = JSON.parseObject(JSON.toJSONString(tableInfo), Map.class);

            System.out.println();
            System.out.println(JSON.toJSONString(tableMap, true));
            System.out.println();


            //1.创建配置类
            Configuration configuration = new Configuration(Configuration.getVersion());
            URL url = ClassLoader.getSystemResource(templateDirectory);
            int subIndex = url.getPath().length() + 1;
            configuration.setDirectoryForTemplateLoading(new File(url.getPath()));
            //3.设置字符集
            configuration.setDefaultEncoding("UTF-8");
            //4.加载模板
            Files.walk(Paths.get(url.toURI())).filter(Files::isRegularFile).filter(p -> p.toString().endsWith(".ftl")).forEach(p -> {
                try {

                    System.out.println(p);
                    String templateFile = p.toString().substring(subIndex);
                    Template template = configuration.getTemplate(templateFile);

                    StringWriter writer = new StringWriter();

                    template.process(tableMap, writer);

                    save(writer.toString());
//                    System.out.println(writer.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }


            });

        } catch (Exception e) {

            e.printStackTrace();

        }


    }
}
