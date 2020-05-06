package com.request.vo;
import lombok.Data;

import java.util.Set;

@Data
public class Role {
    private String id;
    private String roleName;
    private Set<Permissions> permissions;

    public Role(String id, String roleName, Set<Permissions> permissions) {
        this.id = id;
        this.roleName = roleName;
        this.permissions = permissions;
    }
}
