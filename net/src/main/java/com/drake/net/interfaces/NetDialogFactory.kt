/*
 * MIT License
 *
 * Copyright (c) 2023 劉強東 https://github.com/liangjingkanji
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.drake.net.interfaces

import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import com.drake.net.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder


fun interface NetDialogFactory {

    /**
     * 构建并返回Dialog. 当使用 scopeDialog 作用域时将会自动显示该对话框且作用域完成后关闭对话框
     *
     * @param activity 请求发生所在的[FragmentActivity]
     */
    fun onCreate(activity: FragmentActivity): AlertDialog

    companion object DEFAULT : NetDialogFactory {
        override fun onCreate(activity: FragmentActivity): AlertDialog {
            val progress = MaterialAlertDialogBuilder(activity)
            progress.setView(R.layout.progress_dialog)
            val dialog = progress.create().apply {
                if (window != null) {
                    val attr = window!!.attributes
                    if (attr != null) {
                        attr.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        attr.width = ViewGroup.LayoutParams.WRAP_CONTENT
                        attr.gravity = Gravity.CENTER //设置dialog 在布局中的位置
                    }
                    window?.attributes = attr
                }
            }
            return dialog
        }
    }
}