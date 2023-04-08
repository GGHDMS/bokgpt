package kr.ac.bokgpt.welfareapi;

import org.springframework.stereotype.Component;

@Component
public interface Client<T,E> {
    T search(E req);
}
