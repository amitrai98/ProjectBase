package com.zenix.me.baseproject.Activities.Backend;

import com.android.volley.VolleyError;
import com.zenix.me.baseproject.Activities.App.AppInitalizer;
import com.zenix.me.baseproject.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.UnknownHostException;


/**
 * Created by sergey on 07.04.15.
 */
public class ErrorParser {

    public static String parseVolleyError(VolleyError volleyError) {

        String message;

        if (volleyError.networkResponse != null) {
            message = new String(volleyError.networkResponse.data);
        } else {
            if (volleyError.getCause() instanceof UnknownHostException) {
                message = AppInitalizer.getContext().getString(R.string.error_message_no_internet_connection);
            } else {
                message = volleyError.getMessage();
            }
        }
        return message == null ? AppInitalizer.getContext().getString(R.string.error_message_standard_connection_error) : message;
    }

    public static JSONObject parseJSONError(String jsonError) throws JSONException {

        return new JSONObject(jsonError);
    }

    public static boolean hasInvalidToken(JSONObject jsonError) throws JSONException {

        return jsonError.has("detail") && jsonError.getString("detail").equals("Invalid token.");
    }
}
