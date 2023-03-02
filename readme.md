# Generikusok

##  Mit nyerunk a generikusokkal?

1. Explicit tipuskonverzio elkerulese
2. Futasido helyett mar forditasidoben kiderulnek a tipuskonverzioval kapcsolatos hibak
3. Típus függetlenül tudunk létrehozni adattároló szerkezeteket, algoritmusokat

## Bevezeto

- JDK 5 (2004) vezette be
- uj szintaktikai elemek (pl.: wildcard ?, Java 7-tol diamond <>)
- Java osztalyainak, metodusainak atirasat vonta maga utan
- lehet osztaly, interfesz, metodus es konstruktor

## Generikus osztaly:
> Egy osztaly generikus, ha *egy vagy tobb* tipusvaltozot deklaral.<br> Ezeket a tipusvaltozokat **tipus parametereknek** nevezzuk.


**szintaxis**:
```java
class class-name<type-param-list> {...}
```
**tipus parameter scope**-ja: osztalydefinicio blokkja

```java
class Gen<T> {

    private T ob;

    public Gen(T o) {
        ob = o;
    }

    public T getOb() {
        return ob;
    }

    public void setOb(T o) {
        this.ob = o;
    }
}
```

```java
class Gen {

    private Object ob;

    public Gen(Object o) {
        ob = o;
    }

    public Object getOb() {
        return ob;
    }

    public void setOb(Object o) {
        this.ob = o;
    }
}
```

### tipus torles (type erasure)

A generikusok implementalasahoz illetve a visszafele-kompatibilitas biztositasahoz a Java compiler type erasure-t hasznal.

- kicserel minden tipus parametert a korlatjaval vagy Object tipussal, hogyha nincs korlatja
- a szukseges cast-kat beilleszti
- a polimorfizmus megorzesenek erdekeben a szukseges bridge metodusokat legeneralja

## Nyers tipusok

- generikusok nem leteztek JDK 5 elott
- szukseges volt egy olyan megoldas, ami eleri, hogy pre-JDK5 elotti kod mukodokepes maradjon, de ugyanakkor kompatibilis legyen a generikusokkal is
- ezt az un. **raw** (nyers) tipusok megengedesevel erte el, ami azt jelenti, hogy lehetoseg van generikus osztaly hasznalatara, anelkul, hogy barmilyen tipus argumentumot megadnank.
- **hatranya**: a compiler nem tudja ellenorizni a tipusok helyesseget, elveszitjuk a type-safety-t


## Tipus parameter

- a tipus parameter scope-ja a deklaralas helyetol fugg
- < > kozott kell deklaralni oket
- nincs jelentosege a tipus parameter nevenek
- nevkonvenciok:
- nevkonvenciok:
    - E: Element (Collections Framework hasznalja)
    - K: Key
    - N: Number
    - T: Type
    - V: Value
    - S, U, etc.
- JDK 10: tipous parameter nem lehet **var**
- az argumentumok csak referenciatipusok lehetnek pl: Integer, List<Integer>, int[]
- ha tobb tipusparametert akarunk deklaralni, azokat vesszovel valsztjuk el

### Tipus parameter - tipushalmaz korlatozasa

Az oroklodes jelenseget felhasznalhatjuk arra, hogy korlatozzuk a behelyettesitheto tipusok halmazat.

**szintaxis**:
```java
<T extends superclass>
```
Ez azt jelenti, hogy T csak superclassal, vagy annak valamelyik gyerekosztalyaval helyettesitheto, igy superclass egy inkluziv felso korlatot hataroz meg.

- a korlat csak osztaly vagy interfesz lehet
- csak felso korlatot lehet szabni, alsot nem
- csak konkret tipusok lehetnek a korlatok, kulonben fel kell sorolnunk azok tipus parameteret
- tobb korlatot is szabhatunk
    - & operator
    - csak 1 osztaly lehet
    - osztalyt kell elsokent deklaralni
- ha a superclass generikus es nincs konkret tipus argumentuma, akkor a tipus parameteret fel kell vennunk a tipus parameter listaba

## Wildcard argumentum
Generikus kodban a ?-et wildcard-nak nevezzuk es **ismeretlen tipus**-t jelent.

- wildcard-ot hasznalo generikusok elofordulhatnak
    - parameterekkent (pl metodusban)
    - osztalyvaltozokkent
    - lokalis valtozokkent
- wildcard **nem** fordulhatnak elo
    - generikus metodus-hivasoknal, mint tipus argumentum
    - generikus osztaly peldanyositasakor
    - szuloosztaly deklaralasakor

### Wildcard argumentum - tipushalmaz korlatozasa

Az oroklodes jelenseget felhasznalhatjuk arra, hogy korlatozzuk a behelyettesitheto tipusok halmazat.

- a korlat csak osztaly vagy interfesz lehet
- csak meghatarozott tipusokat hasznalhatunk
- nem lehet tobbszoros korlatot definialni
- a korlat lehet **also** vagy **felso** korlat

