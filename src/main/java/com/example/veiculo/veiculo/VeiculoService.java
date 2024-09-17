package com.example.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void deletar(Long id) {
        veiculoRepository.deleteById(id);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
        Veiculo veiculoExistente = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veiculo não encontrado com o id: " + id));

        veiculoExistente.setModelo(veiculo.getModelo());
        veiculoExistente.setAnoFabricacao(veiculo.getAnoFabricacao());
        veiculoExistente.setPlaca(veiculo.getPlaca());

        return veiculoRepository.save(veiculoExistente);
    }
}
