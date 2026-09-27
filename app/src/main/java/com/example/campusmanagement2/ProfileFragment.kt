package com.example.campusmanagement2

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

class ProfileFragment : Fragment() {

    private lateinit var etFullName: TextInputEditText
    private lateinit var etEmail: TextInputEditText
    private lateinit var etRollNumber: TextInputEditText
    private lateinit var etDepartment: TextInputEditText
    private lateinit var etSemester: TextInputEditText
    private lateinit var btnEditSaveProfile: Button

    private lateinit var sharedPreferences: SharedPreferences
    private var isEditingMode = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferences = requireActivity().getSharedPreferences("UserProfileData", Context.MODE_PRIVATE)

        // Initialize Views
        etFullName = view.findViewById(R.id.etFullName)
        etEmail = view.findViewById(R.id.etEmail)
        etRollNumber = view.findViewById(R.id.etRollNumber)
        etDepartment = view.findViewById(R.id.etDepartment)
        etSemester = view.findViewById(R.id.etSemester)
        btnEditSaveProfile = view.findViewById(R.id.btnEditSaveProfile)

        // Load existing data
        loadProfileData()

        // Handle Edit/Save Toggle
        btnEditSaveProfile.setOnClickListener {
            if (isEditingMode) {
                saveProfileData()
                setFieldsEnabled(false)
                btnEditSaveProfile.text = "Edit Profile"
                isEditingMode = false
                Toast.makeText(requireContext(), "Profile updated successfully!", Toast.LENGTH_SHORT).show()
            } else {
                setFieldsEnabled(true)
                btnEditSaveProfile.text = "Save Changes"
                isEditingMode = true
            }
        }
    }

    private fun loadProfileData() {
        etFullName.setText(sharedPreferences.getString("full_name", "Anishka Gupta"))
        etEmail.setText(sharedPreferences.getString("email", "anishka@example.com"))
        etRollNumber.setText(sharedPreferences.getString("roll_number", "CS2026-089"))
        etDepartment.setText(sharedPreferences.getString("department", "Computer Science"))
        etSemester.setText(sharedPreferences.getString("semester", "4"))
    }

    private fun saveProfileData() {
        with(sharedPreferences.edit()) {
            putString("full_name", etFullName.text.toString().trim())
            putString("email", etEmail.text.toString().trim())
            putString("roll_number", etRollNumber.text.toString().trim())
            putString("department", etDepartment.text.toString().trim())
            putString("semester", etSemester.text.toString().trim())
            apply()
        }
    }

    private fun setFieldsEnabled(enabled: Boolean) {
        etFullName.isEnabled = enabled
        etEmail.isEnabled = enabled
        etRollNumber.isEnabled = enabled
        etDepartment.isEnabled = enabled
        etSemester.isEnabled = enabled
    }
}