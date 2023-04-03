package com.construlider.fluxocaixa.util;

import com.construlider.fluxocaixa.models.*;
import com.construlider.fluxocaixa.models.enums.TypeEntry;
import com.construlider.fluxocaixa.models.enums.TypePerson;
import com.construlider.fluxocaixa.service.CategoryService;
import com.construlider.fluxocaixa.service.EntryService;
import com.construlider.fluxocaixa.service.PersonService;
import com.construlider.fluxocaixa.service.ProductService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyData {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final PersonService personService;
    private final EntryService entryService;

    @Autowired
    public DummyData(CategoryService categoryService, ProductService productService, PersonService personService, EntryService entryService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.personService = personService;
        this.entryService = entryService;
    }

//    @PostConstruct
    public void saveEntries(){
        List<Category> categoryList = new ArrayList<>();
        Category category1 = new Category();
        category1.setName("abastecimento de diesel");

        Category category2 = new Category();
        category2.setName("serviço de guincho");

        categoryList.add(category1);
        categoryList.add(category2);

        categoryList.forEach(categoryService::create);

        List<Product> productList = new ArrayList<>();
        Product product1 = new Product();
        product1.setName("caminhão");

        Product product2 = new Product();
        product2.setName("escavadeira");

        productList.add(product1);
        productList.add(product2);

        productList.forEach(productService::create);

        List<Person> personList =  new ArrayList<>();
        Person person1 = new Person();
        person1.setName("Alvaro Pires Santos");
        person1.setEmail("alvaropires.eng@hotmail.com");
        person1.setPhoneNumber("33999278378");
        person1.setTypePerson(TypePerson.CLIENT);
        Adress adress1 = new Adress();
        adress1.setStreet("Rua Belem");
        adress1.setNumber("165");
        adress1.setComplement("Apto 202");
        adress1.setDistrict("Veneza");
        adress1.setCity("Ipatinga");
        adress1.setState("Minas Gerais");
        person1.setAdress(adress1);

        Person person2 = new Person();
        person2.setName("Deborah Sa Telles Carvalho Santos");
        person2.setEmail("deborahsatelles@gmail.com");
        person2.setPhoneNumber("33999128451");
        person2.setTypePerson(TypePerson.CLIENT);
        Adress adress2 = new Adress();
        adress2.setStreet("Rua Belo Horizonte");
        adress2.setNumber("21");
        adress2.setComplement("");
        adress2.setDistrict("centro");
        adress2.setCity("Campanário");
        adress2.setState("Minas Gerais");
        person2.setAdress(adress2);

        personList.add(person1);
        personList.add(person2);

        personList.forEach(personService::create);

        List<Entry> entryList = new ArrayList<>();
        Entry entry1 = new Entry();
        entry1.setDescription("Entrada 1");
        entry1.setProduct(product1);
        entry1.setCategory(category1);
        entry1.setPerson(person1);
        entry1.setTypeEntry(TypeEntry.EXPENSE);
        entry1.setAmount(2000);
        entry1.setPaid(true);
        entry1.setEntryDate(LocalDate.now());
        entry1.setPaymentDate(LocalDate.now());
        entry1.setObservation("Sem observações");

        Entry entry2 = new Entry();
        entry2.setDescription("Entrada 2");
        entry2.setProduct(product2);
        entry2.setCategory(category2);
        entry2.setPerson(person2);
        entry2.setTypeEntry(TypeEntry.INCOME);
        entry2.setAmount(10000);
        entry2.setPaid(true);
        entry2.setEntryDate(LocalDate.now());
        entry2.setPaymentDate(LocalDate.now());
        entry2.setObservation("Sem observações 2");

        entryList.add(entry1);
        entryList.add(entry2);

        entryList.forEach(entryService::create);

    }
}
