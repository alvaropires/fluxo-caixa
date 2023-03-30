package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.dto.request.EntryRequest;
import com.construlider.fluxocaixa.dto.response.EntryResponse;
import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.models.Entry;
import com.construlider.fluxocaixa.models.Product;
import com.construlider.fluxocaixa.models.enums.TypeEntry;
import com.construlider.fluxocaixa.repository.EntryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntryService {

    private final EntryRepository entryRepository;
    private final ModelMapper modelMapper;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public EntryService(EntryRepository entryRepository, ModelMapper modelMapper, ProductService productService, CategoryService categoryService){
        this.entryRepository = entryRepository;
        this.modelMapper = modelMapper;
        this.productService = productService;
        this.categoryService = categoryService;
    }
    public Entry create(Entry entry){
        return entryRepository.save(entry);
    }

    public List<Entry> findAll(){
        return entryRepository.findAll();
    }

    public Entry findById(int id){
        return entryRepository.findById(id).orElseThrow();
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
    public List<Entry> getEntriesFromCategory(Category category){
        return entryRepository.entriesFromCategory(category);
    }
    public List<Entry> getEntriesFromProduct(Product product){
        return entryRepository.entriesFromProduct(product);
    }
    public List<Entry> getEntriesFromTypeEntry(TypeEntry typeEntry){
        return entryRepository.entriesFromTypeEntry(typeEntry);
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
        Entry entry = modelMapper.map(entryRequest, Entry.class);
        entry.setProduct(product);
        entry.setCategory(category);
        return entry;
    }


}
