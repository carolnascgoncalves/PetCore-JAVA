package br.com.fiap.javaadv.blog.backend.services;

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

    @Override
    public Pet create(Pet pet) {
        return this.petRepository.save(pet);
    }

    @Override
    public Optional<Pet> updateStatus(UUID id, StatusEnum status) {
        if( this.petRepository.existsById( id ) ){
            Optional<Pet> pet = petRepository.findById(id);
            pet.get().setStatus(status);

            return Optional.of(petRepository.save(pet.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Pet> update(UUID id, Pet pet) {
        if( this.petRepository.existsById( id ) ){
            pet.setId(id);
            return Optional.of(petRepository.save(pet));
        }
        return Optional.empty();
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
        petRepository.deleteById(id);
    }
}
