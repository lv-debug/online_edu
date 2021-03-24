package com.lvgr.code.excelread;

import com.alibaba.excel.EasyExcel;
import com.lvgr.code.excelwrite.ExcelDemo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvgr
 * @date 2021/3/22 22:44
 * @desc:EasyExcel实现读的操作。
 */
public class TestExcelRead {
    public static void main(String[] args) {
        String filename = "F:\\write.xlsx";
        EasyExcel.read(filename,ReadData.class,new ExcelListener()).sheet("学生列表").doRead();
    }
}
