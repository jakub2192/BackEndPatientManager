package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiParam;
import io.swagger.model.Address;
import io.swagger.model.Patient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import repository.AddressRepository;
import repository.PatientRepository;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-14T13:20:07.664Z")

@Controller
public class PatientApiController implements PatientApi {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;

    private static final Logger log = LoggerFactory.getLogger(PatientApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;
    private List<Patient> patients = new ArrayList<Patient>();

    @org.springframework.beans.factory.annotation.Autowired
    public PatientApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addPatient(@ApiParam(value = "Patient object that needs to be added", required = true) @Valid @RequestBody Patient body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json") && !patients.stream().filter(index -> index.getId() == body.getId()).findFirst().isPresent()) {
            patients.add(body);
            Patient newPatient = new Patient();
            Address newAddress = new Address();
            newPatient.setId(body.getId());
            newPatient.setBirthNumber(body.getBirthNumber());
            newPatient.setName(body.getName());
            newPatient.setStatus(body.getStatus());
            newPatient.setAddresses(body.getAddresses());
            body.getAddresses().stream().forEach(address -> {
                addressRepository.save(address);
            });
            patientRepository.save(newPatient);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
        return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
    }

    public ResponseEntity<Void> deletePatient
            (@ApiParam(value = "Patient id to delete", required = true) @PathVariable("patientId") Long patientId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                patientRepository.delete(patientId);
                patients.remove(getPatientFromList(patientId));
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (NoSuchElementException e) {
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            } catch (MethodArgumentTypeMismatchException e1) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    public ResponseEntity<Patient> getPatientById
            (@ApiParam(value = "ID of patient to return", required = true) @PathVariable("patientId") final Long patientId) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Patient patient = patientRepository.findById(patientId);
                System.out.println(patient);
                return new ResponseEntity<Patient>(getPatientFromList(patientId), HttpStatus.FOUND);
//                return new ResponseEntity<Patient>(objectMapper.readValue("{  \"addresses\" : [ {    \"zip\" : \"zip\",    \"country\" : \"country\",    \"city\" : \"city\",    \"name\" : \"name\",    \"id\" : 6  }, {    \"zip\" : \"zip\",    \"country\" : \"country\",    \"city\" : \"city\",    \"name\" : \"name\",    \"id\" : 6  } ],  \"name\" : \"Jan Chorlavy\",  \"id\" : 0,  \"birthNumber\" : \"8604125468\",  \"status\" : \"slobodny\"}", Patient.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (NoSuchElementException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Patient>(HttpStatus.NOT_FOUND);
            } catch (MethodArgumentTypeMismatchException e1) {
                return new ResponseEntity<Patient>(HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<Patient>(HttpStatus.OK);
    }

    public ResponseEntity<Void> updatePatient
            (@ApiParam(value = "ID of patient to update", required = true) @PathVariable("patientId") Long
                     patientId, @ApiParam(value = "Patient object that needs to be added", required = true) @Valid @RequestBody Patient
                     body) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                Patient patient = getPatientFromList(patientId);
                Patient newPatient = new Patient();
                Address newAddress = new Address();
                newPatient.setId(body.getId());
                newPatient.setBirthNumber(body.getBirthNumber());
                newPatient.setName(body.getName());
                newPatient.setStatus(body.getStatus());
                newPatient.setAddresses(body.getAddresses());
                body.getAddresses().stream().forEach(address -> {
                    addressRepository.save(address);
                });
                patientRepository.save(newPatient);
                patients.set(patients.indexOf(patient), body);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (NoSuchElementException e) {
//                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
            } catch (MethodArgumentTypeMismatchException e1) {
                return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    private Patient getPatientFromList(Long patientId) {
        return patients.stream().filter(index -> index.getId() == patientId).findFirst().get();
    }

}
