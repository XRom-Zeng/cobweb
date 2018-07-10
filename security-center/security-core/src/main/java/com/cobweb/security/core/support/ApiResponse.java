package com.cobweb.security.core.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import static com.cobweb.security.core.support.ResponseCode.*;

@Data
@AllArgsConstructor
public class ApiResponse {

    private int code;

    private Object data;

    private String msg;

    private static ApiResponse of(int code, Object data, String msg) {
        return new ApiResponse(code, data, msg);
    }

    public static ApiResponse success() {
        return of(SUCCESSFUL.getCode(), null, SUCCESSFUL.getMsg());
    }

    public static ApiResponse success(Object data) {
        return of(SUCCESSFUL.getCode(), data, SUCCESSFUL.getMsg());
    }

    public static ApiResponse error() {
        return of(UNDEFINED.getCode(), null, UNDEFINED.getMsg());
    }

    public static ApiResponse error(ResponseCode responseCode) {
        return of(responseCode.getCode(), null, responseCode.getMsg());
    }
}
