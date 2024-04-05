package br.com.vainaweb.escolat2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.vainaweb.escolat2.dto.DadosAtualizados;
import br.com.vainaweb.escolat2.dto.DadosColaborador;
import br.com.vainaweb.escolat2.model.ColaboradorModel;
import br.com.vainaweb.escolat2.repository.ColaboradorRepository;
import br.com.vainaweb.escolat2.service.ColaboradorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/colaborador")
public class ColaboradorController {

	@Autowired
	private ColaboradorService service;
	
	@Autowired
	private ColaboradorRepository repository;
	
	@GetMapping
	public List<ColaboradorModel> encontrarTodosOsColaboradores(){
		return service.encontrar();
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ColaboradorModel> listarPorId(@PathVariable long id) {
		return repository.findById(id)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//http://localhost:8080/colaborador-teste/{id}
	
	@PostMapping
	public ResponseEntity<String> cadastrarColaborador(@RequestBody DadosColaborador dados){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dados));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> atualizar(@PathVariable long id,@RequestBody @Valid DadosAtualizados dados) {
		var colaborador = repository.getReferenceById(id);
		colaborador.atualizarInfo(dados);
		repository.save(colaborador);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable Long id) {
		var deletar = repository.deleteById(id);
		return "Deletado com sucesso";
	}
	
//	@PostMapping //Verbo HTTP POST
//	public String cadastrarColaborador(@RequestBody DadosColaborador dados) {
//		service.cadastrar(dados);
//		return "ok";
//	}
	
	
	
}
