package com.example.boardv1._core.errors;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.boardv1._core.errors.ex.Exception400;
import com.example.boardv1._core.errors.ex.Exception401;
import com.example.boardv1._core.errors.ex.Exception403;
import com.example.boardv1._core.errors.ex.Exception404;
import com.example.boardv1._core.errors.ex.Exception500;

@RestControllerAdvice // 모든 예외를 처리하는 클래스 (Data 응답)
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = Exception400.class) // 어떤 예외인지 지정하기 / 유효성 검사 실패 시
    public String ex400(Exception400 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
        return html;
    }

    @ExceptionHandler(exception = Exception401.class) // 어떤 예외인지 지정하기 / 인증 실패 시
    public String ex401(Exception401 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    location.href = '/login-form'
                </script>
                """, e.getMessage());
        return html;
    }

    @ExceptionHandler(exception = Exception403.class) // 어떤 예외인지 지정하기 / 권한 실패 시
    public String ex403(Exception403 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
        // log 남기기
        return html;
    }

    @ExceptionHandler(exception = Exception404.class) // 어떤 예외인지 지정하기 / 자원을 찾을 수 없다.
    public String ex404(Exception404 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
        return html;
    }

    @ExceptionHandler(exception = Exception500.class) // 어떤 예외인지 지정하기 / 서버 측 에러
    public String ex500(Exception500 e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, e.getMessage());
        return html;
    }

    @ExceptionHandler(exception = Exception.class) // 어떤 예외인지 지정하기
    public String exUnknown(Exception e) {
        String html = String.format("""
                <script>
                    alert('%s');
                    history.back();
                </script>
                """, "관리자에게 문의하세요");

        // 1. 로그
        // 2. sms 알림 -> e.getMessage()
        return html;
    }

}
