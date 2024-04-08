package com.dev.rest.restinvoice.controller;

import com.dev.rest.restinvoice.entity.Invoice;
import com.dev.rest.restinvoice.exception.InvoiceNotFoundException;
import com.dev.rest.restinvoice.service.IInvoiceService;
import com.dev.rest.restinvoice.utility.InvoiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class InvoiceController {

    @Autowired
    private IInvoiceService iInvoiceService;

    @Autowired
    private InvoiceUtil invoiceUtil;

    @PostMapping("/invoice")
    @ResponseBody
    public Long saveInvoice(@RequestBody Invoice inv){
        return iInvoiceService.saveInvoice(inv);
    }

    @PostMapping("/invoices")
    public ResponseEntity<String> saveInvoices(@RequestBody Invoice invoice) {
        ResponseEntity<String> responseEntity = null;
        try{
            Long id = iInvoiceService.saveInvoice(invoice);
            responseEntity = new ResponseEntity<String>("Invoice  '"+ id + "' created", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<String>("Unable to save invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
    @GetMapping("/get/invoice")
    public List<Invoice> getAllInvoice() {
        return iInvoiceService.getAllInvoices();
    }

    @GetMapping("/get/invoices")
    public ResponseEntity<?> getAllInvoices() {
        ResponseEntity<?> responseEntity = null;
        try {
            List<Invoice> invoiceList = iInvoiceService.getAllInvoices();
            responseEntity =  new ResponseEntity<List<Invoice>>(invoiceList, HttpStatus.OK);
        }catch (Exception invoiceNotFoundException){
            invoiceNotFoundException.printStackTrace();
            responseEntity = new ResponseEntity<String>("Unable to get invoice",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

}
