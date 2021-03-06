/*Jason Hammond
  CSCI 4100 - Assignment 1
  Dr. Cueva-Parra

This is a class file for addition, subtraction, multiplication, and
division of complex numbers in the form (A + Bi). Designed for use
with ComplexCalc.java.
----------------------------------------------------------------------*/

import java.lang.Math;

public class Complex{
    Complex soln, numer, denom;
    private double real, imag;
	
    public Complex(){
        real = 0.0;
        imag = 0.0;
    }//end Complex class

    public double getReal(){
        return real;
    }//end returnReal

    public double getImag(){
        return imag;
    }//end returnImag

    public void changeReal(double a){
        real = a;
    }//end changeReal

    public void changeImag(double a){
        imag = a;
    }//end changeImag

    public Complex complexAdd(Complex t){
        soln = new Complex();
        //solution
        soln.changeReal(real + t.getReal());
        soln.changeImag(imag + t.getImag());
        return soln;
    }//end ComplexAdd

    public Complex complexSubtract(Complex t){
        soln = new Complex();
        //solution
        soln.changeReal(real - t.getReal());
        soln.changeImag(imag - t.getImag());
        return soln;
    }//end ComplexSubtract

    public Complex complexMult(Complex t){
        soln = new Complex();
        //solution
        soln.changeImag((imag * t.getReal()) + (real * t.getImag()));
        soln.changeReal((real * t.getReal()) + (imag * t.getImag() * (-1)));
        return soln;
    }//end ComplexMult

    public Complex complexDiv(Complex t){
        soln = new Complex();
        numer = new Complex();
        denom = new Complex();
        //numerator
        numer.changeImag((real * (-1 * t.getImag())) + (imag * t.getReal()));
        numer.changeReal(real * t.getReal() + (imag * (-1 * t.getImag()) * -1));

        //denominator
        denom.changeImag((t.getReal() * t.getImag()) + (t.getReal() * (-1 * t.getImag())));
        denom.changeReal(t.getReal() * t.getReal() + (t.getImag() * (-1 * t.getImag()) * -1));

        //solution
        soln.changeReal(numer.getReal() / denom.getReal());
        soln.changeImag(numer.getImag() / denom.getReal());
        return soln;
    }//end ComplexDiv

}//end class Complex
