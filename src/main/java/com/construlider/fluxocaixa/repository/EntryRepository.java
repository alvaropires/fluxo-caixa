package com.construlider.fluxocaixa.repository;

import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.models.Entry;
import com.construlider.fluxocaixa.models.Person;
import com.construlider.fluxocaixa.models.Product;
import com.construlider.fluxocaixa.models.enums.TypeEntry;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {

    @Query("SELECT e FROM Entry e WHERE e.category = :category")
    Page<Entry> entriesFromCategory(@Param("category") Category category, Pageable pageable);

    @Query("SELECT e FROM Entry e WHERE e.product = :product")
    Page<Entry> entriesFromProduct(@Param("product") Product produc, Pageable pageable);
    @Query("SELECT e FROM Entry e WHERE e.typeEntry = :typeEntry")
    Page<Entry> entriesFromTypeEntry(@Param("typeEntry") TypeEntry typeEntry, Pageable pageable);

    @Query("SELECT e FROM Entry e WHERE e.person = :person")
    Page<Entry> entriesFromPerson(@Param("person")Person person, Pageable pageable);
}
