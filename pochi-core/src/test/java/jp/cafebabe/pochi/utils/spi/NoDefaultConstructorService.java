package jp.cafebabe.pochi.utils.spi;

public abstract class NoDefaultConstructorService {
    private String string;

    public NoDefaultConstructorService(String string){
        this.string = string;
    }

    public abstract String type();

    public String string(){
        return string;
    }
}