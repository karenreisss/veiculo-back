package com.example.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acessorio")
public class AcessorioController {

    @Autowired
    private AcessorioService acessorioService;

    @GetMapping
    public List<Acessorio> listarTodos() {
        return acessorioService.listarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Acessorio salvar(@RequestBody Acessorio acessorio) {
        return acessorioService.salvar(acessorio);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acessorio> buscarPorId(@PathVariable Long id) {
        try {
            Acessorio acessorio = acessorioService.buscarPorId(id);
            return ResponseEntity.ok(acessorio);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Acessorio> atualizar(@PathVariable Long id, @RequestBody Acessorio acessorio) {
        try {
            Acessorio acessorioAtualizado = acessorioService.atualizar(id, acessorio);
            return ResponseEntity.ok(acessorioAtualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            acessorioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
