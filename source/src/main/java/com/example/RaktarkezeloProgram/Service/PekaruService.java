package com.example.RaktarkezeloProgram.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.RaktarkezeloProgram.Repository.PekaruRepository;
import com.example.RaktarkezeloProgram.domain.Pekaru;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PekaruService {

	private final PekaruRepository pekaruRepository;
	
	public List<Pekaru> getAllPekaru(){
		return pekaruRepository.findAll();
	}
	public Pekaru getPekaruById(Long id) {
		Optional<Pekaru> pekaru = pekaruRepository.findById(id);
		if(pekaru.isPresent()) {
			return pekaru.get();
		}else {
			throw new IllegalArgumentException("not found pekaru by this id: "+id);
		}
		
	}
	public Pekaru createOrUpdatePekaru(Pekaru entity) {
		if(entity.getId()==null) {
			return pekaruRepository.save(entity);
		}
		else {
			Optional<Pekaru> pekaru = pekaruRepository.findById(entity.getId());
			if(pekaru.isPresent()) {
				Pekaru newPekaru = pekaru.get();
				newPekaru.setCikkSzam(entity.getCikkSzam());
				//newPekaru.setDarabSzam(entity.getDarabSzam());
				newPekaru.setDarabSzamMinimum(entity.getDarabSzamMinimum());
				newPekaru.setNev(entity.getNev());
				newPekaru.setSulyGramm(entity.getSulyGramm());
				newPekaru.setTermekAra(entity.getTermekAra());
				pekaruRepository.save(newPekaru);
				return newPekaru;
				
			}
			else {
				entity.setId(null);
				entity = pekaruRepository.save(entity);
				return entity;
			}
		}
	}
	public void deletePekaruById(Long id) {
		Optional<Pekaru> pekaru = pekaruRepository.findById(id);
		if(pekaru.isPresent()) {
			pekaruRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("not found pekaru by given id: "+id);
		}
	}
	public void editDarabszamPekaru(Pekaru entity) {
		Optional<Pekaru> pekaru = pekaruRepository.findById(entity.getId());
		if(pekaru.isPresent()) {
			Pekaru newPekaru = pekaru.get();
			newPekaru.setDarabSzam(entity.getDarabSzam()+newPekaru.getDarabSzam());
			pekaruRepository.save(newPekaru);
		}
		
	}
}
