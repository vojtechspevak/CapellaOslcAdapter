package capellaserver.server;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Class providing reusable methods for servlets 
 */
public class ServletHelper {
	
	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING_UTF_8 = "UTF-8";

	/**
	 * Sets ok response and serializes object to json as named parameter 
	 * @param response http response
	 * @param result the object to be serialized
	 * @ param resultJsonAttrtName the attribute name of the serialized object in resulting json
	 * @throws IOException
	 */
	public static void setOkResponse(HttpServletResponse response, Object result, String resultJsonAttrtName) throws IOException {
		Gson gson = new Gson();
		String resultJson = gson.toJson(result);

		response.setContentType(CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_OK);
		response.setCharacterEncoding(ENCODING_UTF_8);
		response.getWriter().println("{ \""+ resultJsonAttrtName  +"\": " + resultJson + " }");
	}
	
	/**
	 * Sets ok response and serializes object to json
	 * @param response http response
	 * @param result the object to be serialized
	 * @throws IOException
	 */
	public static void setOkResponse(HttpServletResponse response, Object result) throws IOException {
		Gson gson = new Gson();
		String resultJson = gson.toJson(result);

		response.setContentType(CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println(resultJson);
	}

	/**
	 * Sets error response with Bad request status code and error message
	 * @param response http response
	 * @param errorMessage explanation of the error
	 * @throws IOException
	 */
	public static void setErrorResponse(HttpServletResponse response,String errorMessage) throws IOException {
		response.setContentType(CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.getWriter().println("{ \"errorMessage\": \"" + errorMessage + "\" }");
	}

	/**
	 * Sets ok response and content type
	 * @param response http response
	 */
	public static void setOkResponse(HttpServletResponse response) {
		response.setCharacterEncoding(ENCODING_UTF_8);
		response.setContentType(CONTENT_TYPE);
		response.setStatus(HttpServletResponse.SC_OK);
	}

	/**
	 * retrieves and decodes Base64 encoded parameter of given name from request
	 * @param request http request with Base64 encoded project name
	 * @return decoded parameter or null if the parameter is not present in the request
	 */
	public static String getAndDecodeBase64Parameter(HttpServletRequest request, String parameterName) {
		String encodedParameter = request.getParameter(parameterName);
		if(encodedParameter == null) {
			return null;
		}
		Base64.Decoder decoder = Base64.getUrlDecoder();
		return new String(decoder.decode(encodedParameter),StandardCharsets.UTF_8);
	}
	
}
