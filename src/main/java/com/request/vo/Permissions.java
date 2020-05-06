package com.request.vo;

import lombok.Data;

@Data
public class Permissions {
    private String id;
    private String permissionsName;

    public Permissions(String s, String query) {
        this.id = s;
        this.permissionsName = query;
    }
}
