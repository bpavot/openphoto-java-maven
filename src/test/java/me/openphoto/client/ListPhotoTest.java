package me.openphoto.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import junit.framework.TestCase;

public class ListPhotoTest extends TestCase {

    public void testGetPhotos() throws JSONException {
        OpenPhotoClient client = new OpenPhotoClient();

        String result = client.rawGet("/photos/list.json", null);
        System.out.println(result);
        
        JSONObject json = new JSONObject(result);
        JSONArray data = json.getJSONArray("result");

        // testing
        assertNotNull("Answer should not be null", result);
        assertTrue("Code is not 200", json.getInt("code") == 200);
        assertTrue("Array should be empty or >1 ", data.length() >= 0);
    }

    public void testGetPhotosNoAuth() throws JSONException {
        OpenPhotoClient client = new OpenPhotoClient();
        
        String result = client.rawGetNoAuth("/photos/list.json", null);
        System.out.println(result);

        JSONObject json = new JSONObject(result);
        JSONArray data = json.getJSONArray("result");

        // testing
        assertNotNull("Answer should not be null", result);
        assertTrue("Code is not 200", json.getInt("code") == 200);
        assertTrue("Array should be empty or >1 ", data.length() >= 0);
    }
}
