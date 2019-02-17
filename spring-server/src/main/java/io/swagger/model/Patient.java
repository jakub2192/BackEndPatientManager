package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Address;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Patient
 */
@Entity
@Table(name = "Patient")
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2019-02-14T13:20:07.664Z")

public class Patient   {
  @Id
  @Column(name = "id")
  @JsonProperty("id")
  private Long id = null;

  @Column(name = "name")
  @JsonProperty("name")
  private String name = null;

  @Column(name = "birthNumber")
  @JsonProperty("birthNumber")
  private String birthNumber = null;

  @OneToMany(cascade = CascadeType.ALL,
          orphanRemoval = true)
  @Column(name = "addresses")
  @JsonProperty("addresses")
  @Valid
  private List<Address> addresses = null;

  /**
   * Gets or Sets status
   */
  public enum StatusEnum {
    SLOBODNY("slobodny"),
    
    VYDATA("vydata"),
    
    ZENATY("zenaty");

    private String value;

    StatusEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static StatusEnum fromValue(String text) {
      for (StatusEnum b : StatusEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("status")
  private StatusEnum status = null;

  public Patient id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Patient name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(example = "Jan Chorlavy", required = true, value = "")
  @NotNull


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Patient birthNumber(String birthNumber) {
    this.birthNumber = birthNumber;
    return this;
  }

  /**
   * Get birthNumber
   * @return birthNumber
  **/
  @ApiModelProperty(example = "8604125468", required = true, value = "")
  @NotNull


  public String getBirthNumber() {
    return birthNumber;
  }

  public void setBirthNumber(String birthNumber) {
    this.birthNumber = birthNumber;
  }

  public Patient addresses(List<Address> addresses) {
    this.addresses = addresses;
    return this;
  }

  public Patient addAddressesItem(Address addressesItem) {
    if (this.addresses == null) {
      this.addresses = new ArrayList<Address>();
    }
    this.addresses.add(addressesItem);
    return this;
  }

  /**
   * Get addresses
   * @return addresses
  **/
  @ApiModelProperty(value = "")

  @Valid

  public List<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(List<Address> addresses) {
    this.addresses = addresses;
  }

  public Patient status(StatusEnum status) {
    this.status = status;
    return this;
  }

  /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")


  public StatusEnum getStatus() {
    return status;
  }

  public void setStatus(StatusEnum status) {
    this.status = status;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Patient patient = (Patient) o;
    return Objects.equals(this.id, patient.id) &&
        Objects.equals(this.name, patient.name) &&
        Objects.equals(this.birthNumber, patient.birthNumber) &&
        Objects.equals(this.addresses, patient.addresses) &&
        Objects.equals(this.status, patient.status);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, birthNumber, addresses, status);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Patient {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    birthNumber: ").append(toIndentedString(birthNumber)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

