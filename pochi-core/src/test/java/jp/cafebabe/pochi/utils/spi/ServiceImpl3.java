package jp.cafebabe.pochi.utils.spi;

public class ServiceImpl3 implements Service {
    @Override
    public Type type() {
        return Service.Type.of("service3");
    }

    @Override
    public String description() {
        return "";
    }
}
