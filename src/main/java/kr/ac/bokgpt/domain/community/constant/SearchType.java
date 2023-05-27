package kr.ac.bokgpt.domain.community.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SearchType {

    TITLE("제목"),
    CONTENT("본문 내용"),
    EMAIL("유저 EMAIL"),
    NAME("유저 NAME");

    private final String description;

}
