package dev.youcode.cvtheque.response;

public class AuthResponse extends Response {
    private Long id;
    private String token;
    private Long resume_id;
    private String role;
    public AuthResponse() {
    }

    //    public AuthResponse(String token) {
//        this.token = token;
//    }
    public AuthResponse(String mssage) {
        super(mssage);
    }
    public AuthResponse(String message , Integer status){
        super(message, status);
    }

    public AuthResponse(String message, Integer status, String token, Long id, Long resume_id, String role) {
        super(message, status);
        this.token = token;
        this.id = id;
        this.resume_id = resume_id;
        this.role = role;
    }

    public AuthResponse(String message, String token, Long id, String role) {
        super(message);
        this.token = token;
        this.id = id;
        this.role = role;
    }
    public AuthResponse(String message, String token, Long id, Long resume_id, String role) {
        super(message);
        this.token = token;
        this.id = id;
        this.resume_id = resume_id;
        this.role = role;
    }

    public String getToken() {
        return token;
    }
    public Long getId(){ return  id; }

    public void setToken(String token) {
        this.token = token;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getResume_id() {
        return resume_id;
    }

    public void setResume_id(Long resume_id) {
        this.resume_id = resume_id;
    }

    public String getRole() {
        return role;
    }
}
