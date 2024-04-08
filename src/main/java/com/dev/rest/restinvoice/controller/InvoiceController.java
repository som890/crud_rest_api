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

    //post invoices to db
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

    //get all invoices
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

    //get one invoice
    @GetMapping("/get_one/invoice/{id}")
    public Invoice getOneInvoice(@PathVariable Long id) {
        return iInvoiceService.getInvoice(id);
    }

    @GetMapping("/get_one/invoices/{id}")
    public ResponseEntity<?> getOneInvoices(@PathVariable Long id) {
        ResponseEntity<?> responseEntity = null;
        try{
            Invoice invoice = iInvoiceService.getInvoice(id);
            responseEntity = new ResponseEntity<Invoice>(invoice,HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
           responseEntity =  new ResponseEntity<String>("Unale to find invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //update invoice
    @PutMapping("/update/invoice")
    public void updateInvoice(@RequestBody Invoice invoice) {
        iInvoiceService.updateInvoice(invoice);
    }

    @PutMapping("/update/invoices/{id}")
    public ResponseEntity<?> updateInvoices(@PathVariable Long id, @RequestBody Invoice invoice) {
        ResponseEntity<?> responseEntity = null;
        try {
            Invoice invoiceDatabase = iInvoiceService.getInvoice(id);
            invoiceUtil.copyNonNullValues(invoice,invoiceDatabase);
            iInvoiceService.updateInvoice(invoice);
            responseEntity = new ResponseEntity<String>("Invoice has '"+ id+ "' is updated already", HttpStatus.RESET_CONTENT);
        }catch (Exception e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<String>("Unable to update invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    //update feature by id
    @PutMapping("/update/invoice/{number}/{id}")
    public Integer updateInvoiceNumberById(@PathVariable String number,@PathVariable Long id) {
        return iInvoiceService.updateInvoiceNumberById(number,id);
    }
    @PatchMapping("/update/invoices/{number}/{id}")
    public ResponseEntity<?> updateInvouicesNumberById(@PathVariable String number, @PathVariable Long id) {
        ResponseEntity<?> responseEntity = null;
        try{
            iInvoiceService.updateInvoiceNumberById(number,id);
            responseEntity = new ResponseEntity<String>("Invoice '" + number+ "' is updated", HttpStatus.PARTIAL_CONTENT);
        }catch (Exception e){
            e.printStackTrace();
            responseEntity = new ResponseEntity<String>("Unable to update number by id Invoice", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return  responseEntity;
    }
    @DeleteMapping("delete/invoice/{id}")
    public void deleteInvoice(@PathVariable Long id) {
        iInvoiceService.deleteInvoice(id);
    }

    @DeleteMapping("delete/invoices/{id}")
    public ResponseEntity<String> deleteInvoicess(@PathVariable Long id) {
        ResponseEntity<String> responseEntity = null;
        try {
            iInvoiceService.deleteInvoice(id);
            responseEntity = new ResponseEntity<String>("Invoice has '" + id + "' is deleted succesfully", HttpStatus.OK);
        }catch(Exception e ){
            responseEntity = new ResponseEntity<String>("Unable to delete invoice now", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


}
