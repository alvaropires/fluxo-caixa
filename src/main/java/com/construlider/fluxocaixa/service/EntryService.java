package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.dto.request.EntryRequest;
import com.construlider.fluxocaixa.dto.response.EntryResponse;
import com.construlider.fluxocaixa.exceptions.EntryNotFoundException;
import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.models.Entry;
import com.construlider.fluxocaixa.models.Person;
import com.construlider.fluxocaixa.models.Product;
import com.construlider.fluxocaixa.models.enums.TypeEntry;
import com.construlider.fluxocaixa.repository.EntryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryService {

    private final EntryRepository entryRepository;
    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final PersonService personService;

    @Autowired
    public EntryService(EntryRepository entryRepository, ModelMapper modelMapper, ProductService productService, CategoryService categoryService, PersonService personService){
        this.entryRepository = entryRepository;
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.categoryService = categoryService;
        this.personService = personService;
    }
    public Entry create(Entry entry){
        return entryRepository.save(entry);
    }

    public List<Entry> findAll(){
        return entryRepository.findAll();
    }
    public Page<Entry> findAll(Pageable pageable, String flag){
        if(flag!= null && flag.equals("expense")){
            return entryRepository.entriesFromTypeEntry(TypeEntry.EXPENSE, pageable);
        }
        else if(flag != null && flag.equals("income")){
            return entryRepository.entriesFromTypeEntry(TypeEntry.INCOME, pageable);
        }else {
            return entryRepository.findAll(pageable);
        }
    }

    public Entry findById(int id){
        return entryRepository.findById(id).orElseThrow(()-> new EntryNotFoundException(id));
    }

    public Entry update(int id, Entry updatedEntry){
        var entry = this.findById(id);
        entry.setDescription(updatedEntry.getDescription());
        entry.setProduct(updatedEntry.getProduct());
        entry.setCategory(updatedEntry.getCategory());
        entry.setPerson(updatedEntry.getPerson());
        entry.setTypeEntry(updatedEntry.getTypeEntry());
        entry.setAmount(updatedEntry.getAmount());
        entry.setPaid(updatedEntry.isPaid());
        entry.setEntryDate(updatedEntry.getEntryDate());
        entry.setPaymentDate(updatedEntry.getPaymentDate());
        entry.setObservation(updatedEntry.getObservation());
        return entryRepository.save(entry);
    }
    public void deleteById(int id){
        entryRepository.deleteById(id);
    }
    public Page<Entry> getEntriesFromCategory(Category category, Pageable pageable){
        return entryRepository.entriesFromCategory(category, pageable);
    }
    public Page<Entry> getEntriesFromProduct(Product product, Pageable pageable){
        return entryRepository.entriesFromProduct(product, pageable);
    }

    public Page<Entry> getEntriesFromPerson(Person person, Pageable pageable){
        return entryRepository.entriesFromPerson(person, pageable);
    }

    public EntryResponse toEntryResponse(Entry entry){
        return modelMapper.map(entry, EntryResponse.class);
    }

    public List<EntryResponse> toEntryResponseList(List<Entry> entries){
        return entries.stream().map(this::toEntryResponse).collect(Collectors.toList());
    }

    public Entry toEntry(EntryRequest entryRequest){
        Product product = productService.findById(entryRequest.getProduct());
        Category category = categoryService.findById(entryRequest.getCategory());
        Person person = personService.findById(entryRequest.getPerson());
        Entry entry = modelMapper.map(entryRequest, Entry.class);
        entry.setProduct(product);
        entry.setCategory(category);
        entry.setPerson(person);
        return entry;
    }
    public Page<EntryResponse> toEntryResponsePage(Page<Entry> entryPage){
        return entryPage.map(this::toEntryResponse);
    }
}
