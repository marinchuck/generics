package com.example.presentationExample3;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        Number myNumber;
        Integer myInteger = 69;
//        upcasting
        myNumber = myInteger;
//        downcasting
        myInteger = (Integer) myNumber;

//        Why is it possible?
//        Because there is inheritance between Integer and Number class!

//        Does it work here?
        List<Number> myNumberList;
        List<Integer> myIntegerList = List.of(69);
//        Nope!, because myNumberList is of type List<Number> and myIntegerList is of type List<Integer>, which are two different types, there is no inheritance between them!
//        myNumberList = myIntegerList;
//        myIntegerList = (List<Integer>)myNumberList;

//        What if i really want a new reference?
//        Suppose that we have a method, which takes a List<Number> and
//          returns a List<Integer> from it if the number of the elements is even, and
//          returns a List<Double> from it if the number of the elements is odd.
//        How can I save the return value in a reference?

//        Vaskoek! Nem Object-be rakjuk!!!

        List myNumberList2 = myIntegerList;
        List<?> myNumberList3 = myIntegerList;

//        Difference according to
//        https://stackoverflow.com/questions/1351299/whats-the-difference-between-unbounded-wildcard-type-list-and-raw-type-list
//        and
//        https://stackoverflow.com/questions/14983489/difference-between-list-and-list

//        List: A list with no type parameter. It is a list whose elements are of any type -- the elements may be of different types.
//        List<?>: A list with an unbounded type parameter. Its elements are of a specific, but unknown, type; the elements must all be the same type.

//        Then why it is allowed?
        myNumberList3 = myNumberList2;

//        Don't code with raw types any more . They are basically supported for backward compatibility!!!

//        wildcard can be bounded too
        List<? extends Number> myNumberList4 = myIntegerList;
//        List<?> == List<? extends Object>

    }
}