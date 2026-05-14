package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.*;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@Transactional( propagation = Propagation.REQUIRED)
public class ProntuarioServiceImp implements ProntuarioService{
    private final ProntuarioRepository prontuarioRepository;
    private final MedicoRepository medicoRepository;
    private final HistoricoRepository historicoRepository;
    private final ExameRepository exameRepository;
    private final ReceitaRepository receitaRepository;

    @Override
    public Prontuario create(Prontuario prontuario) {
        UUID medicoId = prontuario.getMedico().getId();
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow();

        UUID historicoId = prontuario.getHistorico().getId();
        Historico historico = historicoRepository.findById(historicoId)
                .orElseThrow();


        Set<Exame> exames = prontuario.getExames()
                        .stream()
                        .map(exame -> exameRepository.findById(exame.getId()).orElseThrow())
                        .collect(Collectors.toSet());


        Set<Receita> receitas = prontuario.getReceitas()
                        .stream()
                        .map(receita -> receitaRepository.findById(receita.getId()).orElseThrow())
                        .collect(Collectors.toSet());

        prontuario.setMedico(medico);
        prontuario.setHistorico(historico);
        prontuario.setExames(exames);
        prontuario.setReceitas(receitas);

        Prontuario savedProntuario = prontuarioRepository.save(prontuario);


        for (Exame exame : exames) {
            exame.setProntuario(savedProntuario);
            exameRepository.save(exame);
        }


        for (Receita receita : receitas) {
            receita.setProntuario(savedProntuario);
            receitaRepository.save(receita);
        }


        if(historico.getProntuarios() != null) {historico.getProntuarios().add(savedProntuario);
        } else {historico.setProntuarios(Set.of(savedProntuario));}

        historicoRepository.save(historico);


        savedProntuario.setExames(exames);
        savedProntuario.setReceitas(receitas);
        savedProntuario.setHistorico(historico);
        savedProntuario.setMedico(medico);

        return savedProntuario;
    }

    @Override
    public Optional<Prontuario> update(UUID id, Prontuario patch) {
        return prontuarioRepository.findById(id)
                .map(existing -> {

                    if (patch.getDescricao() != null)
                        existing.setDescricao(patch.getDescricao());

                    return prontuarioRepository.save(existing);
                });
    }

    @Override
    public Page<Prontuario> fetchAll(Pageable pageable) {
        return prontuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Prontuario> fetchById(UUID id) {
        return prontuarioRepository.findById(id);
    }

    @Override
    public boolean existsById(UUID id){
        return prontuarioRepository.existsById(id);
    }

    @Override
    public void delete(UUID id){

        Prontuario pront = prontuarioRepository.findById(id).orElseThrow();

        if(pront.getExames() != null){
            pront.getExames().forEach(ex -> ex.setProntuario(null));
            exameRepository.saveAll(pront.getExames());
        }

        if(pront.getReceitas() != null){
            pront.getReceitas().forEach(rec -> rec.setProntuario(null));
            receitaRepository.saveAll(pront.getReceitas());
        }

        if(pront.getHistorico() != null){
            pront.getHistorico().getProntuarios().remove(pront);
            historicoRepository.save(pront.getHistorico());
        }

        if(pront.getMedico() != null){
            pront.getMedico().getProntuarios().remove(pront);
            medicoRepository.save(pront.getMedico());
        }

        prontuarioRepository.delete(pront);
    }

}
