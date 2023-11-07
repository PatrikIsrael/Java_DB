
package br.com.atividade03maven.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author patri
 */
public class UsuarioDB {
    public static Usuario validarUsuarioSeguro(Usuario usuario) {
        String sql = "SELECT * FROM usuario WHERE login = ? AND senha = ?";
        Usuario usuarioEncontrado = null;

        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setId(rs.getInt("id"));
                    usuarioEncontrado.setNome(rs.getString("nome"));
                    usuarioEncontrado.setLogin(rs.getString("login"));
                    usuarioEncontrado.setSenha(rs.getString("senha"));
                    usuarioEncontrado.setTipo(rs.getString("tipo"));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao validar usuário", ex);
        }

        return usuarioEncontrado;
    }


    public static Usuario validarUsuarioInseguro(Usuario usuario) {
        String sql = "SELECT * FROM usuario WHERE login =? AND senha =?";
        Usuario usuarioEncontrado = null;

        try (Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "");
             PreparedStatement statement = conexao.prepareStatement(sql)) {

            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    usuarioEncontrado = new Usuario();
                    usuarioEncontrado.setId(rs.getInt("id"));
                    usuarioEncontrado.setNome(rs.getString("nome"));
                    usuarioEncontrado.setLogin(rs.getString("login"));
                    usuarioEncontrado.setSenha(rs.getString("senha"));
                    usuarioEncontrado.setTipo(rs.getString("tipo"));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao validar usuário inseguro", ex);
        }

        return usuarioEncontrado;
    }
}


