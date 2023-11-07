
package br.com.atividade03maven.classes;

/**
 *
 * @author patri
 */
public class Autenticacao {
    private static Usuario usuarioLogado;

    public static void setUsuarioLogado(Usuario usuario) {
        usuarioLogado = usuario;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }
}

