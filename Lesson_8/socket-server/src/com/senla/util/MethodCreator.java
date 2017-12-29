package com.senla.util;

import com.senla.enums.ResponseStatus;
import com.senla.view.facade.OnlineBookStore;

import java.lang.reflect.Method;
import java.util.List;

public class MethodCreator {
    public static ServerResponse execute(ClientRequest request) throws NoSuchMethodException {
        ServerResponse response = new ServerResponse();
        List<Object> args = request.getArguments();
        Class[] types = getArrayOfTypes(args);
        Method method = OnlineBookStore.class.getMethod(request.getMethod(), types);
        try {
            Object invoke;
            if (args == null || args.isEmpty()) {
                invoke = method.invoke(OnlineBookStore.getInstance());
            } else {
                invoke = method.invoke(OnlineBookStore.getInstance(), args.toArray());
            }
            response.setResponse((String) invoke);
            response.setStatus(ResponseStatus.Success);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return response;
    }

    private static Class[] getArrayOfTypes(List<Object> list) {
        Class[] types = new Class[list.size()];
        for (int i = 0; i < list.size(); i++) {
            types[i] = list.get(i).getClass();
        }
        return types;
    }

}
