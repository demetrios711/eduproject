package org.euproject.eduproject.util.constants;

public enum Privileges {

    RESET_ANY_USER_PASSWORD(1l, "RESET_ANY_USER_PASSWORD"),
    ACCESS_ADMIN_PANEL(2l,"ACCESS_ADMIN_PANEL");

    private long id;
    private String privilege;
    private Privileges(long id, String privilege){
        this.id = id;
        this.privilege = privilege;
    }
    public long getId(){
        return id;
    }
    public String getPrivilege(){
        return privilege;
    }
}
