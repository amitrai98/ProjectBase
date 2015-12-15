package com.zenix.me.baseproject.Activities.Backend;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.zenix.me.baseproject.Activities.App.AppInitalizer;
import com.zenix.me.baseproject.Activities.App.Constants;
import com.zenix.me.baseproject.Activities.Util.AppLogger;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by me on 15-Dec-15.
 */
public class NetworkHelper {


    private final String TAG = NetworkHelper.class.getSimpleName();

    //todo  email field for the api change it in accordance to yours
    private final String EMAIL_FIELD = "EMAIL_ID";

    //todo  password field for the api change it in accordance to yours
    private final String PASSWORD_FIELD = "PASSWORD";

    // todo  token field change it according to your requirement
    private final String TOKEN_FIELD = "token";


    public interface OnLoginCallback {
        public void onLoggedIn(String token);
    }

    public interface OnErrorCallback {
        public void onError(String error);
    }

    /**
     * todo change it as per requirement
     * loging method
     * @param email
     * @param password
     * @param callback
     */
    public void login(String email, String password, @NonNull final OnLoginCallback callback) {

        JSONObject object = new JSONObject();
        try {
            object.put(EMAIL_FIELD, email);
            object.put(PASSWORD_FIELD, password);
        } catch (JSONException e) {
            AppLogger.e(TAG, e.toString());
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                Constants.URL,
                object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        try {
                            Log.d(TAG, "Login response:\n" + jsonObject.toString(4));

                            String token = jsonObject.getString(TOKEN_FIELD);
                            callback.onLoggedIn(token);

                        } catch (JSONException e) {
                            mOnErrorCallback.onError(e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        String message = ErrorParser.parseVolleyError(volleyError);
                        Log.e(TAG, "Login error: " + message);
                        mOnErrorCallback.onError(message);
                    }
                }
        );
        request.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 8,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppInitalizer.getInstance().addToRequestQueue(request, TAG);
    }


    private OnErrorCallback mOnErrorCallback = new OnErrorCallback() {
        @Override
        public void onError(String error) {
            Log.e(TAG, error);
        }
    };
}
