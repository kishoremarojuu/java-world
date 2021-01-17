package com.myproject.json_to_java;

import org.json.JSONException;
import org.json.JSONObject;

public class WorkingWithJSON_Java {

    //json parsing
    public static void main(String[] args) {

        String response="{\"apiStatus\":\"SUCCESS\",\"elapseTimeMillis\":0,\"message\":\"User Type\",\"data\":{\"userId\":854793,\"userName\":\"UNITEDSTATESINDUST1\",\"isAnonymous\":false,\"isB2BMasterUser\":false,\"userType\":\"b2c\",\"isCrmManager\":false,\"isSosManager\":false}}";
        try {
            JSONObject jsonObject = new JSONObject(response);

            String userNameFromAPUI = jsonObject.getJSONObject("data").getString("userName");
            System.out.println(userNameFromAPUI);
        } catch (JSONException var14) {
            var14.printStackTrace();
        }

    }

}
