package com.lvgr.edubase.exceptionhandle;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lvgr
 * @date 2021/3/10 22:01
 * @desc
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EduException extends RuntimeException {

    public static Integer ERROR = 20001;

    private Integer code;

    private String msg;
}
