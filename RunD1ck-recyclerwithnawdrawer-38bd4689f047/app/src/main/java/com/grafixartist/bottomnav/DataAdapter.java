package com.grafixartist.bottomnav;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Phone> phones = new ArrayList<>();
    private OnItemClickListener clickListener;

    DataAdapter(Context context) {

        this.inflater = LayoutInflater.from(context);
        prepareDesserts();
    }
    private void prepareDesserts() {
        phones.add(new Phone ("Разработчик 2d", "Я тип чувак жесткий и ваваавававававаgffffffffffffffffffffffffffffffff...", "15m"));
        phones.add(new Phone ("Разработчик 3d", "Прогер тупо че когоgggggggggggg", "1d"));
        phones.add(new Phone ("Разработчик 4d", "Тупо рядовой Жмышенко отдельно мехdfsdfsdfsdfsdfssfdfsdf...", "12h"));
        phones.add(new Phone ("Разработчик Nd и вообще ", "Камон я Лосевffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", "10s"));
    }
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_simplevh, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, int position) {
        Phone phone = phones.get(position);
        holder.whoisView.setText(phone.getWhoIs().trim());
        if(phone.getAbout().length() >27){
            String little ="";
            String full = "";
            char a[] = phone.getAbout().toCharArray();
            for (int i = 0;i <phone.getAbout().length();i++){
                if(i<27){
                    little = little + a[i];
                }else{
                    full = full + a[i];
                }
            }
            little = little + "...";

            holder.aboutView.setText(little.trim());
            holder.aboutfullView.setText(full.trim());
        }else{
            holder.aboutView.setText(phone.getAbout().trim());
        }
        holder.timeView.setText(phone.getTime().trim());


    }

    @Override
    public int getItemCount() {
        return phones.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        final TextView whoisView, aboutView, timeView,aboutfullView;
        final LinearLayout fragment,littleView;
        private int originalHeight = 0;
        private int min_height;
        int i = 0;
        private boolean isViewExpanded = false;
        ViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            littleView = (LinearLayout)view.findViewById(R.id.little_view) ;
            whoisView = (TextView) view.findViewById(R.id.whois);
            aboutView = (TextView) view.findViewById(R.id.about);
            timeView = (TextView)view.findViewById(R.id.time);
            fragment = (LinearLayout)view.findViewById(R.id.fragment);
            aboutfullView = (TextView)view.findViewById(R.id.aboutfull);
            min_height=fragment.getHeight() + 60;
            if(isViewExpanded==false) {
                fragment.setVisibility(View.GONE);
                fragment.setEnabled(false);
            }





        }

        @Override
        public void onClick(final View v){
            i=i+1;
            if(i==2)i=0;
            if(originalHeight ==0){
                originalHeight = v.getHeight();
            }


            ValueAnimator valueAnimator;
            if(!isViewExpanded){
                fragment.setVisibility(View.VISIBLE);
                fragment.setEnabled(true);
                isViewExpanded = true;
                valueAnimator = ValueAnimator.ofInt(originalHeight,originalHeight+(int)(min_height));
            }
            else{
                isViewExpanded = false;
                valueAnimator = ValueAnimator.ofInt(originalHeight + (int)(min_height),originalHeight);
                Animation a = new AlphaAnimation(1.00f,0.00f);
                a.setDuration(200);
                a.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        if(i==1) {
                            fragment.setVisibility(View.GONE);
                            fragment.setEnabled(false);
                        }

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if(i==0) {
                            fragment.setVisibility(View.GONE);
                            fragment.setEnabled(false);
                        }


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                fragment.startAnimation(a);
            }
            valueAnimator.setDuration(200);
            valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Integer value = (Integer) animation.getAnimatedValue();
                    v.getLayoutParams().height = value.intValue();
                    v.requestLayout();

                }
            });
            valueAnimator.start();
        }


    }
}