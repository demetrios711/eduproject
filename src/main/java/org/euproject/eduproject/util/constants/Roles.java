package org.euproject.eduproject.util.constants;

public enum Roles {
    
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN"), MODERATOR("ROLE_MODERATOR");

    private String role;

    private Roles(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
