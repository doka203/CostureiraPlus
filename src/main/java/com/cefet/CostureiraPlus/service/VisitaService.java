package com.cefet.CostureiraPlus.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.CostureiraPlus.dto.VisitaDTO;
import com.cefet.CostureiraPlus.entities.Usuario;
import com.cefet.CostureiraPlus.entities.Visita;
import com.cefet.CostureiraPlus.repositories.UsuarioRepository;
import com.cefet.CostureiraPlus.repositories.VisitaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Buscar todos
    public List<VisitaDTO> findAll() {
        List<Visita> listaVisitas = visitaRepository.findAll();
        return listaVisitas.stream().map(VisitaDTO::new).toList();
    }

    // Buscar por iD
    public VisitaDTO findById(long id) {
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visita com ID: " + id + " não encontrada."));
        return new VisitaDTO(visita);
    }

    // Inserir Visita
    public VisitaDTO insert(VisitaDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.getIdUsuarioCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente com ID: " + dto.getIdUsuarioCliente()
                        + " não encontrado"));
        Usuario costureira = usuarioRepository.findById(dto.getIdUsuarioCostureira())
                .orElseThrow(() -> new EntityNotFoundException("Costureira com ID: " + dto.getIdUsuarioCostureira()
                        + " não encontrada"));
        Visita visita = new Visita();
        visita.setData(dto.getData());
        visita.setHora(dto.getHora());
        visita.setDescricao(dto.getDescricao());
        visita.setUsuarioCliente(cliente);
        visita.setUsuarioCostureira(costureira);
        Visita visitaSalvo = visitaRepository.save(visita);
        return new VisitaDTO(visitaSalvo);
    }

    // Atualizar Visita
    public VisitaDTO update(Long id, VisitaDTO dto) {
        Usuario cliente = usuarioRepository.findById(dto.getIdUsuarioCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente com ID: " + dto.getIdUsuarioCliente()
                        + " não encontrado"));
        Usuario costureira = usuarioRepository.findById(dto.getIdUsuarioCostureira())
                .orElseThrow(() -> new EntityNotFoundException("Costureira com ID: " + dto.getIdUsuarioCostureira()
                        + " não encontrada"));
        Visita visita = visitaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Visita com ID: " + id + " não encontrada."));
        visita.setData(dto.getData());
        visita.setHora(dto.getHora());
        visita.setDescricao(dto.getDescricao());
        visita.setUsuarioCliente(cliente);
        visita.setUsuarioCostureira(costureira);
        Visita visitaAtualizada = visitaRepository.save(visita);
        return new VisitaDTO(visitaAtualizada);
    }

    // Remove por iD
    public void delete(Long id) {
        if (!visitaRepository.existsById(id)) {
            throw new EntityNotFoundException("Visita não encontrada com ID: " + id);
        }
        visitaRepository.deleteById(id);
    }

    public List<VisitaDTO> findVisitasByClienteId(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new EntityNotFoundException("Usuário não encontrado com o ID: " + usuarioId);
        }

        List<Visita> visitas = visitaRepository.findByUsuarioClienteId(usuarioId);
        return visitas.stream().map(VisitaDTO::new).toList();
    }

    public List<VisitaDTO> findByFiltros(LocalDate dataInicio, LocalDate dataFim, Long clienteId) {
        if (dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("As datas de início e fim são obrigatórias.");
        }
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("A data de início não pode ser posterior à data de fim.");
        }

        List<Visita> visitas = visitaRepository.findVisitasComFiltros(dataInicio, dataFim, clienteId);
        return visitas.stream().map(VisitaDTO::new).toList();
    }
}
