package it.ashyzan.pizzeria.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    
    @PutMapping("{id}")
    public ResponseEntity update(@PathVariable("id")
    Integer IdPizza, @Valid @RequestBody PizzaModel pizza){
	
	try {
	    PizzaModel pizzaUpdated = pizzaService.update(IdPizza, pizza);
		
		return ResponseEntity.ok(pizzaUpdated);
	} catch(IllegalArgumentException e) {
		return new ResponseEntity<>(e.getMessage(), 
				HttpStatus.NOT_FOUND);
	} catch(Exception e) {
		return new ResponseEntity<>("Errore nel salvataggio del libro", 
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
    }
    
 

	@DeleteMapping("{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Integer id) {
		try {
		    pizzaService.delete(id);
			return ResponseEntity.ok("Cancellazione effettuata");
		} catch(Exception e) {
			return new ResponseEntity<>("Errore nella cancellazione del libro", 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
}
