package it.ashyzan.pizzeria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.ashyzan.pizzeria.model.PizzaModel;
import it.ashyzan.pizzeria.service.PizzaService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/api/pizze")
public class PizzaRestController {
    
    @Autowired
    private PizzaService pizzaService;
    
    @GetMapping("/{id}")
    public ResponseEntity<PizzaModel> get(@PathVariable("id") Integer idPizza){
	
	Optional<PizzaModel> pizza = pizzaService.findById(idPizza);
	
	if(pizza.isPresent()) {
	    
	    return new ResponseEntity<PizzaModel>(pizza.get(), HttpStatus.OK);
	}
	else {
	    return new ResponseEntity<PizzaModel>(HttpStatus.NOT_FOUND);
	    
	}
    }
    
    
    @PostMapping
	public ResponseEntity store(
			@Valid @RequestBody PizzaModel pizza) {
		try {
		    PizzaModel pizzasalvata = pizzaService.save(pizza);
			return ResponseEntity.ok(pizzasalvata);
		} catch(Exception e) {
			return new ResponseEntity<>("Errore nel salvataggio del libro", 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
//  @GetMapping
//  public List<PizzaModel> pizzaindex() {
//	 return pizzaService.findAll();
//
//  }
  
//  @GetMapping("/{id}")
//  public PizzaModel get(@PathVariable Integer PizzaId) {
//	return pizzaService.findById(PizzaId).get();
//  
//  }
    
}
