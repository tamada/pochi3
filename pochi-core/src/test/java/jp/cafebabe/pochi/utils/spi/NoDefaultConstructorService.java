package jp.cafebabe.pochi.utils.spi;

public abstract class NoDefaultConstructorService {
    private final String string;

    public NoDefaultConstructorService(String string){
        this.string = string;
    }

    public abstract String type();

    public String string(){
        return string;
    }
}