package it.ashyzan.pizzeria.service;

import java.util.Optional;

import it.ashyzan.pizzeria.model.PizzaModel;

// qui si mette la business logic. Il pizzaservice è un'interfaccia 
// che si posiziona fra il controller (che quindi diventa una view, accetta i parametri e li valida,
// passa i dati al servizio, recepisce  la risposta e comporre la risposta del servizio)
// e interagisce con il repository , qui vengono messe le istruzioni di logica

public interface PizzaService {

    public Optional<PizzaModel> findById(Integer id);
    
//    salvo la pizza salvata come argomento e 
//    restituisco l'elemento appena salvato, 
//    mi serve epr ricavare l'id
    public PizzaModel save(PizzaModel pizza);

}
