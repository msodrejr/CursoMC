package com.mauriciosodre.cursomc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mauriciosodre.cursomc.domain.Cidade;
import com.mauriciosodre.cursomc.repositories.CidadeRepository;

@Service
public class CidadeService {
	
	@Autowired
	private CidadeRepository repo;
	
	public List<Cidade> findByEstado(Integer estado_Id){
		return repo.findCidades(estado_Id);
	}

}
