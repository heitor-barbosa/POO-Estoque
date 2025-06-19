package model;

public class Usuario {
	// Atributos
	private String nome;
	private String login;
	private String senha;
	private String nivelAcesso; 	// FUNCIONARIO ou GERENTE
	
	// Construtor
	public Usuario(String nome, String login, String senha, String nivelAcesso) {
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.nivelAcesso = nivelAcesso;
	}
	public Usuario() {
		
	}
	
	// Getter e Setter
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getNivelAcesso() {
		return nivelAcesso;
	}
	public void setNivelAcesso(String nivelAcesso) {
		this.nivelAcesso = nivelAcesso;
	}
	
	
	// Metodos
	
	
}
