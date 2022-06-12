package com.rixiangtek.common.api;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @program: swis-parent
 * @description:
 * @author: 陈学宝
 * @create: 2022-05-11 10:35
 **/
@Data
@Component
@ConfigurationProperties(prefix = "backupconfig")
public class BackupConfig {
    @Value("${BASE_FILE_PATH}")
    private String BASE_FILE_PATH;
    @Value("${BACKUP_PATH}")
    private String BACKUP_PATH;
    @Value("${mysqldumpPath}")
    private String mysqldumpPath;
    private DbConfig swis;
    private DbConfig xxlJob;
    private DbConfig his;
    private DbConfig febsBase;
    private DbConfig febsQuartz;
}
----------------


package com.rixiangtek.common.api;

import lombok.Data;

/**
 * @program: swis-parent
 * @description:
 * @author: 陈学宝
 * @create: 2022-05-11 10:38
 **/
@Data
public class DbConfig {
    /**
     * 数据库用户名
     **/
    private String username;
    /**
     * 数据库密码
     **/
    private String password;
    /**
     * 数据库端口号
     **/
    private String port;
    /**
     * 数据库名
     **/
    private String dbName;
}

-----------------




@Resource
BackupConfig backupConfig;

public void backup() {
        try {
            String basePath = backupConfig.getBASE_FILE_PATH() + backupConfig.getBACKUP_PATH() + "/";
            String insertPath = basePath + DateUtil.formatDate(new Date(),DateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS_SSS) + "/";
            FileUtil.mkdir(basePath);
            File[] ls = FileUtil.ls(basePath);
            if(ls.length > 0){
                Date date = new Date();
                Calendar cal  = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH,-1);
                long oldTime = cal.getTime().getTime();
                for (File f: ls){
                    BasicFileAttributes attributes = Files.readAttributes(f.toPath(), BasicFileAttributes.class);
                    FileTime fileTime = attributes.creationTime();
                    long fileCurrentTime = fileTime.toMillis();
                    if(oldTime > fileCurrentTime){
                        FileUtil.del(f);
                    }
                }
            }
            FileUtil.del(insertPath);
            FileUtil.mkdir(insertPath);
            /** 默认使用linux*/
            //String cmdPrefix = "/bin/sh -c ";
            String c1 = "/bin/sh";
            String c2 = "-c";
            String os_name = System.getProperty("os.name");
            // 判断是否是windows系统
            if (os_name.toLowerCase().startsWith("win")){
                //cmdPrefix = "cmd /c ";
                c1 = "cmd";
                c2 = "/c";
            }
            DbConfig swisDbConfig = backupConfig.getSwis();
            DbConfig febsBaseDbConfig = backupConfig.getFebsBase();
            DbConfig hisDbConfig = backupConfig.getHis();
            DbConfig xxlJobDbConfig = backupConfig.getXxlJob();
            DbConfig febsQuartzConfig = backupConfig.getFebsQuartz();
            String swisFileName = buildBackupFileName(insertPath, swisDbConfig);
            String febsBaseFileName = buildBackupFileName(insertPath, febsBaseDbConfig);
            String hisFileName = buildBackupFileName(insertPath, hisDbConfig);
            String xxlJobFileName = buildBackupFileName(insertPath, xxlJobDbConfig);
            String febsQuartzFileName = buildBackupFileName(insertPath, febsQuartzConfig);
            List<String> cmds = new ArrayList<>();
            cmds.add(buildCmd(backupConfig.getMysqldumpPath(), swisDbConfig, swisFileName));
//            cmds.add(buildCmd(backupConfig.getMysqldumpPath(), febsBaseDbConfig, febsBaseFileName));
//            cmds.add(buildCmd(backupConfig.getMysqldumpPath(), hisDbConfig, hisFileName));
//            cmds.add(buildCmd(backupConfig.getMysqldumpPath(), xxlJobDbConfig, xxlJobFileName));
//            cmds.add(buildCmd(backupConfig.getMysqldumpPath(), febsQuartzConfig, febsQuartzFileName));
            for(String s: cmds){
                log.info("database backup start:" + DateUtil.now() + ",command is: " + s);
                /**
                 * exec重载方法有一个参数的，window下执行正常，linux下无法完成备份。
                 * 使用多参数重载方法都可以正常备份
                 */
                Process process = Runtime.getRuntime().exec(new String[]{c1, c2, s});
                process.waitFor();
                log.info("database backup end:" + DateUtil.now());
            }
        }catch (Exception e){
            log.error("database backup error:" + e.getMessage());
        }
}

-----------------
backupconfig.swis.username=${spring.datasource.username}
backupconfig.swis.password=${spring.datasource.password}
backupconfig.swis.port=${spring.datasource.port}
backupconfig.swis.dbName=kaifa_dev_swis
backupconfig.xxlJob.username=${spring.datasource.username}
backupconfig.xxlJob.password=${spring.datasource.password}
backupconfig.xxlJob.port=${spring.datasource.port}
backupconfig.xxlJob.dbName=kaifa_xxl_job
backupconfig.his.username=${spring.datasource.username}
backupconfig.his.password=${spring.datasource.password}
backupconfig.his.port=${spring.datasource.port}
backupconfig.his.dbName=dev_his
backupconfig.febsBase.username=${spring.datasource.username}
backupconfig.febsBase.password=${spring.datasource.password}
backupconfig.febsBase.port=${spring.datasource.port}
backupconfig.febsBase.dbName=febs_base
backupconfig.febsQuartz.username=${spring.datasource.username}
backupconfig.febsQuartz.password=${spring.datasource.password}
backupconfig.febsQuartz.port=${spring.datasource.port}
backupconfig.febsQuartz.dbName=febs_quartz
mysqldumpPath=D:/chenxuebao/app/phpstudy_pro/Extensions/MySQL5.7.26/bin/mysqldump.exe

