package com.azzam.notesapp.presentation.update

import android.os.Bundle
import android.view.*
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.azzam.notesapp.R
import com.azzam.notesapp.data.local.Notes
import com.azzam.notesapp.databinding.FragmentUpdateBinding
import com.azzam.notesapp.presentation.NotesViewModel
import com.azzam.notesapp.utils.ExtensionFunctions.setActionBar
import com.azzam.notesapp.utils.HelperFunctions.parseToPriority
import com.azzam.notesapp.utils.HelperFunctions.setPriorityColor
import java.text.SimpleDateFormat
import java.util.*


class UpdateFragment : Fragment() {

    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding as FragmentUpdateBinding
    private val args by navArgs<UpdateFragmentArgs>()
    private val updateViewModel by viewModels<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setHasOptionsMenu(true)
        binding.updateArgs = args

        binding.apply {
            binding.toolbarUpdate.setActionBar(requireActivity())
            spinnerPrioritiesUpdate.onItemSelectedListener =
                context?.let { setPriorityColor(it, priorityIndicator) }
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        val item = menu.findItem(R.id.menu_save)
        item.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            updateNotes()
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun updateNotes() {
        binding.apply {
            val title = edtTitleUpdate.text.toString()
            val desc = edtDescriptionUpdate.text.toString()
            val priority = spinnerPrioritiesUpdate.selectedItem.toString()
            // buat format calendar
            val date = Calendar.getInstance().time
            val formattedDate = SimpleDateFormat("dd-mm-yyyy", Locale.getDefault()).format(date)

            val currentData = Notes(args.notes.id, title, desc, formattedDate, parseToPriority(
                    context, priority
                    )
                )
            updateViewModel.updateData(currentData)
            val action = UpdateFragmentDirections.actionUpdateFragmentToDetailFragment(currentData)
            findNavController().navigate(action)
            Toast.makeText(context, "Note Has Been Updated.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}