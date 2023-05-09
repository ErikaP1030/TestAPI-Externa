package com.example.sql.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.sql.models.alumnosDTO;

@Controller
@RequestMapping("/miAlumno")
public class ConsultasAlumnos {
	
	@GetMapping("{sw}")
	public String startMethod (@PathVariable("sw") String sw, Model model) {
		String titulo = "Pagina API con SprinBot";
		model.addAttribute("name", sw);
		model.addAttribute("titulo", titulo);
		alumnosDTO startClienteDTO = starClientResultDTO (sw);
		model.addAttribute("startClienteDTO", startClienteDTO);
		return "viewmain";
	}
	
	public alumnosDTO starClientResultDTO (String numID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<alumnosDTO> resp =
				restTemplate
				.getForEntity(
						String.format("https://myappfb-4718b.firebaseio.com/campeche/alumnosTSP.json/%s", numID),
						alumnosDTO.class);
		return resp.getBody();
	}

}
