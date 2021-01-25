package ar.edu.teclab.comments.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

import ar.edu.teclab.comments.service.CommentsService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.teclab.prueba.service.PruebaService;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentsController {

	private static final Log LOG = LogFactory.getLog(CommentsController.class);
	private String baseUrl = "https://teclab1593636133.zendesk.com";
	private String ticketsUrl = "/api/v2/tickets/";
	private String commentsUrl = "/comments";

	@Autowired
	protected CommentsService commentsService;


	@GetMapping("/getComments")
	public ResponseEntity<JsonObject> getCommentsList(@RequestParam(value = "ticketId") Integer ticketId){
		try {
			URL url = new URL(baseUrl + ticketsUrl + ticketId + commentsUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			String userCredentials = "jorge.danni@teclab.edu.ar:Abril2019";
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Authorization", basicAuth);

			StringBuilder sb = new StringBuilder();

			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
				String line;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				br.close();
			}else {
				LOG.error("Error: request didn't work");
			}

			JsonObject jsonObject = new JsonParser().parse(sb.toString()).getAsJsonObject();

			return ResponseEntity.ok().body(jsonObject);

		}catch (Exception e){
			LOG.error("Error", e);
		}
		return (ResponseEntity<JsonObject>) ResponseEntity.badRequest();
	}

	@PutMapping("/insertComment")
	public ResponseEntity.BodyBuilder getCommentsList(@RequestParam(value = "ticketId") Integer ticketId,
													  @RequestParam(value = "comment") String comment){

		try {
			URL url = new URL(baseUrl + ticketsUrl + ticketId );
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			String userCredentials = "jorge.danni@teclab.edu.ar:Abril2019";
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));

			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Authorization", basicAuth);

			String requestJson = "{\"ticket\": {\"comment\": {\"body\": \" " + comment
					+" \", \"author_id\": 400041509731}}}";

			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty ("Authorization", basicAuth);
			con.setDoOutput(true);

			try(OutputStream os = con.getOutputStream()) {
				byte[] input = requestJson.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			if (con.getResponseCode() == HttpURLConnection.HTTP_OK) { // success
				return ResponseEntity.ok();

			}else {
				System.out.println("Request didn't work");
			}

		}catch (Exception e){
			LOG.error("Error", e);
		}
		ResponseEntity.badRequest();

		return ResponseEntity.badRequest();
	}

}


