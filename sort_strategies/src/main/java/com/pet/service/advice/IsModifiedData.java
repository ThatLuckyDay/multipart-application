package com.pet.service.advice;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class IsModifiedData extends DelegatingIntroductionInterceptor implements IsModified {
    private boolean isModified = false;
    private Map<Method, Method> methods = new HashMap<>();

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        if (!this.isModified()) {
            if (mi.getMethod()
                    .getDeclaringClass()
                    .getName()
                    .startsWith("set")) {
                Method getter = getGetter(mi.getMethod());
                if (getter != null) {
                    Object newValue = mi.getArguments()[0];
                    Object oldValue = getter.invoke(mi.getThis(), null);
                    if (newValue == null && oldValue == null) {
                        this.isModified = false;
                    } else if ((newValue == null) && (oldValue != null)) {
                        this.isModified = true;
                    } else if (newValue != null && oldValue == null) {
                        this.isModified = true;
                    } else {
                        this.isModified = !newValue.equals(oldValue);
                    }
                }
            }
        }
        return super.invoke(mi);
    }

    private Method getGetter(Method setter) {
        Method getter = null;

        getter = this.methods.get(setter);

        if (getter != null) {
            return getter;
        }
        String getterName = setter.getName().replaceFirst("set", "get");
        try {
            getter = setter.getDeclaringClass().getMethod(getterName, null);
            synchronized (this.methods) {
                this.methods.put(setter, getter);
            }
            return getter;
        } catch (NoSuchMethodException exception) {
            return null;
        }
    }

}
