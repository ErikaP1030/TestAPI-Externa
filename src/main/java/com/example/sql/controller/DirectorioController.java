package com.example.sql.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.example.sql.models.MDirectorio;


@Controller
@RequestMapping("/Directorio/Academico")
public class DirectorioController {

	@GetMapping("{sw}")
	public String getDependecy(@PathVariable("sw") String sw, Model model) {
		String titulo = "Pagina API con SprinBot";
		model.addAttribute("name", sw);
		model.addAttribute("titulo", titulo);
		MDirectorio starDirectorioDTO = starDirectorioResultDTO (sw);
		model.addAttribute("starDirectorioDTO", starDirectorioDTO);
		return "directorio";
	}
	
	public MDirectorio starDirectorioResultDTO(String sw) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<MDirectorio> resp =
				restTemplate.getForEntity(
						String.format("https://myappfb-4718b.firebaseio.com/campeche/directorioFI/%s.json", sw), MDirectorio.class);
		return resp.getBody();
	}

}
