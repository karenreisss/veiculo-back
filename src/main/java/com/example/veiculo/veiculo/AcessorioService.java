package com.example.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcessorioService {

    @Autowired
    private AcessorioRepository acessorioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Acessorio> listarTodos() {
        return acessorioRepository.findAll();
    }

    public Acessorio salvar(Acessorio acessorio) {
        return acessorioRepository.save(acessorio);
    }

    public Acessorio buscarPorId(Long id) {
        return acessorioRepository.findById(id).orElseThrow(() -> new RuntimeException("Acessório não encontrado com o id: " + id));
    }

    public void deletar(Long id) {
        // Verifica se o acessório está associado a algum veículo
        if (veiculoRepository.existsByAcessorioId(id)) {
            throw new RuntimeException("Não é possível excluir o acessório. Ele está associado a um ou mais veículos.");
        }
        acessorioRepository.deleteById(id);
    }

    public Acessorio atualizar(Long id, Acessorio acessorio) {
        Acessorio acessorioExistente = acessorioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Acessório não encontrado com o id: " + id));
        acessorioExistente.setNome(acessorio.getNome());
        return acessorioRepository.save(acessorioExistente);
    }
}
