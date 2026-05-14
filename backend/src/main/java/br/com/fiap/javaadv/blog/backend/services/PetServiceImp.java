package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.HistoricoRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.PetRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Pet;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.StatusEnum;
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
public class PetServiceImp implements PetService {
    private final PetRepository petRepository;
    private final HistoricoRepository historicoRepository;

    @Override
    public Pet create(Pet pet) {
        return this.petRepository.save(pet);
    }

    @Override
    public Optional<Pet> updateStatus(UUID id, Pet patch) {
        return petRepository.findById(id)
                .map(existing -> {

                    if (patch.getStatus() != null)
                        existing.setStatus(patch.getStatus());

                    return petRepository.save(existing);
                });
    }

    @Override
    public Optional<Pet> updateImage(UUID id, Pet patch) {
        return petRepository.findById(id)
                .map(existing -> {

                    if (patch.getUrlImg() != null)
                        existing.setUrlImg(patch.getUrlImg());

                    return petRepository.save(existing);
                });
    }


    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Page<Pet> fetchAll(Pageable pageable){
        return this.petRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Page<Pet> fetchMenuAll(Pageable pageable){
        return this.petRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.NEVER)
    public Optional<Pet> fetchById(UUID id){
        return this.petRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id) {
        return this.petRepository.existsById(id);
    }

    @Override
    public void delete(UUID id) {
        Pet pet = petRepository.findById(id).orElseThrow();

        pet.getHistorico().setPet(null);
        historicoRepository.save(pet.getHistorico());

        petRepository.delete(pet);
    }
}
