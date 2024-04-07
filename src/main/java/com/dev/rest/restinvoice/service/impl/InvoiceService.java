package com.dev.rest.restinvoice.service.impl;

import com.dev.rest.restinvoice.entity.Invoice;
import com.dev.rest.restinvoice.repository.InvoiceRepository;
import com.dev.rest.restinvoice.service.IInvoiceService;
import com.dev.rest.restinvoice.utility.InvoiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class InvoiceService implements IInvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceUtil invoiceUtil;

    @Override
    public Long saveInvoice(Invoice invoice) {
        invoiceUtil.calculateFinalAmountIncludingGST(invoice);
        return invoiceRepository.save(invoice).getId();
    }

    @Override
    public void updateInvoice(Invoice invoice) {

    }

    @Override
    public void deleteInvoice(Long id) {

    }

    @Override
    public Invoice getInvoice() {
        return null;
    }

    @Override
    public List<Invoice> getAllInvoices() {
        return List.of();
    }

    @Override
    public boolean isInvoiceExist(Long id) {
        return false;
    }

    @Override
    public Integer updateInvoiceNumberById(String number, Long id) {
        return 0;
    }
}
