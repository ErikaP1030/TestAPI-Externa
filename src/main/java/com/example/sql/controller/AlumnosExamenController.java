package com.example.sql.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.sql.models.Alumnos;

@Controller
@RequestMapping("/AlumnoExamen/Matricula")
public class AlumnosExamenController {
	
	@GetMapping("{sw}")
	public String getAlumnos(@PathVariable("sw") String sw, Model model) {
		String titulo = "P{agina API con SprinBot";
		model.addAttribute("name", sw);
		model.addAttribute("titulo", titulo);
		Alumnos alumnos = alumnosStart (sw);
		model.addAttribute("alumnos", alumnos);
		return "alumnos";
	}
	
	public Alumnos alumnosStart(String sw) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Alumnos> resp =
				restTemplate.getForEntity(
						String.format("https://myappfb-4718b.firebaseio.com/campeche/alumnosTSP/%s"+".json", sw), Alumnos.class);
		//model.addAttribute("respCesp", resp);
		return resp.getBody();
	}

}
