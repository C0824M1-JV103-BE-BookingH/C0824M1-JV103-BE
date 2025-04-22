package com.example.casestudy_g2_m4.aop;//package com.example.demo_sb.aop;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.*;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//@Aspect
//@Component
//public class ProductControllerAOP {
//    @Pointcut("execution(* com.example.demo_sb.controller.DemoController.*(..))")
//    public void pointcutShowProductList() {
//    }
//
//    @After("pointcutShowProductList()")
//    public void doAfter(JoinPoint joinPoint) {
//        System.out.println("doAfter: " + joinPoint.getSignature().getName());
//    }
//
//    @AfterThrowing(pointcut = "pointcutShowProductList()", throwing = "throwable")
//    public void doAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
//        LocalDateTime now = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//        String timestamp = now.format(formatter);
//        System.out.printf("""
//                        =====ERROR DETAILS=====
//                        Method name: %s
//                        Method type: %s
//                        Location: %d
//                        Time: %s
//                        """, joinPoint.getSignature().getName(),
//                throwable.getClass().getName(),
//                throwable.getStackTrace()[0].getLineNumber(),
//                timestamp);
//    }
//}
