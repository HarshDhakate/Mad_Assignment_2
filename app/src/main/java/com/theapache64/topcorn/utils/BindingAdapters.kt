package com.Harsh Dhakate.MoviesMad.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("textList")
fun setTextList(textView: TextView, list: List<String>) {
    textView.text = list.joinToString(",")
}