package org.apache.cordova;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

@Deprecated
public class JSONUtils {
    public static List<String> toStringList(JSONArray array) throws JSONException {
        if (array == null) {
            return null;
        }
        List<String> list = new ArrayList();
        for (int i = 0; i < array.length(); i++) {
            list.add(array.get(i).toString());
        }
        return list;
    }
}
