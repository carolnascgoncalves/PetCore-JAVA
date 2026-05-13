package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Tutor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional( propagation = Propagation.REQUIRED)
public class MedicoServiceImp implements MedicoService{
    private final MedicoRepository medicoRepository;

    private final ExameRepository exameRepository;
    private final RelatorioRepository relatorioRepository;
    private final ProntuarioRepository prontuarioRepository;
    private final ReceitaRepository receitaRepository;

    @Override
    public Medico create(Medico medico){
        return this.medicoRepository.save(medico);
    }

    @Override
    public Optional<Medico> update(UUID id, Medico patch){
        return medicoRepository.findById(id)
                .map(existing -> {

                    if (patch.getEmail() != null)
                        existing.setEmail(patch.getEmail());

                    if (patch.getTelefone() != null)
                        existing.setTelefone(patch.getTelefone());

                    if (patch.getSenha() != null)
                        existing.setSenha(patch.getSenha());

                    if (patch.getUrlImg() != null)
                        existing.setUrlImg(patch.getUrlImg());

                    return medicoRepository.save(existing);
                });
    }

    @Override
    public void delete(UUID id) {
        Medico medico = medicoRepository.findById(id).orElseThrow();

        medico.getRelatorios().forEach(rel -> rel.setMedico(null));
        relatorioRepository.saveAll(medico.getRelatorios());

        medico.getProntuarios().forEach(pront -> pront.setMedico(null));
        prontuarioRepository.saveAll(medico.getProntuarios());

        medico.getExames().forEach(exame -> exame.setMedico(null));
        exameRepository.saveAll(medico.getExames());

        medico.getReceitas().forEach(rec -> rec.setMedico(null));
        receitaRepository.saveAll(medico.getReceitas());

        medicoRepository.delete(medico);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Medico> fetchByEmail(UUID id){
        return this.medicoRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.medicoRepository.existsById(id);
    }

    public Page<Medico> fetchAll(Pageable pageable){
        return this.medicoRepository.findAll(pageable);
    }

    @Override
    public Optional<Medico> fetchByEmail(String email, String senha){
        Optional<Medico> medico = medicoRepository.findByEmail(email);
        if(medico.isPresent() && medico.get().getSenha().equals(senha)){return medico;}

        return Optional.empty();
    }
}
