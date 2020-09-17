package com.example.RaktarkezeloProgram.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.RaktarkezeloProgram.Repository.ItalRepository;
import com.example.RaktarkezeloProgram.domain.Ital;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItalService {

	private final ItalRepository italRepository;
	
	public List<Ital> getAllItal(){
		return italRepository.findAll();
	}
	public Ital getItalById(Long id) {
		Optional<Ital> ital = italRepository.findById(id);
		if(ital.isPresent()) {
			return ital.get();
		}
		else {
			throw new IllegalArgumentException("not found {ital} by this id");
		}
	}
	public Ital createOrUpdateItal(Ital entity) {
		if(entity.getId() == null) {
			return italRepository.save(entity);
		}
		else {
			Optional<Ital> ital = italRepository.findById(entity.getId());
			if(ital.isPresent()) {
				Ital newItal = ital.get();
				newItal.setAlkoholTartalom(entity.getAlkoholTartalom());
				newItal.setCikkSzam(entity.getCikkSzam());
				//newItal.setDarabSzam(entity.getDarabSzam());
				newItal.setDarabSzamMinimum(entity.getDarabSzamMinimum());
				newItal.setKiszerelesLiter(entity.getKiszerelesLiter());
				newItal.setNev(entity.getNev());
				newItal.setTermekAra(entity.getTermekAra());
				italRepository.save(newItal);
				return newItal;
			}
			else {
				entity.setId(null);
				entity = italRepository.save(entity);
				return entity;
			}
		}
	}

		
	
	public void deleteItalById(Long id) {
		Optional<Ital> ital = italRepository.findById(id);
		if(ital.isPresent()) {
			italRepository.deleteById(id);
		}else {
			throw new IllegalArgumentException("delete failed Ital not found");
		}
	}
	public void editDarabszamItal(Ital entity) {
		Optional<Ital> ital = italRepository.findById(entity.getId());
		if(ital.isPresent()) {
			Ital newItal = ital.get();
			newItal.setDarabSzam(entity.getDarabSzam()+newItal.getDarabSzam());
			italRepository.save(newItal);
		}
		
	}
}
