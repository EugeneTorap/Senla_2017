package com.senla.util;

import com.senla.view.facade.OnlineBookStore;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodInvoker {
    static public Map<String, Object> getResponse(Map<String, List<Object>> request) throws Exception {
        Map<String, Object> response = new HashMap<>();
        if (request != null) {
            String stringMethod = request.keySet().toArray()[0].toString();
            List<Object> objectList = request.get(stringMethod);
            Method method = OnlineBookStore.class.getMethod(stringMethod, getTypes(objectList));
            Object invokedMethod;
            if (objectList == null || objectList.isEmpty()) {
                invokedMethod = method.invoke(OnlineBookStore.getInstance());
            } else {
                invokedMethod = method.invoke(OnlineBookStore.getInstance(), objectList.toArray());
            }
            response.put("response", invokedMethod);
        }
        return response;
    }

    private static Class[] getTypes(List<Object> list) {
        Class[] types = new Class[list.size()];
        for (int i = 0; i < list.size(); i++) {
            types[i] = list.get(i).getClass();
        }
        return types;
    }
}
