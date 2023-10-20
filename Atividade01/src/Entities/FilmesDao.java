package Entities;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FilmesDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cenaflix_db", "root", "");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }

    public int salvar(Filmes filmes) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO filmes (nome, datalancamento, categoria) VALUES (?, ?, ?)");
            st.setString(1, filmes.getNome());
            st.setString(2, filmes.getData());
            st.setString(3, filmes.getCategoria());

            status = st.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public Filmes consultar(String Nome) {

        try {
            Filmes filmes = new Filmes();
            st = conn.prepareStatement("SELECT * from funcionarios WHERE nome = ?");
            st.setString(1, Nome);
            rs = st.executeQuery();
            //verificar se a consulta encontrou o funcionário com a matrícula informada
            if (rs.next()) { // se encontrou o funcionário, vamos carregar os dados
                filmes.setNome(rs.getString("Nome"));
                filmes.setData(rs.getString("Data"));
                filmes.setCategoria(rs.getString("Categoria"));
                return filmes;
            } else {
                return null;
            }
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return null;
        }
    }

    public boolean excluir(String Nome) {
        try {
            st = conn.prepareStatement("DELETE FROM funcionarios WHERE nome = ?");
            st.setString(1, Nome);
            st.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }
    
      public int atualizar(Filmes filmes){
        int status;
        try {
            st = conn.prepareStatement("UPDATE funcionarios SET nome = ?, data = ?, categoria = ? where nome = ?");
            st.setString(1,filmes.getNome());
            st.setString(2,filmes.getData());
            st.setString(3, filmes.getCategoria());
            status = st.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println(ex.getErrorCode());
            return ex.getErrorCode();
        }
    }

    public void desconectar() {
        try {
            conn.close();
        } catch (SQLException ex) {
            //pode-se deixar vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }
}
