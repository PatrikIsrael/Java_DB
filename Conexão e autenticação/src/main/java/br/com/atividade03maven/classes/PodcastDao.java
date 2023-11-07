package br.com.atividade03maven.classes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Classe para interagir com o banco de dados de Podcast.
 *
 * @author patri
 */
public class PodcastDao {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    /**
     * Construtor da classe.
     */
    public PodcastDao() {
        emf = Persistence.createEntityManagerFactory("atividade03mavenPU");
        em = emf.createEntityManager();
    }

    /**
     * Método para conectar ao banco de dados.
     *
     * @return true se a conexão foi estabelecida, false caso contrário.
     */
    public boolean conectar() {
        try {
            em.getTransaction().begin();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Método para desconectar do banco de dados.
     */
    public void desconectar() {
        em.close();
        emf.close();
    }

    /**
     * Método para salvar um Podcast no banco de dados.
     *
     * @param podcast O Podcast a ser salvo.
     * @return 1 se a operação foi bem sucedida, 0 caso contrário.
     */
    public int salvar(Podcast podcast) {
        try {
            em.getTransaction().begin();
            em.persist(podcast);
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        }
    }

    /**
     * Método para atualizar um Podcast no banco de dados.
     *
     * @param podcast O Podcast a ser atualizado.
     * @return 1 se a operação foi bem sucedida, 0 caso contrário.
     */
    public int atualizar(Podcast podcast) {
        try {
            em.getTransaction().begin();
            em.merge(podcast);
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        }
    }

    /**
     * Método para listar todos os Podcasts no banco de dados.
     *
     * @return Lista de Podcasts, ou null em caso de erro.
     */
    public List<Podcast> listar() {
        try {
            Query query = em.createQuery("SELECT p FROM Podcast p");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Método para buscar Podcasts com filtros.
     *
     */
 
       public List<Podcast> buscarPorFiltros(String produtor, String nomeEpisodio, int numeroEpisodio) {
    try {
        String jpql = "SELECT p FROM Podcast p WHERE 1=1";
        Map<String, Object> parametros = new HashMap<>();

        if (produtor != null && !produtor.isEmpty()) {
            jpql += " AND p.produtor LIKE :produtor";
            parametros.put("produtor", "%" + produtor + "%");
        }

        if (nomeEpisodio != null && !nomeEpisodio.isEmpty()) {
            jpql += " AND p.nomeEpisodio LIKE :nomeEpisodio";
            parametros.put("nomeEpisodio", "%" + nomeEpisodio + "%");
        }

        if (numeroEpisodio > 0) {
            jpql += " AND p.numeroEpisodio = :numeroEpisodio";
            parametros.put("numeroEpisodio", numeroEpisodio);
        }

        Query query = em.createQuery(jpql);

        for (Map.Entry<String, Object> entry : parametros.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return query.getResultList();
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}

 

    /**
     * Método para excluir um Podcast pelo nome.
     *
     * @param nome Nome do Podcast a ser excluído.
     */
    public void excluirPorNome(String nome) {
        try {
            List<Podcast> podcasts = em.createQuery("SELECT p FROM Podcast p WHERE p.nome = :nome", Podcast.class)
                    .setParameter("nome", nome)
                    .getResultList();

            if (!podcasts.isEmpty()) {
                Podcast podcast = podcasts.get(0);
                em.getTransaction().begin();
                em.remove(podcast);
                em.getTransaction().commit();
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
