package br.com.vainaweb.escolat2.model;

import org.hibernate.validator.constraints.br.CPF;

import br.com.vainaweb.escolat2.dto.DadosAtualizados;
import br.com.vainaweb.escolat2.dto.EnderecoDTO;
import br.com.vainaweb.escolat2.enums.Cargo;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // Trata a classe como uma entidade
@Table(name = "tb_colaboradores") // Defino o nome da minha tabela
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ColaboradorModel {

	@Id // - Determina a chave primária
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Transforma nosso ID em um auto_increment
	private Long id;
	private String nome;

	@Email
	@Column(unique = true) // Adicionando constraint UNIQUE
	private String email;

	@CPF
	@Column(unique = true) // Adicionando constraint UNIQUE
	private String cpf;

	private Cargo cargo;

	@Embedded // Diz que o atributo é incorporável
	private Endereco endereco;

	// |-----------------------------------------------------Construtor-------------------------------------------------|

	public ColaboradorModel(ColaboradorModel dados) {
		this.nome = dados.nome;
		this.email = dados.email;
		this.cpf = dados.cpf;
		this.cargo = dados.cargo;
		this.endereco = new Endereco(dados.endereco().cep(), dados.endereco().logradouro(), dados.endereco().bairro(),
				dados.endereco().cidade(), dados.endereco().complemento(), dados.endereco().uf(),
				dados.endereco().numero());
	}

	public void atualizarInfo(DadosAtualizados dados) {
		this.nome = dados.nome() != null ? dados.nome() : this.nome;
		this.email = dados.email();
	}
}
