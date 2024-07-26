package it.ashyzan.pizzeria.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.ashyzan.pizzeria.model.PizzaModel;
import it.ashyzan.pizzeria.repository.PizzaRepository;

@Service
public class PizzaServiceImplementation implements PizzaService {

    
    @Autowired
    private PizzaRepository pizzarepository;
    

    @Override
    public Optional<PizzaModel> findById(Integer PizzaId) {
	return pizzarepository.findById(PizzaId);
    }

//    @Override
//    public Optional<PizzaModel> findAllPizze(List ListaPizze) {
//	return pizzarepository.findAll(ListaPizze);
//    }
//
//    @Override
//    public Optional<IngredientiModel> findAllIngredienti(List Listaingredienti) {
//	return ingredientirepository.findAll(Listaingredienti);
//    }

   
    

}
