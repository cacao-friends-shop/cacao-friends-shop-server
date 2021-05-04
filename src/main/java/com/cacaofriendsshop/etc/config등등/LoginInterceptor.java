package com.cacaofriendsshop.etc.config등등;

import com.cacaofriendsshop.etc.exception.NoPermissionException;
import com.cacaofriendsshop.member.service.LoginService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    private final LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
        Object handler)
        throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);

            if (loginCheck == null) {
                return true;
            }

            if (loginService.getLoginUser() == null) {
                throw new NoPermissionException();
            }

            if (loginCheck.authority() == MemberLevel.ADMIN) {
                if (loginService.getMemberLevel() != MemberLevel.ADMIN) {
                    throw new NoPermissionException();
                }
            }

        }
        return true;
    }
}
