package com.dev.rest.restinvoice.service.impl;

import com.dev.rest.restinvoice.entity.Invoice;
import com.dev.rest.restinvoice.exception.InvoiceNotFoundException;
import com.dev.rest.restinvoice.repository.InvoiceRepository;
import com.dev.rest.restinvoice.service.IInvoiceService;
import com.dev.rest.restinvoice.utility.InvoiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

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
        invoiceUtil.calculateFinalAmountIncludingGST(invoice);
        invoiceRepository.save(invoice);
    }

    @Override
    public void deleteInvoice(Long id) {
       // invoiceRepository.deleteById(id);
        //Invoice invoice = getInvoice(id);
        //invoiceRepository.delete(invoice);
        boolean checkExistInvoice = isInvoiceExist(id);
        if(checkExistInvoice) {
            invoiceRepository.deleteById(id);
        }else {
            throw new InvoiceNotFoundException("Not found invoice");
        }

    }

    @Override
    public Invoice getInvoice(Long id) {
        Invoice inv = invoiceRepository.findById(id)
                .orElseThrow(() -> new InvoiceNotFoundException("Product '" + id + "' not exist"));
        return inv;

    }

    @Override
    public List<Invoice> getAllInvoices() {
        List<Invoice> invoiceList = invoiceRepository.findAll();
        invoiceList.sort((ob1, ob2) -> ob1.getId().intValue() - ob2.getId().intValue());
        invoiceList.sort((ob1, ob2) -> ob2.getAmount().compareTo(ob1.getAmount()));
        return invoiceList;
    }

    @Override
    public boolean isInvoiceExist(Long id) {
        return invoiceRepository.existsById(id);
    }

    @Override
    @Transactional
    public Integer updateInvoiceNumberById(String number, Long id) {
        boolean invoiceCheckExist = invoiceRepository.existsById(id);
        if (!invoiceCheckExist) {
            throw new InvoiceNotFoundException("Invoice '" +
                    id +
                    "' not exist");
        }
        return invoiceRepository.updateInvoiceNumberById(number, id);
    }

}
