package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.HistoricoRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.PetRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.ProntuarioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.RelatorioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Historico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Medico;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Prontuario;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class HistoricoServiceImp implements HistoricoService{
    private final HistoricoRepository historicoRepository;
    private final PetRepository petRepository;
    private final RelatorioRepository relatorioRepository;
    private final ProntuarioRepository prontuarioRepository;

    @Override
    public Historico create(Historico historico){
        UUID petId = historico.getPet().getId();

        Pet pet = petRepository.findById(petId).orElseThrow();
        Set<Prontuario> prontuarios =
                historico.getProntuarios()
                        .stream()
                        .map(prontuario -> prontuarioRepository.findById(prontuario.getId()).orElseThrow())
                        .collect(Collectors.toSet());


        historico.setPet(pet);
        historico.setProntuarios(prontuarios);

        Historico savedHistorico = historicoRepository.save(historico);

        pet.setHistorico(savedHistorico);
        petRepository.save(pet);

        for (Prontuario prontuario : prontuarios) {
            prontuario.setHistorico(savedHistorico);
        }

        savedHistorico.setPet(pet);
        savedHistorico.setProntuarios(prontuarios);

        return savedHistorico;
    }

    @Override
    public void delete(UUID id){
        Historico hist = historicoRepository.findById(id).orElseThrow();

        if(hist.getRelatorios() != null){
        hist.getRelatorios().forEach(rel -> rel.setHistorico(null));
        relatorioRepository.saveAll(hist.getRelatorios());
        }

        if(hist.getProntuarios() != null){
        hist.getProntuarios().forEach(pront -> pront.setHistorico(null));
        prontuarioRepository.saveAll(hist.getProntuarios());}


        if(hist.getPet() != null){
        hist.getPet().setHistorico(null);
        petRepository.save(hist.getPet());
        }


        historicoRepository.delete(hist);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Historico> fetchById(UUID id){
        return this.historicoRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.historicoRepository.existsById(id);
    }

    public Page<Historico> fetchAll(Pageable pageable){
        return this.historicoRepository.findAll(pageable);
    }

}
