package me.openphoto.client;

import junit.framework.TestCase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ListAlbumTest extends TestCase {

    public void testGetAlbums() throws JSONException {
        OpenPhotoClient client = new OpenPhotoClient();

        String result = client.rawGet("/albums/list.json", null);
        System.out.println(result);
        
        JSONObject json = new JSONObject(result);
        JSONArray data = json.getJSONArray("result");

        // testing
        assertNotNull("Answer should not be null", result);
        assertTrue("Code is not 200", json.getInt("code") == 200);
        assertTrue("Array should be empty or >1 ", data.length() >= 0);

    }

    public void testGetAlbumsNoOAuth() throws JSONException {
        OpenPhotoClient client = new OpenPhotoClient();

        String result = client.rawGetNoAuth("/albums/list.json", null);
        System.out.println(result);

        JSONObject json = new JSONObject(result);
        JSONArray data = json.getJSONArray("result");

        // testing
        assertNotNull("Answer should not be null", result);
        assertTrue("Code is not 200", json.getInt("code") == 200);
        assertTrue("Array should be empty or >1 ", data.length() >= 0);

    }

}
