package com.example.sql.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.sql.models.miAPI;



@Controller
@RequestMapping("/miAPI")
public class ConsultamiAPI {

	
	@GetMapping("{sw}")
	public String startMethod (@PathVariable("sw") String sw, Model model) {
		String titulo = "Pagina API con SprinBot";
		model.addAttribute("name", sw);
		model.addAttribute("titulo", titulo);
		miAPI startClienteDTO = starClientResultDTO (sw);
		model.addAttribute("startClienteDTO", startClienteDTO);
		return "viewmain";
	}
	
	public miAPI starClientResultDTO (String numID) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<miAPI> resp =
				restTemplate
				.getForEntity(
						String.format("https://jsonplaceholder.typicode.com/todos/%s", numID),
						miAPI.class);
		return resp.getBody();
	}
	

}
