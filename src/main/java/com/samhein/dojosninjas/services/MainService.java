package com.samhein.dojosninjas.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.samhein.dojosninjas.models.Dojo;
import com.samhein.dojosninjas.models.Ninja;
import com.samhein.dojosninjas.repositories.DojoRepository;
import com.samhein.dojosninjas.repositories.NinjaRepository;

@Service
public class MainService {

	@Autowired
	private DojoRepository dojoRepo;
	
	@Autowired
	private NinjaRepository ninjaRepo;
	
	// create dojo
	public Dojo saveDojo(Dojo dojo) {
		return dojoRepo.save(dojo);
	}
	// get all dojos
	public List<Dojo> allDojos(){
		return dojoRepo.findAll();
	}
	// create ninja
	public Ninja saveNinja(Ninja ninja) {
		return ninjaRepo.save(ninja);
	}
	// find one dojo
	public Dojo oneDojo(Long dojoId) {
		Optional<Dojo> optionalDojo = dojoRepo.findById(dojoId);
		if(optionalDojo.isPresent()) {
			return optionalDojo.get();
		} else {
			return null;
		}
	}
}
