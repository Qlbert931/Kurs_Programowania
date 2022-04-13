import java.util.*;

public class Test
{
    public static void main(String[] args)
    {
        FigureFactory figureFactory;
        try
        {
            figureFactory = new FigureFactory(args);
        }
        catch(WrongFirstArgumentException e){
            System.out.println("Nieprawdiłowe wywołanie funkcji, " + e.OnWhich() + " nie jest akceptowalnym rodzajem figury, akceptowalne to 'o', 'p', 's', 'c'");
            return;
        }
        catch(WrongAmountOfArgumentsException e){
            if(e.TheDiffrence() > 0)
                System.out.println("Nieprawdiłowe wywołanie funkcji, o " + e.TheDiffrence() + " za mało argumentów");
            else
                System.out.println("Nieprawdiłowe wywołanie funkcji, o " + e.TheDiffrence() + " za dużo argumentów");
            return;
        }
        catch(NumberFormatException e){
            System.out.println("Nieprawdiłowe wywołanie funkcji, argumenty od drugiego powinny być liczbami");

            return;
        }
        catch(TooLowArgumentException e){
            System.out.println("Nieprawdiłowe wywołanie funkcji, argumenty od drugiego powinny być większe od " + e.whatIsExlusionMin());
            return;
        }
        
        while(figureFactory.IsNext())
        {
            try
            {
                Figure f = figureFactory.CreateNextFigure();
                System.out.println(f.NameYourself() + " ma pole " + f.Area() + " oraz obwód " + f.Perimeter());
            }
            catch(UnknowQuadrangle e){
                Vector<Double> unknow = e.getUnknow(); 
                System.out.print("Nierozpoznany czworokąt,");
                for(Double d : unknow)
                    System.out.print(" " + d);
                System.out.println(" nie jest ani kwadratem, ani prostokątem, ani rombem");
            }
        }
    }
}
