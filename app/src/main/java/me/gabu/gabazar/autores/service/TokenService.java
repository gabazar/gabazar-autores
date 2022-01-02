
package me.gabu.gabazar.autores.service;

public interface TokenService {

    public void validaToken(String token);
    public String recuperarUsuario(String token);
}
