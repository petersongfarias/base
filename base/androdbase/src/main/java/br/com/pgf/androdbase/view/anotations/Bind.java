package br.com.pgf.androdbase.view.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.pgf.androdbase.anim.Animations;


/**
 * Created by peterson on 02/08/2016.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Bind {

   /**
    * View ID to which the field will be bound.
    */
   int id();

   /**
    * It indicates whether the animation is enabled for this view
    */
   boolean enableAnimation() default true;

   /**
    * animation type
    * @return
    */
   Animations animation() default Animations.FadeIn;

}
