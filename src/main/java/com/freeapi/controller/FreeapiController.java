package com.freeapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.freeapi.util.FreeAPIConstants;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(servers = { @Server(url = "http://localhost:8080") }, info = @Info(title = "Free API", version = "v1", description = "Freeapi Swagger-UI enabled", license = @License(name = "MIT License", url = "https://github.com/gomatikarki"), contact = @Contact(url = "https://in.linkedin.com/in/gomati-karki-0956219", name = "Gomati Karki")))
@RestController
@RequestMapping("freeapi")
public class FreeapiController {
	
	RestTemplate restTemplate = new RestTemplate();
	
	@Operation(summary = "Returns list of universities")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(type = "object"))) })
	@GetMapping(value = "/universitylist/{country}", produces = { "application/json" })
	public String getCountryListOfUniversities(@PathVariable(value="country") String country) {
		String universityURL = FreeAPIConstants.UNIVERSITY_LIST + country;
		ResponseEntity<String> response
		  = restTemplate.getForEntity(universityURL, String.class);
		String listOfUniversity = response.getBody();
		return listOfUniversity;
	}
	
	@Operation(summary = "Returns place name based on zipcode")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(type = "object"))) })
	@GetMapping(value = "/zipcode/{country}/{code}", produces = { "application/json" })
	public String getZipcodeInfo(@PathVariable(value="country") String country, @PathVariable(value="code") String code) {
		String universityURL = FreeAPIConstants.ZIPCODE_INFO + country + "/" + code;
		ResponseEntity<String> response
		  = restTemplate.getForEntity(universityURL, String.class);
		String placeName = response.getBody();
		return placeName;
	}

}
