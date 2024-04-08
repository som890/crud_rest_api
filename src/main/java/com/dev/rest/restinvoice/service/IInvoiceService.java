package com.dev.rest.restinvoice.service;

import com.dev.rest.restinvoice.entity.Invoice;

import java.util.List;

public interface IInvoiceService {
    public Long saveInvoice(Invoice invoice);
    public void updateInvoice(Invoice invoice);
    public void deleteInvoice(Long id);
    public Invoice getInvoice(Long id) ;
    public List<Invoice> getAllInvoices();
    public boolean isInvoiceExist(Long id);
    public Integer updateInvoiceNumberById(String number, Long id);
}
