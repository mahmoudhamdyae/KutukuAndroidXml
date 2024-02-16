package com.mahmoudhamdyaexml.kutuku.onboarding.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.mahmoudhamdyaexml.kutuku.R
import com.mahmoudhamdyaexml.kutuku.databinding.FragmentOnboardingBinding

class OnboardingFragment: Fragment() {

    private lateinit var binding: FragmentOnboardingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOnboardingBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.adapter = WelcomeViewPageAdapter()
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tabLayout: TabLayout.Tab, index: Int ->
        }.attach()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                if (position == MAX_STEP - 1) {
                    binding.nextButton.text = getString(R.string.get_started)
                    binding.nextButton.contentDescription = getString(R.string.get_started)
                } else {
                    binding.nextButton.text = getString(R.string.next)
                    binding.nextButton.contentDescription = getString(R.string.next)
                }
            }
        })
        binding.viewPager.setPageTransformer(ZoomOutPageTransformer())

        binding.skipButton.setOnClickListener {
            findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
            changeFirstTime()
        }

        binding.nextButton.setOnClickListener {
            if (binding.nextButton.text.toString() == getString(R.string.get_started)) {
                findNavController().navigate(OnboardingFragmentDirections.actionOnboardingFragmentToLoginFragment())
                changeFirstTime()
            } else {
                val current = (binding.viewPager.currentItem) + 1
                binding.viewPager.currentItem = current

                // Update next button
                if (current == MAX_STEP - 1) {
                    binding.nextButton.text = getString(R.string.get_started)
                    binding.nextButton.contentDescription = getString(R.string.get_started)
                } else {
                    binding.nextButton.text = getString(R.string.next)
                    binding.nextButton.contentDescription = getString(R.string.next)
                }
            }
        }
    }

    private fun changeFirstTime() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        with(sharedPref!!.edit()) {
            putBoolean("IS_FIRST_TIME", false)
            apply()
        }
    }

    companion object {
        const val MAX_STEP = 3
    }
}