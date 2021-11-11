package com.boostcamp.mountainking.ui.mountain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.boostcamp.mountainking.R
import com.boostcamp.mountainking.databinding.FragmentMountainBinding

class MountainFragment : Fragment() {

    private var _binding: FragmentMountainBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMountainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener() {
        binding.rpMap.findAllRichPaths().forEach { path ->
            path.setOnPathClickListener {
                Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_navigation_mountain_to_mountainListFragment, bundleOf("state" to it.name))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}