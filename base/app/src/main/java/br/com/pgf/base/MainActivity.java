package br.com.pgf.base;

import android.os.Bundle;

import br.com.pgf.androdbase.view.ActivityView;
import br.com.pgf.androdbase.view.BaseActivity;

public class MainActivity extends BaseActivity<MainHolder> implements ActivityView {

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
   }

   @Override
   public void mapUI() {
      holder = new MainHolder(this);
      holder.myText.setText("Iniciando o teste da api base");
   }
}
