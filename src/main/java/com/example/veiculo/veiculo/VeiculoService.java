package com.example.veiculo.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private AcessorioRepository acessorioRepository;

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo salvar(Veiculo veiculo) {
        if (veiculo.getAcessorio() != null && veiculo.getAcessorio().getId() != null) {
            Acessorio acessorio = acessorioRepository.findById(veiculo.getAcessorio().getId())
                    .orElseThrow(() -> new RuntimeException("Acessorio nao encontrado"));
            veiculo.setAcessorio(acessorio);
        }
        return veiculoRepository.save(veiculo);
    }

    public void deletar(Long id) {
        veiculoRepository.deleteById(id);
    }

    public Veiculo atualizarVeiculo(Long id, Veiculo veiculo) {
        Veiculo veiculoExistente = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veiculo nao encontrado com o id: " + id));

        veiculoExistente.setModelo(veiculo.getModelo());
        veiculoExistente.setAnoFabricacao(veiculo.getAnoFabricacao());
        veiculoExistente.setPlaca(veiculo.getPlaca());

        if (veiculo.getAcessorio() != null && veiculo.getAcessorio().getId() != null) {
            Acessorio acessorio = acessorioRepository.findById(veiculo.getAcessorio().getId())
                    .orElseThrow(() -> new RuntimeException("Acessorio nao encontrado"));
            veiculoExistente.setAcessorio(acessorio);
        }

        return veiculoRepository.save(veiculoExistente);
    }
}

