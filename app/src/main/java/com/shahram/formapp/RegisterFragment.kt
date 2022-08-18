package com.shahram.formapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.shahram.formapp.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private lateinit var registerBinding: FragmentRegisterBinding
    private lateinit var registerSharedPref: SharedPreferences
    private lateinit var registerSharedPrefEditor: SharedPreferences.Editor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registerBinding = FragmentRegisterBinding.bind(view)

        registerSharedPref =
            requireActivity().getSharedPreferences(ConstValues.NAME_FILE, Context.MODE_PRIVATE)

        configWeight()
    }

    private fun configWeight() {
        managementButtons()
    }

    private fun managementButtons() {
        registerBinding.buttonRegister.setOnClickListener {
            checkInformation()
        }
    }

    private fun checkInformation() {
        checkName()
        checkFamily()
        checkEmail()
        checkPasswords()
    }

    private fun saveName(name: String) {
        registerSharedPrefEditor = registerSharedPref.edit()
        registerSharedPrefEditor.putString(ConstValues.NAME_SHARED, name)
        registerSharedPrefEditor.apply()
        registerSharedPrefEditor.commit()
    }

    private fun saveFamily(family: String) {
        registerSharedPrefEditor = registerSharedPref.edit()
        registerSharedPrefEditor.putString(ConstValues.FAMILY_SHARED, family)
        registerSharedPrefEditor.apply()
        registerSharedPrefEditor.commit()
    }

    private fun saveEmail(email: String) {
        registerSharedPrefEditor = registerSharedPref.edit()
        registerSharedPrefEditor.putString(ConstValues.EMAIL_SHARED, email)
        registerSharedPrefEditor.apply()
        registerSharedPrefEditor.commit()
    }

    private fun savePassword(password: String) {
        registerSharedPrefEditor = registerSharedPref.edit()
        registerSharedPrefEditor.putString(ConstValues.PASSWORD_SHARED, password)
        registerSharedPrefEditor.apply()
        registerSharedPrefEditor.commit()
    }

    private fun checkName() {
        val name = registerBinding.editTextName.text.toString()

        if (name.isEmpty()) {
            registerBinding.layoutName.helperText = getString(R.string.text_error_name)
            return
        }
        registerBinding.layoutName.helperText = ""
        saveName(name)
    }

    private fun checkFamily() {
        val family = registerBinding.editTextFamily.text.toString()

        if (family.isEmpty()) {
            registerBinding.layoutFamily.helperText = getString(R.string.text_error_family)
            return
        }

        registerBinding.layoutFamily.helperText =""
        saveFamily(family)
    }

    private fun checkEmail() {
        val email = registerBinding.editTextEmail.text.toString()

        if (email.isEmpty()) {
            registerBinding.layoutEmail.helperText = getString(R.string.text_error_email)
            return
        }

        registerBinding.layoutEmail.helperText =""
        saveEmail(email)
    }

    private fun checkEqualPasswords(password: String, retypePassword: String) {
        if (password != retypePassword) {
            registerBinding.layoutPassword.helperText =
                getString(R.string.text_error_not_equals_password)
            registerBinding.layoutRetypePassword.helperText =
                getString(R.string.text_error_not_equals_password)
            return
        }

        registerBinding.layoutPassword.helperText = ""
        registerBinding.layoutRetypePassword.helperText = ""
        savePassword(password)
    }

    private fun checkPasswords() {
        val password = registerBinding.editTextPassword.text.toString()
        val retypePassword = registerBinding.editTextRetypePassword.text.toString()

        if (password.isEmpty() || retypePassword.isEmpty()) {
            registerBinding.layoutPassword.helperText = getString(R.string.text_error_password)
            registerBinding.layoutRetypePassword.helperText =
                getString(R.string.text_error_retype_password)
            return
        }

        registerBinding.layoutPassword.helperText = ""
        registerBinding.layoutRetypePassword.helperText = ""
        checkEqualPasswords(password, retypePassword)
    }

}