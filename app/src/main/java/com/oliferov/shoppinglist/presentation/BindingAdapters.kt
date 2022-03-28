package com.oliferov.shoppinglist.presentation

import androidx.core.content.ContextCompat.startActivity
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import com.oliferov.shoppinglist.R

@BindingAdapter("errorInputName")
fun bindErrorInputName(textInputLayout: TextInputLayout,isError: Boolean){
    val message = if (isError) {
        textInputLayout.context.getString(R.string.message_error)
    } else {
        null
    }
    textInputLayout.error = message
}
@BindingAdapter("errorInputCount")
fun bindErrorInputCount(textInputLayout: TextInputLayout,isError: Boolean){
    val message = if (isError) {
        textInputLayout.context.getString(R.string.message_error)
    } else {
        null
    }
    textInputLayout.error = message
}