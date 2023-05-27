package kr.ac.bokgpt.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.bokgpt.domain.community.Comment;
import kr.ac.bokgpt.repository.community.CommentRepository;
import kr.ac.bokgpt.security.exception.CommentNotFoundException;
import kr.ac.bokgpt.security.exception.EmailNotFoundException;
import kr.ac.bokgpt.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommentAuthInterceptor implements HandlerInterceptor {
    private final CommentRepository commentRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String httpMethod = request.getMethod();

        if (isRestrictedMethod(httpMethod)) {
            String currentEmail = SecurityUtil.getCurrentEmail().orElseThrow(EmailNotFoundException::new);
            Long commentId = extractPostId(request);

            Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
            String commentWriter = comment.getCreatedBy();

            if (!commentWriter.equals(currentEmail)) {
                sendForbiddenResponse(response);
                return false;
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    private boolean isRestrictedMethod(String httpMethod) {
        return httpMethod.equals("POST") || httpMethod.equals("DELETE") || httpMethod.equals("PUT");
    }

    private Long extractPostId(HttpServletRequest request) {
        Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        String postIdString = (String) pathVariables.get("commentId");
        return Long.parseLong(postIdString);
    }

    private void sendForbiddenResponse(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().println("NOT AUTHORIZE!!");
    }
}
