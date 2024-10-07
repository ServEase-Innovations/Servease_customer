package com.cus.customertab.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cus.customertab.constants.CustomerConstants;
import com.cus.customertab.dto.CustomerConcernDTO;
import com.cus.customertab.dto.CustomerDTO;
import com.cus.customertab.dto.CustomerRequestDTO;
import com.cus.customertab.service.CustomerConcernService;
import com.cus.customertab.service.CustomerRequestService;
import com.cus.customertab.service.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/customer")
@Api(value = "Customer operations API", tags = "Customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerRequestService customerRequestService;

    @Autowired
    private CustomerConcernService customerConcernService;

    //--------------------------API's FOR CUSTOMER ENTITY----------------------------------------
    // API to get all customers
    @GetMapping("/get-all-customers")
    @ApiOperation(value = CustomerConstants.RETRIEVE_ALL_DESC, response = List.class)
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    // API to get customer by ID
    @GetMapping("/get-customer-by-id/{id}")
    @ApiOperation(value = CustomerConstants.GET_BY_ID_DESC, response = CustomerDTO.class)
    public ResponseEntity<CustomerDTO> getCustomerById(
            @ApiParam(value = "ID of the customer to retrieve", required = true) @PathVariable Long id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        return ResponseEntity.ok(customerDTO); // Handle null in global exception handler
    }

    // API to add a customer
    @PostMapping("/add-customer")
    @ApiOperation(value = CustomerConstants.ADD_NEW_DESC)
    public ResponseEntity<String> addCustomer(
            @ApiParam(value = "Customer data to add", required = true) @RequestBody CustomerDTO customerDTO) {
        customerService.saveCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerConstants.CUSTOMER_ADDED);
    }

    // API to update a customer
    @PutMapping("/update-customer/{id}")
    @ApiOperation(value = CustomerConstants.UPDATE_DESC)
    public ResponseEntity<String> updateCustomer(
            @ApiParam(value = "ID of the customer to update", required = true) @PathVariable Long id,
            @ApiParam(value = "Updated customer object", required = true) @RequestBody CustomerDTO customerDTO) {
        customerDTO.setCustomerId(id);
        customerService.updateCustomer(customerDTO);
        return ResponseEntity.ok(CustomerConstants.CUSTOMER_UPDATED);
    }

    // API to delete a customer
    @PatchMapping("/delete-customer/{id}")
    @ApiOperation(value = CustomerConstants.DELETE_DESC)
    public ResponseEntity<String> deleteCustomer(
            @ApiParam(value = "ID of the customer to delete", required = true) @PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(CustomerConstants.CUSTOMER_DELETED);
    }

    //----------------------API's FOR CUSTOMER REQUEST ENTITY---------------------------------
    // API to get all customer requests
    @GetMapping("/get-all-customer-requests")
    @ApiOperation(value = "Retrieve all customer requests", response = List.class)
    public ResponseEntity<List<CustomerRequestDTO>> getAllCustomerRequests() {
        List<CustomerRequestDTO> requests = customerRequestService.getAll();
        return ResponseEntity.ok(requests);
    }

    // API to get customer request by ID
    @GetMapping("/get-customer-request-by-id/{requestId}")
    @ApiOperation(value = "Get customer request by ID", response = CustomerRequestDTO.class)
    public ResponseEntity<CustomerRequestDTO> getCustomerRequestById(@PathVariable Long requestId) {
        CustomerRequestDTO requestDTO = customerRequestService.getByRequestId(requestId);
        return ResponseEntity.ok(requestDTO); // Handle null in global exception handler
    }

    // API to get all open requests
    @GetMapping("/get-open-requests")
    @ApiOperation(value = "Retrieve all open requests", response = List.class)
    public ResponseEntity<List<CustomerRequestDTO>> getAllOpenRequests() {
        List<CustomerRequestDTO> openRequests = customerRequestService.getAllOpenRequests();
        return ResponseEntity.ok(openRequests);
    }

    // API to get all potential customers
    @GetMapping("/get-potential-customers")
    @ApiOperation(value = "Retrieve all potential customers", response = List.class)
    public ResponseEntity<List<CustomerRequestDTO>> getAllPotentialCustomers() {
        List<CustomerRequestDTO> potentialCustomers = customerRequestService.findAllPotentialCustomers();
        return ResponseEntity.ok(potentialCustomers);
    }

    // API to add a customer request
    @PostMapping("/add-customer-request")
    @ApiOperation(value = "Add a new customer request")
    public ResponseEntity<String> insertCustomerRequest(@RequestBody CustomerRequestDTO customerRequestDTO) {
        customerRequestService.insert(customerRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerConstants.CUSTOMER_REQUEST_ADDED);
    }

    // API to update customer request
    @PutMapping("/update-customer-request/{requestId}")
    @ApiOperation(value = "Update an existing customer request")
    public ResponseEntity<String> updateCustomerRequest(@PathVariable Long requestId,
            @RequestBody CustomerRequestDTO customerRequestDTO) {
        customerRequestDTO.setRequestId(requestId);
        customerRequestService.update(customerRequestDTO);
        return ResponseEntity.ok(CustomerConstants.CUSTOMER_REQUEST_UPDATED);
    }

    //--------------------------API's FOR CUSTOMER CONCERN ENTITY-------------------------------
    // API to get all customer concerns
    @GetMapping("/get-all-customer-concerns")
    @ApiOperation(value = "Retrieve all customer concerns", response = List.class)
    public ResponseEntity<List<CustomerConcernDTO>> getAllConcerns() {
        List<CustomerConcernDTO> concerns = customerConcernService.getAllConcerns();
        return ResponseEntity.ok(concerns);
    }

    // API to get a concern by ID
    @GetMapping("/get-customer-concern-by-id/{id}")
    @ApiOperation(value = "Retrieve a customer concern by ID", response = CustomerConcernDTO.class)
    public ResponseEntity<CustomerConcernDTO> getConcernById(@PathVariable Long id) {
        CustomerConcernDTO concernDTO = customerConcernService.getConcernById(id);
        return ResponseEntity.ok(concernDTO); // Handle null in global exception handler
    } 

    // API to add a new customer concern
    @PostMapping("/add-customer-concern")
    @ApiOperation(value = "Add a new customer concern", response = String.class)
    public ResponseEntity<String> addNewConcern(@RequestBody CustomerConcernDTO customerConcernDTO) {
        customerConcernService.addNewConcern(customerConcernDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(CustomerConstants.CUSTOMER_CONCERN_ADDED);
    }

    // API to update an existing customer concern
    @PutMapping("/modify-customer-concern/{id}")
    @ApiOperation(value = "Update an existing customer concern")
    public ResponseEntity<String> modifyConcern(@PathVariable Long id,
            @RequestBody CustomerConcernDTO customerConcernDTO) {
        customerConcernDTO.setId(id);
        customerConcernService.modifyConcern(customerConcernDTO);
        return ResponseEntity.ok(CustomerConstants.CUSTOMER_CONCERN_UPDATED);
    }

    // API to delete a customer concern by ID
    @DeleteMapping("/delete-customer-concern/{id}")
    @ApiOperation(value = "Delete a customer concern by ID", response = String.class)
    public ResponseEntity<String> deleteConcern(@PathVariable Long id) {
        customerConcernService.deleteConcern(id);
        return ResponseEntity.ok(CustomerConstants.CUSTOMER_CONCERN_DELETED);
    }
}
