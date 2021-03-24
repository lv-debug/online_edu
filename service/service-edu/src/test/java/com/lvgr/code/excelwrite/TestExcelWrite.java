package com.lvgr.code.excelwrite;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lvgr
 * @date 2021/3/22 22:44
 * @desc:EasyExcel实现写的操作。
 */
public class TestExcelWrite {
    public static void main(String[] args) {
        String filename = "F:\\write.xlsx";
        EasyExcel.write(filename,ExcelDemo.class).sheet("学生列表").doWrite(data());
    }

    private static List<ExcelDemo> data() {
        List<ExcelDemo> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ExcelDemo data = new ExcelDemo();
            data.setSno(i);
            data.setSname("张三"+i);
            list.add(data);
        }
        return list;
    }
}
