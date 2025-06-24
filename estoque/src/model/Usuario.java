package model;

public class Usuario {
    /**
     * Nome completo do usuário.
     */
    private String nome;

    /**
     * Login do usuário.
     */
    private String login;

    /**
     * Senha do usuário.
     */
    private String senha;

    /**
     * Nível de acesso do usuário, por exemplo: "FUNCIONARIO" ou "GERENTE".
     */
    private String nivelAcesso;

    /**
     * Construtor que inicializa todos os atributos do usuário.
     * 
     * @param nome nome completo do usuário
     * @param login login do usuário
     * @param senha senha do usuário
     * @param nivelAcesso nível de acesso do usuário
     */
    public Usuario(String nome, String login, String senha, String nivelAcesso) {
        super();
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.nivelAcesso = nivelAcesso;
    }

    /**
     * Construtor padrão sem parâmetros.
     */
    public Usuario() {
    }

    /**
     * Retorna o nome do usuário.
     * 
     * @return nome do usuário
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do usuário.
     * 
     * @param nome novo nome do usuário
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o login do usuário.
     * 
     * @return login do usuário
     */
    public String getLogin() {
        return login;
    }

    /**
     * Define o login do usuário.
     * 
     * @param login novo login do usuário
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retorna a senha do usuário.
     * 
     * @return senha do usuário
     */
    public String getSenha() {
        return senha;
    }

    /**
     * Define a senha do usuário.
     * 
     * @param senha nova senha do usuário
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * Retorna o nível de acesso do usuário.
     * 
     * @return nível de acesso
     */
    public String getNivelAcesso() {
        return nivelAcesso;
    }

    /**
     * Define o nível de acesso do usuário.
     * 
     * @param nivelAcesso novo nível de acesso
     */
    public void setNivelAcesso(String nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    // Métodos adicionais podem ser implementados aqui

}
