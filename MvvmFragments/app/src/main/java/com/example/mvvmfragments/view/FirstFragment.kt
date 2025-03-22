package com.example.mvvmfragments.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvmfragments.R
import com.example.mvvmfragments.databinding.FragmentFirstBinding
import com.example.mvvmfragments.viewmodel.CountViewModel
import kotlin.math.absoluteValue

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val countViewModel : CountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        countViewModel.getCountLiveData().observe(viewLifecycleOwner,{ set->
            binding.textviewFirst.text = "Count: "+set
        })

        binding.incButton.setOnClickListener({
            countViewModel.increment()
        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}