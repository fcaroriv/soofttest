package ar.edu.teclab.prueba.controller;

import ar.edu.teclab.prueba.dto.Ejemplo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.teclab.prueba.service.PruebaService;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class PruebaController {

	private static final Log LOG = LogFactory.getLog(PruebaController.class);
	private String baseUrl = "https://teclab1593636133.zendesk.com";
	private String ticketsUrl = "/api/v2/tickets/";
	private String commentsUrl = "/comments";

	@Autowired
	protected PruebaService pruebaService;

	@GetMapping("/ejemplo")
	public ResponseEntity<Ejemplo> getMessageStatus(@RequestParam(value ="nombre") String nombre) {
		try {
			Ejemplo ejemplo = new Ejemplo();
			ejemplo.setNombre(nombre);
			return ResponseEntity.ok(ejemplo);
		}catch (Exception e){
			LOG.error("Error", e);
		}
		return null;
	}

}


