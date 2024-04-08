package com.dev.rest.restinvoice.repository;

import com.dev.rest.restinvoice.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {


    @Modifying
    @Query("UPDATE Invoice SET number=:number WHERE id=:id")
    Integer updateInvoiceNumberById(String number, Long id);
}
