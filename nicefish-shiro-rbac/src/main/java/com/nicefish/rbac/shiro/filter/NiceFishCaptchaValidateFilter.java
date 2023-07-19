package com.nicefish.rbac.shiro.filter;

import com.google.code.kaptcha.Constants;
import com.nicefish.rbac.constant.NiceFishAuthConstants;
import com.nicefish.rbac.shiro.util.NiceFishSecurityUtils;
import org.apache.shiro.web.filter.AccessControlFilter;

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

    @Override
    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        request.setAttribute(NiceFishAuthConstants.CURRENT_ENABLED, captchaEnabled);
        request.setAttribute(NiceFishAuthConstants.CURRENT_TYPE, captchaType);
        return super.onPreHandle(request, response, mappedValue);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (!this.captchaEnabled)  return true;

        HttpServletRequest httpReq = (HttpServletRequest) request;
        String code = (String)NiceFishSecurityUtils.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String validateCode=httpReq.getParameter(NiceFishAuthConstants.CURRENT_VALIDATECODE);
        if (!code.equalsIgnoreCase(validateCode)) {
            return false;
        }

        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        request.setAttribute(NiceFishAuthConstants.CURRENT_CAPTCHA, NiceFishAuthConstants.CAPTCHA_ERROR);
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
