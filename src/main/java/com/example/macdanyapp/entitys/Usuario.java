package com.example.macdanyapp.entitys;

import java.util.Objects;

public class Usuario {
    private int idUsuario;
    private String nombreDeUsuario;
    private String password;
    private TipoDeUsuario tipoDeUsuario;


    public Usuario() {}

    public Usuario(int idUsuario, String nombreDeUsuario, String password, TipoDeUsuario tipoDeUsuario) {
        this.idUsuario = idUsuario;
        this.nombreDeUsuario = nombreDeUsuario;
        this.password = password;
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public Usuario(String nombreDeUsuario, String password, TipoDeUsuario tipoDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
        this.password = password;
        this.tipoDeUsuario = tipoDeUsuario;
    }


    public boolean equals(Usuario usuario) {
      if(this==usuario){
          return true;
        }
      if(usuario==null || getClass()!=usuario.getClass()){
          return false;
      }
      Usuario usuario1=(Usuario)usuario;
      return nombreDeUsuario.equals(usuario1.nombreDeUsuario)&&password.equals(usuario1.password);
    }


    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreDeUsuario() {
        return nombreDeUsuario;
    }

    public void setNombreDeUsuario(String nombreDeUsuario) {
        this.nombreDeUsuario = nombreDeUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TipoDeUsuario getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public void setTipoDeUsuario(TipoDeUsuario tipoDeUsuario) {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombreDeUsuario='" + nombreDeUsuario + '\'' +
                ", password='" + password + '\'' +
                ", tipoDeUsuario=" + tipoDeUsuario +
                '}';
    }
}
