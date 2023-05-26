package kr.ac.bokgpt.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.bokgpt.domain.community.Post;
import kr.ac.bokgpt.repository.community.PostRepository;
import kr.ac.bokgpt.security.exception.EmailNotFoundException;
import kr.ac.bokgpt.security.exception.PostNotFoundException;
import kr.ac.bokgpt.security.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PostAuthInterceptor implements HandlerInterceptor {

    private final PostRepository postRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String httpMethod = request.getMethod();

        if(httpMethod.equals("POST") || httpMethod.equals("DELETE") || httpMethod.equals("PUT")) {
            String curruntEmail = SecurityUtil.getCurrentEmail().orElseThrow(EmailNotFoundException::new);
            Map<?, ?> pathVariables = (Map<?, ?>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
            Long postId = Long.parseLong((String)pathVariables.get("postId"));

            Post post = postRepository.findById(postId).orElseThrow(PostNotFoundException::new);
            String postWriter = post.getCreatedBy();

            if(!postWriter.equals(curruntEmail)){
                response.getOutputStream().println("NOT AUTHORIZE!!");
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
}
