package com.construlider.fluxocaixa.repository;

import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.models.Entry;
import com.construlider.fluxocaixa.models.Product;
import com.construlider.fluxocaixa.models.enums.TypeEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntryRepository extends JpaRepository<Entry, Integer> {

    @Query("SELECT e FROM Entry e WHERE e.category = :category")
    List<Entry> entriesFromCategory(@Param("category") Category category);

    @Query("SELECT e FROM Entry e WHERE e.product = :product")
    List<Entry> entriesFromProduct(@Param("product") Product product);
    @Query("SELECT e FROM Entry e WHERE e.typeEntry = :typeEntry")
    List<Entry> entriesFromTypeEntry(@Param("typeEntry") TypeEntry typeEntry);
}