#### Felso korlat
**szintaxis**:
```java
<? extends superclass>
```
Ez azt jelenti, hogy a kerdojel csak superclass vagy annak egy alosztalyanak tipusa lehet, igy a superclass valojaban egy inkluziv felso korlatot hataroz meg.

#### Also korlat
**szintaxis**:
```java
<? super subclass>
```
Ez azt jelenti, hogy a kerdojel csak subclass vagy annak egy szuloosztalyanak tipusa lehet, igy a subclass valojaban egy inkluziv also korlatot hataroz meg.

## Generikus metodus:
> Egy metodus generikus, ha *egy vagy tobb* tipusvaltozot deklaral.<br> Ezeket a tipusvaltozokat **formalis tipus parametereknek** nevezzuk.<br><br>
> /A generikus metodusok olyan metodusok, amik sajat tipus parametert deklaralnak./

**szintaxis**:
```java
<formal-type-param-list > ret-type meth-name (param-list) {…}
```
**formalis tipus parameter scope**-ja: metodusdefinicio blokkja

- ugyanazok a tipus parameter szabalyok ervenyesek, mint a fent irtak
- generikus es nem-generikus osztalyban is deklaralhatoak
- metodushivasnal kulon meg tudjuk adni a tipus argumentumokat pl:
```java
class StringLengthChecker {
  public static <K, V> int compareStringLength(K ob1,V ob2){
    Integer ob1Length=ob1.toString().length();
    Integer ob2Length=ob2.toString().length();
    return ob1Length.compareTo(ob2Length);
  }
}

//metodushivasnal
StringLengthChecker.<Integer, String>compareStringLength(69, ”ok”)
```

## Generikus konstruktor
> Egy konstruktor generikus, ha *egy vagy tobb* tipusvaltozot deklaral.<br> Ezeket a tipusvaltozokat **formalis tipus parametereknek** nevezzuk.<br><br>
> /A generikus konstruktorok olyan konstruktorok, amik sajat tipus parametert deklaralnak./

**szintaxis**:
```java
<formal-type-param-list > constructor-name (param-list) {…}
```
**formalis tipus parameter scope**-ja: konstruktor definicio blokkja

- ugyanazok a tipus parameter szabalyok ervenyesek, mint a fent irtak
- generikus es nem-generikus osztalyban is deklaralhatoak
- konstruktor hivasakor nem lehet kulon megadni a tipus argumentumokat

```java
class A {
    private double val;
    
    <T extends Number> A(T arg){
        val = arg.doubleValue();
    }
    
    void showVal(){
      System.out.println("val: " + val);
    }
}
```

## Oroklodes

Generikusok definialasakor minden olyan tipus argumentumot tovabb kell adnia a gyerekosztalynak, amire a szuloosztalynak vagy interfasznek szuksege van.

- csak a nem konkretan megadott tipust kell tovabbadni
- hogyha a tovabbadni kivant argumentm egy korlatolt argumentum, akkor az oroklonek definialnia kell a korlatot. Csak egyszer lehet ezt a korlatot definialni.

## Generikus interfesz

> Egy interfesz generikus, ha *egy vagy tobb* tipusvaltozot deklaral.<br> Ezeket a tipusvaltozokat **tipus parametereknek** nevezzuk.

**szintaxis**:
```java
interface interface-name<type-param-list> {…}
```
**tipus parameter scope**-ja: interfesz definicio blokkja

- ugyanazok a tipus parameter szabalyok ervenyesek, mint a fent irtak

```java
public interface List<E> extends Collection<E>{}
```

## Tipus kovetkeztetes

> A tipus kovetkeztetes a Java compiler-nek az a kepessege, hogy adattipusokat tud kikovetkeztetni a kapcsolodo deklaraciok alapjan.

- JDK 7 bevezette a gyemant operatort <>
    - JDK 7 elott:
  ```java
    class-name<type-arg-list> var-name = new class-name <same-type-arg-list>(cons-arg-list);
  ``` 
    - JDK 7 utan:
  ```java
    class-name<type-arg-list > var-name = new class-name <>(cons-arg-list);
  ```
- JDK 10 bevezette a **var** kulcsszot
    - JDK 10 utan:
  ```java
    var var-name = new class-name <type-arg-list>(cons-arg-list);
  ```

## Felreerthetosegbol fakado hiba

> Felreerthetosegi hiba akkor adodik, amikor a tipus torlesbol (type erasure) fakadoan ket latszolag eltero generikus deklaracio ugyanarra a torolt tipusra ertekelodik ki, igy okozva forditasidobeli hibat.

- tobbnyire metodusok overloadolasakor tortenhet ilyesmi<br>
  (azonos nev, azonos osztaly vagy interfesz, eltero szignatura)

## Nehany megkotes

- nem lehet tipus parametert peldanyositani
- nem lehetseges olyan tomb peldanyositasa, mely elemeinek tipusa tipus parameter
- nem lehet olyan generikus referenciat tartalmazo tombot letrehozni, melynek tipus parametere meghatarozott
- static tag nem hasznalhat az osztalya vagy interfesze altal deklaralt tipus parametert
- generikus osztaly nem orokolhet a Throwable osztalytol
