package com.lvgr.code.excelwrite;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author lvgr
 * @date 2021/3/22 22:41
 * @desc
 */
@Data
public class ExcelDemo {

    @ExcelProperty(value = "学生编号")
    private Integer sno;

    @ExcelProperty(value = "学生姓名")
    private String sname;
}
