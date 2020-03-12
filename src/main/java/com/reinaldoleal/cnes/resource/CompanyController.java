package com.reinaldoleal.cnes.resource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/company")
class CompanyController {
	
	@GetMapping
	public ResponseEntity<Object> listCompanies(@RequestParam(required = false) Map<String, String> params) {
		
		HttpURLConnection connection = null;
		StringBuilder sb = new StringBuilder();

		try {
			String urlStr = "http://localhost:3000/empresas";
			String paramsStr = "";

			JSONObject jObj = new JSONObject( params );
			
			for(Object argumentsKey : jObj.keySet()) {
				if (paramsStr == "") {
					paramsStr += "?";
				} else {
					paramsStr += "&";
				}

				paramsStr = paramsStr + argumentsKey+"="+jObj.get(argumentsKey);
			}
			
			URL url = new URL(urlStr + paramsStr);   
			connection = (HttpURLConnection)url.openConnection();

//			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");

			InputStream content = connection.getInputStream();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			
			try {
				String line;
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
			} finally {
				in.close();
			}
			
			return new ResponseEntity<Object>(sb, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Object>(sb, HttpStatus.BAD_REQUEST);
		} finally {
			connection.disconnect();
		}
	}

	@GetMapping("/{co_cnes}")
	public ResponseEntity<Object> getCompany(@PathVariable Integer co_cnes) {

		System.out.print("getCompany");

		HttpURLConnection connection = null;
		StringBuilder sb = new StringBuilder();

		try {
			URL url = new URL("http://localhost:3000/empresas/" + co_cnes);   
			connection = (HttpURLConnection)url.openConnection();

//			connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");

			InputStream content = connection.getInputStream();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(content));

			try {
				String line;
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
			} finally {
				in.close();
			}
			
			return new ResponseEntity<Object>(sb, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Object>(sb, HttpStatus.BAD_REQUEST);
		} finally {
			connection.disconnect();
		}
	}

	@DeleteMapping("/{co_cnes}")
	public ResponseEntity<Object> deleteCompany(@PathVariable Integer co_cnes) {

		HttpURLConnection connection = null;
		StringBuilder sb = new StringBuilder();

		try {
			URL url = new URL("http://localhost:3000/empresas/" + co_cnes);   
			connection = (HttpURLConnection)url.openConnection();

//			connection.setDoOutput(true);
			connection.setRequestMethod("DELETE");
			connection.setRequestProperty("Content-Type", "application/json");
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			try {
				String line;
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
			} finally {
				in.close();
			}
			
			return new ResponseEntity<Object>(sb, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Object>(sb, HttpStatus.BAD_REQUEST);
		} finally {
			connection.disconnect();
		}
	}

	@PutMapping("/{co_cnes}")
	public ResponseEntity<Object> updateCompany(@PathVariable Integer co_cnes, @RequestBody String company) {
		
		HttpURLConnection connection = null;
		StringBuilder sb = new StringBuilder();

		try {
			URL url = new URL("http://localhost:3000/empresas/" + co_cnes);   
			connection = (HttpURLConnection)url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");

			OutputStream  out = connection.getOutputStream();
			out.write((company).getBytes());
			out.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			try {
				String line;
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
			} finally {
				in.close();
			}
			
			return new ResponseEntity<Object>(sb, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Object>(sb, HttpStatus.BAD_REQUEST);
		} finally {
			connection.disconnect();
		}
	}

	@PostMapping
	public ResponseEntity<Object> createCompany(@RequestBody String company) {

		System.out.println("createCompany");	
		
		HttpURLConnection connection = null;
		StringBuilder sb = new StringBuilder();

		try {
			URL url = new URL("http://localhost:3000/empresas");   
			connection = (HttpURLConnection)url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			OutputStream  out = connection.getOutputStream();
			out.write((company).getBytes());
			out.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			try {
				String line;
				while ((line = in.readLine()) != null) {
					sb.append(line);
				}
			} finally {
				in.close();
			}
			
			return new ResponseEntity<Object>(sb, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Object>(sb, HttpStatus.BAD_REQUEST);
		} finally {
			connection.disconnect();
		}
	}

}
