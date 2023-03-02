package com.example.presentationExample2;

class Gen1<T> {

    private T ob;

    public Gen1(T o) {
        ob = o;
    }

    public T getOb() {
        return ob;
    }

    public void setOb(T o) {
        this.ob = o;
    }

}

class Gen2 {

    private Object ob;

    public Gen2(Object o) {
        ob = o;
    }

    public Object getOb() {
        return ob;
    }

    public void setOb(Object o) {
        this.ob = o;
    }
}


public class Main {

    public static void main(String[] args) {
        Gen1<String> myGen1 = new Gen1<>("hello");
//        nem rakhatunk myGen1-be nem Stringet, mert myGen1 egy olyan referencia, ami Stringet tarolo Gen1 peldanyokra kepes referalni
//        myGen1.setOb(1);

//        nem kell explicit tipuskonverzio, mert ezesetben getOb() String-gel ter vissza, myGen1Property pedig String referencia
        String myGen1Property = myGen1.getOb();

        Gen2 myGen2 = new Gen2("hello");
//        rakhatunk myGen2-be nem Stringet, mert myGen2 egy olyan referencia, ami Object tipust tarol, es autowrapping miatt 1, object lesz
        myGen2.setOb(69);

//        explicit tipuskonverzio szukseges, mert a getOb() metodus Object-tel ter vissza, myGen2Property pedig Integer //downcasting
        Integer myGen2Property = (Integer) myGen2.getOb();

//        upcasting takes place automatically
        Integer myInt1 = 69;
        Number myIntNumber = myInt1;
//        downcasting needs explicit conversion, else compilie-time error will result
        Integer myInt2 = 69;
        Object myIntObject = myInt2;
        myInt2 = (Integer) myIntObject;
    }
}




