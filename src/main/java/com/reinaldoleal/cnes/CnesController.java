package com.reinaldoleal.cnes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
class CnesController {
	
	@GetMapping(path = "/company")
	public ResponseEntity<Object> getAll() {
		
		HttpURLConnection connection = null;
		StringBuilder sb = new StringBuilder();

		try {
			URL url = new URL("http://localhost:3000/empresas");   
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			InputStream content = connection.getInputStream();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
			in.close();
			
			return new ResponseEntity<Object>(sb, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Object>(sb, HttpStatus.BAD_REQUEST);
		} finally {
			connection.disconnect();
		}
	}
	
	@GetMapping(path = "/company/{id}")
	public ResponseEntity<Object> getCompany(@PathVariable String id) {

		HttpURLConnection connection = null;
		StringBuilder sb = new StringBuilder();

		try {
			URL url = new URL("http://localhost:3000/empresas/" + id);   
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoOutput(true);
			InputStream content = connection.getInputStream();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
			in.close();
			
			return new ResponseEntity<Object>(sb, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Object>(sb, HttpStatus.BAD_REQUEST);
		} finally {
			connection.disconnect();
		}
	}

	@DeleteMapping(path = "/company/{id}")
	public ResponseEntity<Object> deleteCompany(@PathVariable String id) {

		HttpURLConnection connection = null;
		StringBuilder sb = new StringBuilder();

		try {
			URL url = new URL("http://localhost:3000/empresas/" + id);   
			connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("DELETE");
			connection.setDoOutput(true);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
			in.close();
			
			return new ResponseEntity<Object>(sb, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Object>(sb, HttpStatus.BAD_REQUEST);
		} finally {
			connection.disconnect();
		}
	}

	@PutMapping(path = "/company/{id}")
	public ResponseEntity<Object> updateCompany(@PathVariable String id, @RequestBody String company) {
		
		HttpURLConnection connection = null;
		StringBuilder sb = new StringBuilder();

		try {
			URL url = new URL("http://localhost:3000/empresas/" + id);   
			connection = (HttpURLConnection)url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("PUT");
			connection.setRequestProperty("Content-Type", "application/json");

			OutputStream  out = connection.getOutputStream();
			out.write((company).getBytes());
			out.flush();
			
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
			in.close();
			
			return new ResponseEntity<Object>(sb, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Object>(sb, HttpStatus.BAD_REQUEST);
		} finally {
			connection.disconnect();
		}
	}

	@PostMapping(path = "/company")
	public ResponseEntity<Object> createCompany(@RequestBody String company) {
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
			String line;
			while ((line = in.readLine()) != null) {
				sb.append(line);
			}
			
			in.close();
			
			return new ResponseEntity<Object>(sb, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			
			return new ResponseEntity<Object>(sb, HttpStatus.BAD_REQUEST);
		} finally {
			connection.disconnect();
		}
	}

}
