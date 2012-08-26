package me.openphoto.client.util.scribe;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.scribe.utils.StreamUtils;

/*
 * Scribe internal classes are not exposed. Copied here mostly for testing purposes...
 */

/**
 * Represents an HTTP Response.
 * 
 * @author Pablo Fernandez
 */
public class SimpleResponse
{
  private static final String EMPTY = "";

  private int code;
  private String body;
  private InputStream stream;
  private Map<String, String> headers;

  SimpleResponse(HttpURLConnection connection) throws IOException
  {
    try
    {
      connection.connect();
      code = connection.getResponseCode();
      headers = parseHeaders(connection);
      stream = isSuccessful() ? connection.getInputStream() : connection.getErrorStream();
    }
    catch (UnknownHostException e)
    {
      code = 404;
      body = SimpleResponse.EMPTY;
    }
  }

  private String parseBodyContents()
  {
    body = StreamUtils.getStreamContents(getStream());
    return body;
  }

  private Map<String, String> parseHeaders(HttpURLConnection conn)
  {
    Map<String, String> headers = new HashMap<String, String>();
    for (String key : conn.getHeaderFields().keySet())
    {
      headers.put(key, conn.getHeaderFields().get(key).get(0));
    }
    return headers;
  }

  public boolean isSuccessful()
  {
    return getCode() >= 200 && getCode() < 400;
  }

  /**
   * Obtains the HTTP Response body
   * 
   * @return response body
   */
  public String getBody()
  {
    return body != null ? body : parseBodyContents();
  }

  /**
   * Obtains the meaningful stream of the HttpUrlConnection, either inputStream
   * or errorInputStream, depending on the status code
   * 
   * @return input stream / error stream
   */
  public InputStream getStream()
  {
    return stream;
  }

  /**
   * Obtains the HTTP status code
   * 
   * @return the status code
   */
  public int getCode()
  {
    return code;
  }

  /**
   * Obtains a {@link Map} containing the HTTP Response Headers
   * 
   * @return headers
   */
  public Map<String, String> getHeaders()
  {
    return headers;
  }

  /**
   * Obtains a single HTTP Header value, or null if undefined
   * 
   * @param name the header name.
   * 
   * @return header value or null.
   */
  public String getHeader(String name)
  {
    return headers.get(name);
  }

}