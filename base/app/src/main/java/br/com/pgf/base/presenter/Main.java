package br.com.pgf.base.presenter;

/**
 * Created by peterson on 29/08/2016.
 */
public class Main {

   public interface Presenter {
      void soma();
   }

   public interface View{
      void notify(final int result);
   }

}
