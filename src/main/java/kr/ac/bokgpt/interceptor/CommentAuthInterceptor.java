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

import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommentAuthInterceptor implements HandlerInterceptor {
    private final CommentRepository commentRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String httpMethod = request.getMethod();
        if(httpMethod.equals("POST") || httpMethod.equals("DELETE") || httpMethod.equals("PUT")){
            String curruntEmail = SecurityUtil.getCurrentEmail().orElseThrow(EmailNotFoundException::new);
            Map<?,?> pathVariables= (Map<?,?>)request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            Long commentId = Long.parseLong((String)pathVariables.get("commentId"));
            Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
            return curruntEmail.equals(comment.getCreatedBy());
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
