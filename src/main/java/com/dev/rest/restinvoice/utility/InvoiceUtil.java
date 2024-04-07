package com.dev.rest.restinvoice.utility;

import com.dev.rest.restinvoice.entity.Invoice;
import org.springframework.stereotype.Component;

@Component
public class InvoiceUtil {
    public Invoice calculateFinalAmountIncludingGST(Invoice invoice) {
        Double amount = invoice.getAmount();
        final Double VAT = 0.1;
        Double finalAmount = amount + (amount * VAT);
        invoice.setFinalAmount(finalAmount);
        return invoice;
    }

    public void copyNonNullValues(Invoice invoiceRequest, Invoice invoiceDb) {
        if(invoiceRequest.getName() !=null)
            invoiceDb.setName(invoiceRequest.getName());
        if(invoiceRequest.getAmount() !=null)
            invoiceDb.setAmount(invoiceRequest.getAmount());
        if(invoiceRequest.getNumber() !=null)
            invoiceDb.setNumber(invoiceRequest.getNumber());
        if(invoiceRequest.getComments() !=null)
            invoiceDb.setComments(invoiceRequest.getComments());
        if(invoiceRequest.getType() !=null)
            invoiceDb.setType(invoiceRequest.getType());
        if(invoiceRequest.getReceivedDate() !=null)
            invoiceDb.setReceivedDate(invoiceRequest.getReceivedDate());
        if(invoiceRequest.getVendor() !=null)
            invoiceDb.setVendor(invoiceRequest.getVendor());
    }

}
