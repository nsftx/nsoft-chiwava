package com.nsoft.chiwava.core.commons.unit.json;

import com.nsoft.chiwava.core.commons.json.JsonMapper;
import lombok.Getter;
import lombok.Setter;
import org.junit.Assert;
import org.junit.Test;

public class JsonMapperTest {

    private static final JsonMapper DEFAULT_MAPPER = new JsonMapper();

    @Test
    public void shouldSerializePOJO() {
        String json = DEFAULT_MAPPER.toJson(new POJO());

        Assert.assertEquals(POJO.JSON, json);
    }

    @Test
    public void shouldDeserializePOJO() {
        POJO pojo = DEFAULT_MAPPER.fromJson(POJO.JSON, POJO.class);

        Assert.assertEquals(new POJO(), pojo);
    }

    @Getter
    @Setter
    private static class POJO {
        public static final String JSON = "{\"value1\":\"1\",\"value2\":2,\"value3\":3,\"value4\":4.0,\"value5\":5.0}";

        private String value1 = "1";
        private int value2 = 2;
        private long value3 = 3L;
        private float value4 = 4f;
        private double value5 = 5d;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            POJO pojo = (POJO) o;

            if (value2 != pojo.value2) return false;
            if (value3 != pojo.value3) return false;
            if (Float.compare(pojo.value4, value4) != 0) return false;
            if (Double.compare(pojo.value5, value5) != 0) return false;
            return value1.equals(pojo.value1);
        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            result = value1.hashCode();
            result = 31 * result + value2;
            result = 31 * result + (int) (value3 ^ (value3 >>> 32));
            result = 31 * result + (value4 != +0.0f ? Float.floatToIntBits(value4) : 0);
            temp = Double.doubleToLongBits(value5);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }
}
