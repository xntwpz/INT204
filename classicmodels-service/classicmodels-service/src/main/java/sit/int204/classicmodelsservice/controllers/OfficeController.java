package sit.int204.classicmodelsservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sit.int204.classicmodelsservice.models.Office;
import sit.int204.classicmodelsservice.services.OfficeService;

import java.util.List;

@RestController
@RequestMapping("/api/offices") // request all method
public class OfficeController {
    @Autowired
    // create objects service cus controller can only communicate with only service not repository
    private OfficeService service;

    // read
    @GetMapping("")
    public List<Office> getAllOffices() {
        // this is list of office but front return json cus springFramework of RestController convert
        return service.getAllOffice();
    }

    // read
    @GetMapping("/{officeCode}") // add more path of /api/offices
    public Office getOfficeById(@PathVariable String officeCode) {
        return service.getOffice(officeCode);
    }

    // create
    @PostMapping("")
    public Office addNewOffice(@RequestBody Office office) {
        // @RequestBody = must post json in body and convert to java entity (Office)
        return service.createNewOffice(office);
    }

    // update
    @PutMapping("/{officeCode}")
    public Office updateOffice(@RequestBody Office office, @PathVariable String officeCode) {
        // @PathVariable = request path convert to officeCode(variable) type string
        return service.updateOffice(officeCode, office);
    }

    // delete
    @DeleteMapping("/{officeCode}")
    public void removeOffice(@PathVariable String officeCode) {
        service.removeOffice(officeCode);
    }
}