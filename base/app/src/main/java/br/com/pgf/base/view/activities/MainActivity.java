package br.com.pgf.base.view.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import br.com.pgf.androdbase.view.ActivityView;
import br.com.pgf.androdbase.view.BaseActivity;
import br.com.pgf.base.view.holder.MainHolder;
import br.com.pgf.base.R;

public class MainActivity extends BaseActivity<MainHolder>{

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
   }



   @Override
   public void mapUI() {


      holder = new MainHolder(this);
      holder.myText.setText("Iniciando o teste da api base");
      holder.centerImage.setImageResource(R.drawable.ic_launcher);
      startRevelAnimation(holder.getViewGroup(), holder.revel, 0, 0, 28f);
      holder.centerImage.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                  .setMessage("Testando animação")
                  .setPositiveButton("Teste Botao", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                     }
                  })
                  .create();
            alertDialog.getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;
            alertDialog.show();
         }
      });
      this.overridePendingTransition(R.anim.fade_in_center, R.anim.fade_out_center);
   }
}
