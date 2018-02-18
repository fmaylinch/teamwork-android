package com.teamwork.android.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

val Context.inflater get() = LayoutInflater.from(this)

fun ViewGroup.inflate(resource: Int) : View {
    return this.context.inflater.inflate(resource, this, false)
}
