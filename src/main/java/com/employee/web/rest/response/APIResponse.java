package com.employee.web.rest.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class APIResponse<T>{
    private T data;
}
