package jp.cafebabe.pochi.utils.spi;

public class NoDefaultConstructorServiceImpl2 extends NoDefaultConstructorService {
    public NoDefaultConstructorServiceImpl2(String string) {
        super(string);
    }

    public String type(){
        return "service2-2";
    }
}
