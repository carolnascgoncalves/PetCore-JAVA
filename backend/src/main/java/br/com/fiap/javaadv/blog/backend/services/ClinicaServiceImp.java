package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.ClinicaRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.EnderecoRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.MedicoRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.RelatorioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Clinica;
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
public class ClinicaServiceImp implements ClinicaService{
    private final ClinicaRepository clinicaRepository;
    private final RelatorioRepository relatorioRepository;
    private final EnderecoRepository enderecoRepository;

    @Override
    public Clinica create(Clinica clinica){
        return this.clinicaRepository.save(clinica);
    }

    @Override
    public Optional<Clinica> update(UUID id, Clinica patch){
        return clinicaRepository.findById(id)
                .map(existing -> {

                    if (patch.getNome() != null) {
                        existing.setNome(patch.getNome());
                    }

                    if (patch.getEndereco() != null) {

                        UUID enderecoId = patch.getEndereco().getId();

                        Endereco endereco = enderecoRepository.findById(enderecoId).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

                        if (endereco.getClinica() != null && !endereco.getClinica().getId().equals(existing.getId())) {
                            throw new RuntimeException("Esse endereço já está vinculado a outra clínica");
                        }

                        existing.setEndereco(endereco);
                    }

                    return clinicaRepository.save(existing);
                });
    }


    @Override
    public void delete(UUID id){
        Clinica cli = clinicaRepository.findById(id).orElseThrow();

        cli.getRelatorios().forEach(rel -> rel.setClinicas(null));
        relatorioRepository.saveAll(cli.getRelatorios());

        cli.getEndereco().setClinica(null);
        enderecoRepository.save(cli.getEndereco());

        clinicaRepository.delete(cli);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Clinica> fetchById(UUID id){
        return this.clinicaRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.clinicaRepository.existsById(id);
    }

    public Page<Clinica> fetchAll(Pageable pageable){
        return this.clinicaRepository.findAll(pageable);
    }

}
