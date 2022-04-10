package jp.cafebabe.pochi.utils.spi;

public class ServiceImpl1 implements Service {
    @Override
    public Service.Type type() {
        return Service.Type.of("service1");
    }

    @Override
    public String description() {
        return "";
    }
}
