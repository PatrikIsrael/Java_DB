
package Entities;


/**
 * Esta é a classe Filmes que representa um filme.
 */
public class Filmes {

    private String nome;
    private String data;
    private String categoria;

    /**
     * Obtém o nome do filme.
     * @return O nome do filme.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do filme.
     * @param nome O novo nome do filme.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém a data de lançamento do filme.
     * @return A data de lançamento do filme.
     */
    public String getData() {
        return data;
    }

    /**
     * Define a data de lançamento do filme.
     * @param data A nova data de lançamento do filme.
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Obtém a categoria do filme.
     * @return A categoria do filme.
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do filme.
     * @param categoria A nova categoria do filme.
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
