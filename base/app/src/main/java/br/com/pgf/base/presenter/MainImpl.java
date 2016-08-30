package br.com.pgf.base.presenter;

import br.com.pgf.androdbase.Presenter;

/**
 * Created by peterson on 29/08/2016.
 */
public class MainImpl extends Presenter<Void, Main.View> implements Main.Presenter {


   @Override
   public void soma() {
      getView().notify(5);
   }
}
