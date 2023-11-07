package br.com.atividade02maven.classes;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmesDao {

    private final EntityManagerFactory emf;
    private final EntityManager em;

    public FilmesDao() {
        emf = Persistence.createEntityManagerFactory("atividade02mavenPU");
        em = emf.createEntityManager();
    }

    public boolean conectar() {
        try {
            em.getTransaction().begin();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void desconectar() {
        em.close();
        emf.close();
    }

    public int salvar(Filmes filme) {
        try {
            em.getTransaction().begin();
            em.persist(filme);
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        }
    }

    public int atualizar(Filmes filme) {
        try {
            em.getTransaction().begin();
            em.merge(filme);
            em.getTransaction().commit();
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            return 0;
        }
    }

    public List<Filmes> listar() {
        try {
            Query query = em.createQuery("SELECT f FROM Filmes f");
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

public List<Filmes> buscarPorFiltros(String nome, String dataLancamento, String categoria) {
    try {
        String jpql = "SELECT f FROM Filmes f WHERE 1=1";
        Map<String, Object> parametros = new HashMap<>();

        if (nome != null && !nome.isEmpty()) {
            jpql += " AND f.nome LIKE :nome";
            parametros.put("nome", "%" + nome + "%");
        }

        if (dataLancamento != null && !dataLancamento.isEmpty()) {
          
            jpql += " AND f.dataLancamento = :dataLancamento";
            parametros.put("dataLancamento", dataLancamento);
        }

        if (categoria != null && !categoria.isEmpty()) {
            jpql += " AND f.categoria = :categoria";
            parametros.put("categoria", categoria);
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



 public void excluirPorNome(String nome) {
    EntityManager em = JPAUtil.getEntityManager();
    try {
        List<Filmes> filmes = em.createQuery("SELECT f FROM Filmes f WHERE f.nome = :nome", Filmes.class)
                .setParameter("nome", nome)
                .getResultList();

        if (!filmes.isEmpty()) {
            Filmes filme = filmes.get(0);
            em.getTransaction().begin();
            em.remove(filme);
            em.getTransaction().commit();
        }
    } catch (Exception e) {
        em.getTransaction().rollback();
        throw e;
    } finally {
        JPAUtil.closeEntityManager();
    }
}

}
