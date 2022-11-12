import java.util.*;

class Complex {
    private int re, im;

    public Complex(int re, int im) {
        this.re = re;
        this.im = im;
    }

    public Complex() {
        this(0, 0);
    }

    public Complex(Complex nr) {
        this.re = nr.re;
        this.im = nr.im;
    }

    public int getReal() {
        return re;
    }

    public void setReal(int re) {
        this.re = re;
    }

    public int getImaginary() {
        return im;
    }

    public void setImaginary(int im) {
        this.im = im;
    }

    public void addWithComplex(Complex nr) {
        this.re += nr.re;
        this.im += nr.im;
    }

    public void showNumber() {
        if (this.im > 0)
            System.out.println(this.re + " + i * " + this.im);
        else if (this.im < 0)
            System.out.println(this.re + " - i * " + -this.im);
        else
            System.out.println(this.re);
    }
}
