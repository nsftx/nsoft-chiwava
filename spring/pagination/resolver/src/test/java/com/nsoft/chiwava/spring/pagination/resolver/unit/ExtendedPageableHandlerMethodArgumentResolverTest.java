package com.nsoft.chiwava.spring.pagination.resolver.unit;

import com.nsoft.chiwava.spring.pagination.resolver.ExtendedPageableHandlerMethodArgumentResolver;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.jpa.domain.JpaSort;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ExtendedPageableHandlerMethodArgumentResolverTest {

    @Test
    public void shouldCreateSort()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String[] properties = {"up", "+explicitUp", "-down"};

        ExtendedPageableHandlerMethodArgumentResolver resolver = new ExtendedPageableHandlerMethodArgumentResolver();

        Method method = resolver.getClass().getDeclaredMethod("buildSort", properties.getClass());
        method.setAccessible(true);

        JpaSort sort = (JpaSort) method.invoke(resolver, new Object[]{properties});
        Assert.assertNotNull(sort);
    }
}
