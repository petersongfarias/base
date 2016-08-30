package br.com.pgf.base.view.holder;

import android.widget.ImageView;
import android.widget.TextView;

import br.com.pgf.androdbase.anim.Animations;
import br.com.pgf.androdbase.holder.ActivityHolder;
import br.com.pgf.androdbase.view.anotations.Bind;
import br.com.pgf.androdbase.view.interact.ActivityInteract;
import br.com.pgf.androdbase.view.widget.RevealBackgroundRelativeLayout;
import br.com.pgf.base.R;

/**
 * Created by peterson on 03/08/2016.
 */
public class MainHolder extends ActivityHolder {

   @Bind(id = R.id.myText, animation = Animations.BounceIn)
   public TextView myText;
   @Bind(id = R.id.centerImage, animation = Animations.SlideInLeft)
   public ImageView centerImage;
   @Bind(id = R.id.centerImage2, animation = Animations.SlideInRight)
   public ImageView centerImage2;
   @Bind(id = R.id.revel, enableAnimation = false)
   public RevealBackgroundRelativeLayout revel;

   /**
    * Default constructor to bind views
    *
    * @param interact ActivityInteract
    */
   public MainHolder(ActivityInteract interact) {
      super(interact);
   }

   @Override
   protected void findViews() {
      findAllMappedChilds(getViewGroup());
   }
}
