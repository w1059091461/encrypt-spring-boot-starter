package com.chenxuebao.web.controller;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.chenxuebao.entity.HospitalInfo;
import com.chenxuebao.entity.test02;
import com.chenxuebao.service.HospitalInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @Description:
 * <dependency>
 *   <groupId>cn.afterturn</groupId>
 *   <artifactId>easypoi-web</artifactId>
 *   <version>3.0.3</version>
 * </dependency>
 * <dependency>
 *   <groupId>cn.afterturn</groupId>
 *   <artifactId>easypoi-base</artifactId>
 *   <version>3.0.3</version>
 * </dependency>
 * <dependency>
 *   <groupId>cn.afterturn</groupId>
 *   <artifactId>easypoi-annotation</artifactId>
 *   <version>3.0.3</version>
 * </dependency>
 * @Author:陈学宝
 * @Date: 2022/6/12 18:58
 */
@RestController
@RequestMapping("/hospital-info")
@Slf4j
public class HospitalInfoController {
    @PostMapping("/test02")
    public void test02(@RequestParam("aa") MultipartFile multipartFile) throws Exception {
        ImportParams params = new ImportParams();
        InputStream inputStream = multipartFile.getInputStream();
        ExcelImportResult<test02> result = ExcelImportUtil.importExcelMore(inputStream, test02.class, params);
        List<test02> list = result.getList();
        System.out.println("'eeeeeeeeeeee");
    }

}

---------------------------
package com.chenxuebao.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

/**
 * @Author: chenXueBao
 * @Date: 2022/3/31 00:47
 * @Description:
 * @version: V1.0
 */
@Data
public class test02 {
    @Excel(name = "陈学宝1",orderNum = "0")
    private String aa;
    @Excel(name = "陈学宝2",orderNum = "1")
    private String aaa;
}