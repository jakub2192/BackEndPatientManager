/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.Patient;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-14T13:20:07.664Z")

@Api(value = "patient", description = "the patient API")
public interface PatientApi {

    @ApiOperation(value = "Add a new patient", nickname = "addPatient", notes = "", tags={ "patient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/patient",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addPatient(@ApiParam(value = "Patient object that needs to be added" ,required=true )  @Valid @RequestBody Patient body);


    @ApiOperation(value = "Deletes a pet", nickname = "deletePatient", notes = "", tags={ "patient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Patient not found") })
    @RequestMapping(value = "/patient/{patientId}",
        produces = { "application/xml", "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deletePatient(@ApiParam(value = "Patient id to delete",required=true) @PathVariable("patientId") Long patientId);


    @ApiOperation(value = "Find patinet by ID", nickname = "getPatientById", notes = "Returns a single patinet", response = Patient.class, tags={ "patient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = Patient.class),
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Patient not found") })
    @RequestMapping(value = "/patient/{patientId}",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Patient> getPatientById(@ApiParam(value = "ID of patient to return",required=true) @PathVariable("patientId") Long patientId);


    @ApiOperation(value = "Update an existing patient", nickname = "updatePatient", notes = "", tags={ "patient", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Patient not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/patient/{patientId}",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updatePatient(@ApiParam(value = "ID of patient to update",required=true) @PathVariable("patientId") Long patientId,@ApiParam(value = "Patient object that needs to be added" ,required=true )  @Valid @RequestBody Patient body);

}