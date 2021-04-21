package com.example.notes.enums;

import lombok.Getter;

@Getter
public enum MangaStatusEnum implements CodeEnum {
    UP(0, "Available"),
    DOWN(1, "Unavailable")
    ;
    private Integer code;
    private String message;

    MangaStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getStatus(Integer code) {

        for(MangaStatusEnum statusEnum : MangaStatusEnum.values()) {
            if(statusEnum.getCode() == code) return statusEnum.getMessage();
        }
        return "";
    }

    public Integer getCode() {
        return code;
    }
}
