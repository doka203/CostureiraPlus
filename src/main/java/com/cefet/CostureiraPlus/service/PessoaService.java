package com.cefet.CostureiraPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.CostureiraPlus.dto.PessoaDTO;
import com.cefet.CostureiraPlus.entities.Pessoa;
import com.cefet.CostureiraPlus.repositories.PessoaRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // Buscar todos
    public List<PessoaDTO> findAll() {
        List<Pessoa> listaPessoa = pessoaRepository.findAll();
        return listaPessoa.stream().map(PessoaDTO::new).toList();
    }

    // Buscar por ID
    public PessoaDTO findById(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com ID: " + id + " não encontrada."));
        return new PessoaDTO(pessoa);
    }

    // Inserir Pessoa
    public PessoaDTO insert(PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setEndereco(dto.getEndereco());
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setEmail(dto.getEmail());
        Pessoa pessoaSalvo = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaSalvo);
    }

    // Atualizar Pessoa por ID
    public PessoaDTO update(Long id, PessoaDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com ID: " + id + " não encontrada."));
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setEndereco(dto.getEndereco());
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setEmail(dto.getEmail());
        Pessoa pessoaAtualizado = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaAtualizado);
    }

    // Remover por ID
    public void delete(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new EntityNotFoundException("Pessoa com ID: " + id + " não encontrada.");
        }
        pessoaRepository.deleteById(id);
    }

    // Buscar por email
    public PessoaDTO findByEmail(String email) {
        Pessoa pessoa = pessoaRepository.findByEmail(email)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa com email: " + email + " não encontrado."));
        return new PessoaDTO(pessoa);
    }

    // Atualizar Pessoa por email
    public PessoaDTO updateByEmail(String email, PessoaDTO dto) {
        Pessoa pessoa = pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com email: " + email + " não encontrada."));
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setEndereco(dto.getEndereco());
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setEmail(dto.getEmail());
        Pessoa pessoaAtualizado = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaAtualizado);
    }

    // Remover por email
    public void deleteByEmail(String email) {
        pessoaRepository.deleteByEmail(email);
    }

    // Buscar por cpf
    public PessoaDTO findByCpf(String cpf) {
        Pessoa pessoa = pessoaRepository.findByCpf(cpf)
        .orElseThrow(() -> new EntityNotFoundException("Pessoa com CPF: " + cpf + " não encontrado."));
        return new PessoaDTO(pessoa);
    }

    // Atualizar Pessoa por cpf
    public PessoaDTO updateByCpf(String cpf, PessoaDTO dto) {
        Pessoa pessoa = pessoaRepository.findByCpf(cpf)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa com CPF: " + cpf + " não encontrada."));
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setEndereco(dto.getEndereco());
        pessoa.setTelefone(dto.getTelefone());
        pessoa.setEmail(dto.getEmail());
        Pessoa pessoaAtualizado = pessoaRepository.save(pessoa);
        return new PessoaDTO(pessoaAtualizado);
    }

    // Remover por cpf
    public void deleteByCpf(String cpf) {
        pessoaRepository.deleteByCpf(cpf);
    }
}
