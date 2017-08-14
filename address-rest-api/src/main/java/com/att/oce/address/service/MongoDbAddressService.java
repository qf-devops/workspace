package com.att.oce.address.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.att.oce.address.Address;
import com.att.oce.address.AddressBO;
import com.att.oce.address.repository.AddressRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 * This service class saves {@link com.javaadvent.bootrest.Address.Address} objects
 * to MongoDB database.
 * @author pg939j
 */
@Service
final class MongoDBAddressService implements AddressService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDBAddressService.class);

    private final AddressRepository repository;

    @Autowired
    MongoDBAddressService(AddressRepository repository) {
        this.repository = repository;
    }

    public AddressBO create(AddressBO address) {
        LOGGER.info("Creating a new Address entry with information: {}", address);

        Address persisted = Address.getBuilder()
                .title(address.getTitle())
                .description(address.getDescription())
                .build();

        persisted = repository.save(persisted);
        LOGGER.info("Created a new Address entry with information: {}", persisted);

        return convertToDTO(persisted);
    }

    public AddressBO delete(String id) {
        LOGGER.info("Deleting a Address entry with id: {}", id);

        Address deleted = findAddressById(id);
        repository.delete(deleted);

        LOGGER.info("Deleted Address entry with informtation: {}", deleted);

        return convertToDTO(deleted);
    }

    public List<AddressBO> findAll() {
        LOGGER.info("Finding all Address entries.");

        List<Address> AddressEntries = repository.findAll();

        LOGGER.info("Found {} Address entries", AddressEntries.size());
        
        return convertToDTOs(AddressEntries);
    }

   /* private List<AddressBO> convertToDTOs(List<Address> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }*/

    public AddressBO findById(String id) {
        LOGGER.info("Finding Address entry with id: {}", id);

        Address found = findAddressById(id);

        LOGGER.info("Found Address entry: {}", found);

        return convertToDTO(found);
    }

    public AddressBO update(AddressBO Address) {
        LOGGER.info("Updating Address entry with information: {}", Address);

        Address updated = findAddressById(Address.getId());
        updated.update(Address.getTitle(), Address.getDescription());
        updated = repository.save(updated);

        LOGGER.info("Updated Address entry with information: {}", updated);

        return convertToDTO(updated);
    }

    private Address findAddressById(String id) {
        Optional<Address> result = repository.findOne(id);
        return result.get();

    }

    private AddressBO convertToDTO(Address model) {
        AddressBO dto = new AddressBO();

        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setDescription(model.getDescription());

        return dto;
    }
    
    
    private List<AddressBO> convertToDTOs(List<Address> model) {
    	
    	List<AddressBO> addressBOList = new ArrayList<AddressBO>();
    	
    	for(Address address : model){
        AddressBO dto = new AddressBO();

        dto.setId(address.getId());
        dto.setTitle(address.getTitle());
        dto.setDescription(address.getDescription());
        
        addressBOList.add(dto);
    	}
        return addressBOList;
    }

}
