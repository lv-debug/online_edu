package com.lvgr.code.excelread;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author lvgr
 * @date 2021/3/22 22:54
 * @desc:读的操作
 */
@Data
public class ReadData {
    //设置列对应的属性
    @ExcelProperty(index = 0)
    private int sid;
        //设置列对应的属性
    @ExcelProperty(index = 1)
    private String sname;
}
