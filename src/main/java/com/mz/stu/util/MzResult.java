package com.mz.stu.util;

import lombok.Data;

@Data
public class MzResult {

    private Boolean isSuccess = true;
    private String message = "操作成功";

    public MzResult(String message) {
        this.isSuccess = false;
        this.message = message;
    }

    public MzResult() {

    }
}