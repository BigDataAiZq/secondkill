package com.zjh.seckill.exception;

import com.zjh.seckill.result.CodeMsg;

/**
 * 全局异常类
 * 
 * @author ztq
 * @date 2018年10月26日
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg cm;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }

}
