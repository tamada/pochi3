package jp.cafebabe.pochi.utils;

import jp.cafebabe.birthmarks.utils.Stringer;
import jp.cafebabe.pochi.utils.spi.NoImplementationService;
import jp.cafebabe.pochi.utils.spi.Service;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceBuilderFactoryTest {
    @Test
    public void testLoadSuccess() {
        var builder = new ServiceBuilderFactory<>(Service.class);
        var list = builder.builders().toList();
        assertEquals(3, list.size());

        assertEquals("jp.cafebabe.pochi.utils.spi.ServiceImpl1", list.get(0).getClass().getName());
        assertEquals("service1", list.get(0).type().string());

        assertEquals("jp.cafebabe.pochi.utils.spi.ServiceImpl2", list.get(1).getClass().getName());
        assertEquals("service2", list.get(1).type().string());

        assertEquals("jp.cafebabe.pochi.utils.spi.ServiceImpl3", list.get(2).getClass().getName());
        assertEquals("service3", list.get(2).type().string());

        assertEquals(3, builder.availables().count());
    }

    @Test
    public void testLoadFailedWithNoImplementation() {
        var builder = new ServiceBuilderFactory<>(NoImplementationService.class);
        var list = builder.builders().toList();

        assertEquals(0, list.size());
    }
}
