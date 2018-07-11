package com.zhiguang.li.utils;

/**
 * 归并排序
 */
public class Guibing {

    private static Integer[] string = {2,4,5,4,7,9,12,4,9,1};
    private static Integer[] string1;

    public static void main(String[] strings) {
        mergeSort(string);
        for (int i = 0; i < string.length; i++) {
            System.out.print(string[i]);
        }


    }

    private static <AnyType extends Comparable<? super AnyType>> void mergeSotr(AnyType[] a, AnyType[] tempArray, int left, int right) {
        if (left < right) {
            int canter = (left + right) /2;
            mergeSotr(a,tempArray,left,canter);
            mergeSotr(a,tempArray,canter + 1,right);
            merge(a,tempArray,left,canter+1,right);


        }
    }


    private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType [] a){
        AnyType[] tmpArray = (AnyType[]) new Comparable[a.length];
        mergeSotr(a,tmpArray,0,a.length-1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType [] a,AnyType [] tmpArray,int leftPos,int rightPos,int rightEng){
        int leftEnd = rightPos -1;
        int tmpPos = leftPos;
        int numElements = rightEng - leftPos + 1;

        //main loop
        while (leftPos <= leftEnd && rightPos <= rightEng){
            if(a[leftPos].compareTo(a[rightPos])<0){
                tmpArray[tmpPos++] = a[leftPos++];
            }else{
                tmpArray[tmpPos++] = a[rightPos++];
            }
        }
        //copy rest of frist half
        while (leftPos <= leftEnd && rightPos <= rightEng){
            tmpArray[tmpPos++] = a[leftPos++];
        }
        //copy rest of right half
        while (leftPos <= leftEnd){
            tmpArray[tmpPos++] = a[rightPos++];
        }
//        Copy tmpArray Back
        for (int i = 0; i < numElements; i++) {
            a[rightEng] = tmpArray[rightEng];
        }
    }

}
