package com.nicefish.rbac.shiro.filter;

import com.google.code.kaptcha.Constants;
import com.nicefish.rbac.constant.AuthConstants;
import com.nicefish.rbac.shiro.util.NiceFishSecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 验证码
 * @author 大漠穷秋
 */
public class NiceFishCaptchaValidateFilter extends AccessControlFilter {
    private static final Logger logger = LoggerFactory.getLogger(NiceFishCaptchaValidateFilter.class);

    private boolean captchaEnabled = true;

    private String captchaType = "math";

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        request.setAttribute(AuthConstants.CURRENT_ENABLED, captchaEnabled);
        request.setAttribute(AuthConstants.CURRENT_TYPE, captchaType);
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (!this.captchaEnabled)  return true;
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String code = (String)NiceFishSecurityUtils.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String validateCode=httpReq.getParameter(AuthConstants.CURRENT_VALIDATECODE);

        //TODO:这里需要 fix 一下，处理 post 请求里面的 captcha 参数，否则会影响前端传递参数的方式。
//        try{
//            if("GET".equals(httpReq.getMethod())){
//                validateCode=httpReq.getParameter(AuthConstants.CURRENT_VALIDATECODE);
//            }else if("POST".equals(httpReq.getMethod())){
//                String jsonParams=httpReq.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//                ObjectMapper mapper = new ObjectMapper();
//                JsonNode actualObj = mapper.readTree(jsonParams);
//                code= String.valueOf(actualObj.get("captcha"));
//            }
//        }catch (Exception e){
//            logger.error(e.getMessage());
//        }

        if (!code.equalsIgnoreCase(validateCode)) {
            return false;
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        request.setAttribute(AuthConstants.CURRENT_CAPTCHA, AuthConstants.CAPTCHA_ERROR);
        return true;
    }

    public boolean isCaptchaEnabled() {
        return captchaEnabled;
    }

    public void setCaptchaEnabled(boolean captchaEnabled) {
        this.captchaEnabled = captchaEnabled;
    }

    public String getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(String captchaType) {
        this.captchaType = captchaType;
    }
}
