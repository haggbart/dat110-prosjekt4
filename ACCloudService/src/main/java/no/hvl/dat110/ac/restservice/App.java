package no.hvl.dat110.ac.restservice;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.post;
import static spark.Spark.delete;
import static spark.route.HttpMethod.post;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static AccessLog accesslog = null;
	static AccessCode accesscode = null;

	private static final Gson gson = new Gson();
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		// objects for data stored in the service
		
		accesslog = new AccessLog();
		accesscode  = new AccessCode();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		// for basic testing purposes
		get("/accessdevice/hello", (req, res) -> gson.toJson("IoT Access Control Device"));
		
		// TODO: implement the routes required for the access control service
		// as per the HTTP/REST operations describined in the project description

		// POST /accessdevice/log/
		post("/accessdevice/log/", ((request, response) -> {
			int id = accesslog.add(gson.fromJson(request.body(), AccessMessage.class).getMessage());
			return gson.toJson(accesslog.get(id));
		}));

		// GET /accessdevice/log/
		get("/accessdevice/log/", (request, response) ->
				accesslog.toJson());

		// GET /accessdevice/log/{id}
		get("/accessdevice/log/:id", (request, response) ->
				gson.toJson(accesslog.get(Integer.parseInt(request.params("id")))));

		// PUT /accessdevice/code
		put("/accessdevice/code", ((request, response) -> {
			accesscode = gson.fromJson(request.body(), AccessCode.class);
			// kunne returnert body tilbake, men det ville ikke vist at ting er gjort riktig.
			return gson.toJson(accesscode);
		}));

		// GET /accessdevice/code
		get("/accessdevice/code", (((request, response) ->
				gson.toJson(accesscode))));

		// DELETE /accessdevice/log/
		delete("accessdevice/log/", ((request, response) -> {
			accesslog.clear();
			return gson.toJson(accesslog.log.values());
		}));
    }
}
