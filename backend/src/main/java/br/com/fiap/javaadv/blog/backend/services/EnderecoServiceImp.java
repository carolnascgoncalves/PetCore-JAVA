package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.EnderecoRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.MedicoRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Endereco;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class EnderecoServiceImp implements EnderecoService{
    private final EnderecoRepository enderecoRepository;

    @Override
    public Endereco create(Endereco endereco){
        return this.enderecoRepository.save(endereco);
    }

    @Override
    public Optional<Endereco> update(UUID id, Endereco endereco){
        if(this.enderecoRepository.existsById(id)){
            endereco.setId(id);
            this.enderecoRepository.save(endereco);

            return Optional.of(endereco);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Endereco endereco){
        enderecoRepository.delete(endereco);
    }

    @Override
    public void delete(UUID id){
        enderecoRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Endereco> fetchById(UUID id){
        return this.enderecoRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.enderecoRepository.existsById(id);
    }

    public Page<Endereco> fetchAll(Pageable pageable){
        return this.enderecoRepository.findAll(pageable);
    }

}
