package com.soms.user_service.util;

import com.soms.user_service.dto.UserContext;
import jakarta.servlet.http.HttpServletRequest;

public class HttpUtil {

    public static UserContext getUserContext(HttpServletRequest request) {
        return new UserContext(request.getHeader("X-User-Id"),
                request.getHeader("X-User-Role"),
                request.getHeader("X-User-Email"),
                request.getHeader("X-User-Username"));
    }
}
