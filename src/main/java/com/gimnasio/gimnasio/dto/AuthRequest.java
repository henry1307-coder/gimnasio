package com.gimnasio.gimnasio.dto;

public class AuthRequest {
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private String rol;

    
    public String getNombre()   { return nombre; }
    public void setNombre(String n) { this.nombre = n; }
    public String getApellido() { return apellido; }
    public void setApellido(String a) { this.apellido = a; }
    public String getEmail()    { return email; }
    public void setEmail(String e) { this.email = e; }
    public String getPassword() { return password; }
    public void setPassword(String p) { this.password = p; }
    public String getRol()      { return rol; }
    public void setRol(String r) { this.rol = r; }
}