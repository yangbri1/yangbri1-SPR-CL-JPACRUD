package Lab.Service;

import Lab.Model.Store;
import Lab.Repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class StoreService {
    StoreRepository storeRepository;
    @Autowired
    public StoreService(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }
    /**
     * TODO: given a transient store, persist the store and return it.
     * @param store a transient store
     * @return the persisted store
     */
    public Store persistStore(Store store){
        // create a new DB record for 'store' entity
        return storeRepository.save(store);
    }
    /**
     * TODO: get all store entities
     * @return all store entities
     */
    public List<Store> getAllStores(){
        /* call upon .findAll() method to return all 'store' entities/records stored in the DB table
         as a List container of 'Store' elements */
        return storeRepository.findAll();
    }
    /**
     * TODO: given an id of a store, return the store.
     *
     * @param id id of store entity
     * @return a store entity
     */
    public Store getStoreById(long id){
        // initialize an Optional container obj w/ respective entity retrieved from 'Store' DB table using .findById() method from Spring framework
        Optional<Store> optionalStore = storeRepository.findById(id);
        // check to see if freshly made 'optionalStore' Optional container is not null/falsy value via .isPresent() method from 'java.util.Optional' package
        // aka if the entity tied to given parameter 'id' exists ... 
        if(optionalStore.isPresent() == true){
            // call built-in .get() method from 'Optional' package to retrieve the entity/record
            return optionalStore.get();
        }
        // otw if the entity DNE ... return default value 'null'
        return null;
    }
    /**
     * TODO: given an id of an existing store, delete the store
     */
    public void deleteStore(long id){
        // invoke built-in .deleteById() method from Spring framework's CrudRepository to do so
        storeRepository.deleteById(id);     // notice: no 'return' statement here as method is 'void' type -- no return!
    }
    /**
     * TODO: given an id and some replacement data for a store, overwrite the data of an existing store,
     * and return the updated store.
     * @return the updated store entity
     */
    public Store updateStore(long id, Store replacement){
        // call .findById() method to retrieve corresponding 'Store' entity by its 'id'
        // ... and assign retrieved entity as to Optional<Store> container 'optionalStore'
        Optional<Store> optionalStore = storeRepository.findById(id);
        // check to see not empty/false
        if(optionalStore.isPresent()){
            // attempts to .get() entity from 'optionalStore' container
            Store store = optionalStore.get();
            // implement setter method .setName() to replace entity w/ specific store 'id' ...
            // w/ given 'replace' Store's name
            store.setName(replacement.getName());
            // .save() the changes
            storeRepository.save(store);
        }
        // call entity/record w/ this 'id' & return 'Store' results
        return optionalStore.get(); 
        // return store;    --- this one would NOT work as 'store' was initialized within if block scope --- Out of Scope
    }

}

