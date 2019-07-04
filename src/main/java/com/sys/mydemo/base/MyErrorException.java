package com.sys.mydemo.base;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 重新errorController
 */
@Controller
public class MyErrorException implements ErrorController {
    private static final String PATH = "/error";
    @Override
    public String getErrorPath() {
        return PATH;
    }
    @RequestMapping({"${error.path:/error}"})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        HttpStatus status = this.getStatus(request);
        return new ResponseEntity("123", status);
    }
    @RequestMapping(value = {"${error.path:/error}"},produces = "text/html")
    public String doHandleError(HttpServletRequest request) {
        System.out.println(getStatus(request).toString());
        return "/views/common/error/error";
    }
    /**
     * 获取错误编码
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        }
        catch (Exception ex) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
