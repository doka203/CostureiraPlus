package com.cefet.CostureiraPlus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cefet.CostureiraPlus.dto.UsuarioDTO;
import com.cefet.CostureiraPlus.entities.Pessoa;
import com.cefet.CostureiraPlus.entities.Usuario;
import com.cefet.CostureiraPlus.repositories.PessoaRepository;
import com.cefet.CostureiraPlus.repositories.UsuarioRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PessoaRepository pessoaRepository;


    // Buscar todos
    public List<UsuarioDTO> findAll() {
        List<Usuario> listaUsuarios = usuarioRepository.findAll();
        return listaUsuarios.stream().map(UsuarioDTO::new).toList();
    }

    // Buscar por iD
    public UsuarioDTO findById(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com ID: " + id + " não encontrado."));
        return new UsuarioDTO(usuario);
    }

    // Inserir Usuario
    public UsuarioDTO insert(UsuarioDTO dto) {
        Pessoa pessoa = pessoaRepository.findByCpf(dto.getCpf());
        Usuario usuario = new Usuario();
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());
        usuario.setTipo(dto.getTipo());
        usuario.setPessoa(pessoa);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioSalvo);
    }

    // Atualizar Usuario
    public UsuarioDTO update(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario com ID: " + id + " não encontrado."));
        Pessoa pessoa = pessoaRepository.findByCpf(dto.getCpf());
        usuario.setLogin(dto.getLogin());
        usuario.setSenha(dto.getSenha());
        usuario.setTipo(dto.getTipo());
        usuario.setPessoa(pessoa);
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return new UsuarioDTO(usuarioAtualizado);
    }

    // Remover por iD
    public void delete(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrada com ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    // Lista as pessoas associadas ao usuario identificado pelo ID
    public List<UsuarioDTO> findUsuariosByPessoaId(Long pessoaId) {
        if (!pessoaRepository.existsById(pessoaId)) {
            throw new EntityNotFoundException("Pessoa não encontrada com o ID: " + pessoaId);
        }

        List<Usuario> usuarios = usuarioRepository.findByPessoaId(pessoaId);
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }
}
