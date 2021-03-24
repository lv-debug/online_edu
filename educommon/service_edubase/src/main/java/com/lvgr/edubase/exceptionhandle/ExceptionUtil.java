package com.lvgr.edubase.exceptionhandle;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author lvgr
 * @date 2021/3/11 16:58
 * @desc:异常工具类
 */
public class ExceptionUtil {

    /**
     * 获取异常的堆栈信息
     * @param t
     * @return
     */
    public static String getStackTrace(Exception t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}