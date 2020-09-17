package com.example.RaktarkezeloProgram.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.example.RaktarkezeloProgram.Repository.CsomagoltRepository;
import com.example.RaktarkezeloProgram.domain.Csomagolt;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CsomagoltService {
 private final CsomagoltRepository csomagoltRepository;


 	public List<Csomagolt> getAllCsomagolt() {
 		
 		return csomagoltRepository.findAll();
 	}
 	public Csomagolt getCsomagoltById(Long id) {
 		Optional<Csomagolt> csomagolt = csomagoltRepository.findById(id);
 		if(csomagolt.isPresent()) {
 			return csomagolt.get();
 			
 		}
 		else {
				throw new IllegalArgumentException("id not found!");
			}
 	}
 	//_-------------------------------------------
 	public Csomagolt createOrUpdateCsomagolt(Csomagolt entity) {
 		if(entity.getId() == null) {
 			return csomagoltRepository.save(entity);
 		}
 		else {
 			Optional<Csomagolt> csomagolt = csomagoltRepository.findById(entity.getId());
 			if(csomagolt.isPresent()) {
 				Csomagolt newCsomagolt = csomagolt.get();
 				newCsomagolt.setCikkSzam(entity.getCikkSzam());
 				//newCsomagolt.setDarabSzam(entity.getDarabSzam());
 				newCsomagolt.setDarabSzamMinimum(entity.getDarabSzamMinimum());
 				newCsomagolt.setNev(entity.getNev());
 				newCsomagolt.setSulyGramm(entity.getSulyGramm());
 				newCsomagolt.setTermekAra(entity.getTermekAra());
 				newCsomagolt.setIsSelected(false);
 				csomagoltRepository.save(newCsomagolt);
 				return newCsomagolt;
 			}else {
 				entity.setId(null);
 				  entity = csomagoltRepository.save(entity);
 		            return entity;
 			}
 		}
 	}
 	//------------------------------------------------------
 	public void editDarabszamCsomagolt(Csomagolt entity) {
			Optional<Csomagolt> csomagolt = csomagoltRepository.findById(entity.getId());
			if(csomagolt.isPresent()){
				Csomagolt newCsomagolt = csomagolt.get();
				newCsomagolt.setDarabSzam(entity.getDarabSzam()+newCsomagolt.getDarabSzam());
	            csomagoltRepository.save(newCsomagolt);
	        }
 	}
 	//-------------------------------------------
 	public void deleteCsomagoltById(Long id) {
 		Optional<Csomagolt> csomagolt = csomagoltRepository.findById(id);
 		if(csomagolt.isPresent()) {
 			csomagoltRepository.deleteById(id);
 		}else {
 			throw new IllegalArgumentException("not found csomagolt by this id!");
 		}
 		}
 	
	
}
