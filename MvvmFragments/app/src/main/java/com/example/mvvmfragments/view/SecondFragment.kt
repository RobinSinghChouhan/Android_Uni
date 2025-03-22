package com.example.mvvmfragments.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mvvmfragments.R
import com.example.mvvmfragments.databinding.FragmentSecondBinding
import com.example.mvvmfragments.viewmodel.CountViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private  val countViewModel : CountViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }


        countViewModel.getCountLiveData().observe(viewLifecycleOwner,{ set->
            binding.textviewSecond.text = "Count: "+set
        })

        binding.decButton.setOnClickListener({
            countViewModel.decrement()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}