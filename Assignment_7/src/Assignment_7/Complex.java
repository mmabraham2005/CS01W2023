package Assignment_7;

public class Complex implements Cloneable, Comparable<Complex>
{
   private double a; 
   private double b; 
   
   public Complex(double a, double b)
   {
      this.a = a;
      this.b = b;
   }
   public Complex(double a)
   {
      this.a = a;
      this.b = 0.0;
      
   }
   public Complex()
   {
      this.a = 0.0;
      this.b = 0.0;
   }
   @Override
   public int compareTo(Complex o)
   {
      // TODO Auto-generated method stub
      return 0;
   }
   public Complex add(Complex second) 
   {
      //a + b i + c + d i = ( a + c ) + ( b + d ) i
      double newReal = this.a + second.getRealPart();
      double newImaginary = this.b + second.getImaginaryPart();
      Complex newComplex = new Complex(newReal,newImaginary);
      return newComplex;
   }
   public Complex subtract(Complex second) 
   {
      //a + b i − ( c + d i ) = ( a − c ) + ( b − d ) i
      double newReal = this.a - second.getRealPart();
      double newImaginary = this.b - second.getImaginaryPart();
      Complex newComplex = new Complex(newReal,newImaginary);
      return newComplex;  
   }
   public Complex multiply(Complex second) 
   {
      //( a + b i ) * ( c + d i ) = ( a c − b d ) + ( b c + a d ) i
      double newReal = (this.a * second.getRealPart()) - 
            (this.b * second.getImaginaryPart());
      double newImaginary = (this.b * second.getRealPart() + 
            (this.a * second.getImaginaryPart()));
      Complex newComplex = new Complex(newReal,newImaginary);
      return newComplex;  
   }
   public Complex divide(Complex second) 
   {
      //( a + b i ) / ( c + d i ) = 
      //( a c + b d ) / ( c2 + d2 ) + ( b c − a d ) i / ( c2 + d2 )
      double divideDouble = ((Math.pow(second.getRealPart(), 2)) 
            + (Math.pow(second.getImaginaryPart(), 2)));
      double newReal = ((this.a * second.getRealPart()) + 
            (this.b * second.getImaginaryPart())) / divideDouble;
      double newImaginary = (this.b * second.getRealPart() - 
            (this.a * second.getImaginaryPart())) / divideDouble;
      Complex newComplex = new Complex(newReal,newImaginary);
      return newComplex;  
   }
   public double abs() 
   {
      return Math.sqrt((Math.pow(this.a, 2)) + (Math.pow(this.b, 2)));
   }
   public double getRealPart() 
   {
      return this.a;
   }
   public double getImaginaryPart() 
   {
      return this.b;
   }
   public String toString() 
   {
      String formatComplex = Double.toString(this.a);
      if(this.b == 0) 
      {
         return formatComplex;
      }
      formatComplex = "(" + Double.toString(this.a) + " + " 
            + Double.toString(this.b) + " i)";
      return formatComplex;
   }
}
