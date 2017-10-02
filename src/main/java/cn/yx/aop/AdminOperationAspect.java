package cn.yx.aop;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.yx.config.handler.exception.UserLoginException;
import cn.yx.config.holder.AdminInfoHolder;
import cn.yx.model.Admin;
import cn.yx.service.AdminOperationService;

/**
 * @author yuxuanjiao
 * @date 2017年9月30日 下午5:08:25
 * @version 1.0
 */

@Component
@Aspect
public class AdminOperationAspect {

    @Resource
    private AdminOperationService adminOperationService;

    @Pointcut("@annotation(cn.yx.annotation.AdminOperation)")
    public void adminOperation() {
    }

    @Around("adminOperation()")
    public Object watchAdminOperation(ProceedingJoinPoint jp) {
        Object obj = null;
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        Admin admin = (Admin) httpServletRequest.getSession().getAttribute("admin");
        if (admin == null) {
            throw new UserLoginException();
        } else {
            AdminInfoHolder.setAdmin(admin);
        }

        try {
            obj = jp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        adminOperationService.process(jp);
        return obj;
    }
}
