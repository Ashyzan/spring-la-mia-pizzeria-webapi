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


    // dico  cosa fare nel caso di post api
    // ad esempio non posso salvare due pizze con lo stesso nome
    // quindi vedo se c'è e decido se restituire errore o se 
    // aggiungere un'altra copia ad esempio.
    @Override
    public PizzaModel save(PizzaModel pizza) {


	return pizzarepository.save(pizza);
    }
    
    @Override
    public PizzaModel update(Integer id, PizzaModel inputpizza){
	Optional<PizzaModel> pizzacercata = pizzarepository.findById(id);
	
	if(pizzacercata.isEmpty()) {
	    throw new IllegalArgumentException
	    ("La Pizza cercata con id " +  id + " non esiste");
	}
	
	PizzaModel pizza1 = pizzacercata.get();
	
	pizza1.setNome(inputpizza.getNome());
	pizza1.setDescrizione(inputpizza.getDescrizione());
	pizza1.setFotourl(inputpizza.getFotourl());
	pizza1.setIngredienti(inputpizza.getIngredienti());
	pizza1.setListaIngredienti(inputpizza.getListaIngredienti());
	
	
	return pizzarepository.save(pizza1);
    }


    @Override
    public void delete(Integer id) {
	pizzarepository.deleteById(id);
	
    }
    
   
    

}
