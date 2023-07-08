package com.nicefish.rbac.shiro.filter;

import com.google.code.kaptcha.Constants;
import com.nicefish.rbac.constant.NiceFishAuthConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 验证码
 * @author 大漠穷秋
 */
public class NiceFishCaptchaValidateFilter extends AccessControlFilter {
    private boolean captchaEnabled = true;

    private String captchaType = "math";

    public void setCaptchaEnabled(boolean captchaEnabled) {
        this.captchaEnabled = captchaEnabled;
    }

    public void setCaptchaType(String captchaType) {
        this.captchaType = captchaType;
    }

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        request.setAttribute(NiceFishAuthConstants.CURRENT_ENABLED, captchaEnabled);
        request.setAttribute(NiceFishAuthConstants.CURRENT_TYPE, captchaType);
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (captchaEnabled == false || !"post".equals(httpServletRequest.getMethod().toLowerCase())) {
            return true;
        }

        Object obj = SecurityUtils.getSubject().getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(ObjectUtils.isEmpty(obj)){
            return false;
        }else{
            String code = String.valueOf(obj);
            String validateCode=httpServletRequest.getParameter(NiceFishAuthConstants.CURRENT_VALIDATECODE);
            if (StringUtils.isEmpty(validateCode) || !validateCode.equalsIgnoreCase(code)) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        request.setAttribute(NiceFishAuthConstants.CURRENT_CAPTCHA, NiceFishAuthConstants.CAPTCHA_ERROR);
        return true;
    }
}
