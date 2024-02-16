package com.mahmoudhamdyaexml.kutuku.onboarding.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mahmoudhamdyaexml.kutuku.onboarding.view.OnboardingFragment.Companion.MAX_STEP
import com.mahmoudhamdyaexml.kutuku.R
import com.mahmoudhamdyaexml.kutuku.databinding.OnboardingItemBinding

class WelcomeViewPageAdapter : RecyclerView.Adapter<PagerVH2>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH2 {
        return PagerVH2(
            OnboardingItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int = MAX_STEP

    override fun onBindViewHolder(holder: PagerVH2, position: Int) = holder.itemView.run {
        with(holder) {
            if (position == 0) {
                bindingDesign.introTitle.text = context.getString(R.string.on_boarding_title_1)
                bindingDesign.introDescription.text = context.getString(R.string.on_boarding_text_1)
                bindingDesign.introImage.setImageResource(R.drawable.on_boarding_image_1)
            }
            if (position == 1) {
                bindingDesign.introTitle.text = context.getString(R.string.on_boarding_title_2)
                bindingDesign.introDescription.text = context.getString(R.string.on_boarding_text_2)
                bindingDesign.introImage.setImageResource(R.drawable.on_boarding_image_2)
            }
            if (position == 2) {
                bindingDesign.introTitle.text = context.getString(R.string.on_boarding_title_3)
                bindingDesign.introDescription.text = context.getString(R.string.on_boarding_text_3)
                bindingDesign.introImage.setImageResource(R.drawable.on_boarding_image_3)
            }
        }
    }
}

class PagerVH2 (val bindingDesign: OnboardingItemBinding) : RecyclerView.ViewHolder(bindingDesign.root)