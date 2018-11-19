package com.example.restservice;

import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication
public class RestserviceApplication {
    
    @Component
    class Estado {
        
        private Long id;
        private String nome;

        Estado() {}

        Estado(Long id, String nome) {
            this.id = id;
            this.nome = nome;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        @Override
        public String toString() {
            return "Estado{" + "id=" + id + ", nome=" + nome + '}';
        }
    }
    
    @Component
    class Cidade{
        
        private Long id;
        private String nome;
        private Estado estado;

        Cidade() {}

        Cidade(Long id, String nome, Estado estado) {
            this.id = id;
            this.nome = nome;
            this.estado = estado;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public Estado getEstado() {
            return estado;
        }

        public void setEstado(Estado estado) {
            this.estado = estado;
        }

        @Override
        public String toString() {
            return "Cidade{" + "id=" + id + ", nome=" + nome + ", estado=" + estado + '}';
        }
        
    }
   
    @Component
    class CidadeDAO{
        
        private List<Cidade> listaCidades = new ArrayList<>();
        
        public Cidade criar(Cidade cidade){
            return cidade;
        }
        
        public List<Cidade> inserir(){
            
            Cidade cid = new Cidade();
            Estado est = new Estado();
            
            est.setId(new Long(5));
            est.setNome("São Paulo");
            cid.setId(new Long(10));
            cid.setNome("Santos");
            cid.setEstado(est);
            
            listaCidades.add(cid);
            
            return listaCidades;
        }
        
        public Cidade alterar (Cidade cidade){
            return cidade;
        }
        
        public boolean excluir (Cidade cidade){
            return true;
        }
  
    }
    
    @Controller
    class RestController{
        
        private CidadeDAO cidadeDAO;
        
        RestController(CidadeDAO ciadadeDAO){
            this.cidadeDAO = cidadeDAO;
        }
        
        @PostMapping("/cidade")
	public Cidade criar(@RequestBody Cidade cidade){
            return cidade;
	}

	@GetMapping("/cidade")
	public List<Cidade> inserir(){
            return cidadeDAO.inserir();
	}

	@PutMapping("/cidade")
	public Cidade alterar(@RequestBody Cidade cidade){
            return cidade;
	}

	@DeleteMapping("/cidade")
	public int excluir(@PathVariable int id){
            return 200;
	}
        
    }

    public static void main(String[] args) {
	SpringApplication.run(RestserviceApplication.class, args);
    }
}