package br.com.atividade03maven.classes;

/**
 *
 * @author patri
 */
public class Permissoes {

    public static boolean podeCadastrarPodcast(String tipoUsuario) {
        return tipoUsuario.equals("Administrador") || tipoUsuario.equals("Desenvolvedor");
    }

    public static boolean podeExcluirPodcast(String tipoUsuario) {
        return tipoUsuario.equals("Administrador");
    }

    public static boolean podeListarPodcasts(String tipoUsuario) {
        return true; // Todos os tipos de usuários podem listar podcasts
    }

     public static String getTipoUsuarioLogado() {
        Usuario usuario = Autenticacao.getUsuarioLogado();
        if (usuario != null) {
            return usuario.getTipo();
        } else {
            return null; 
        }
    }

}
