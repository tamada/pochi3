package jp.cafebabe.pochi.utils.spi;

public class ServiceImpl2 implements Service {
    @Override
    public Service.Type type() {
        return Service.Type.of("service2");
    }

    @Override
    public String description() {
        return "";
    }
}
