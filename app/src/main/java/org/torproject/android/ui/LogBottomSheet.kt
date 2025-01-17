package org.torproject.android.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import org.torproject.android.OrbotBottomSheetDialogFragment
import org.torproject.android.R


class LogBottomSheet : OrbotBottomSheetDialogFragment() {

    private lateinit var tvLog : TextView
    private var buffer = StringBuffer()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.log_bottom_sheet, container, false)
        tvLog = v.findViewById(R.id.orbotLog)
        tvLog.text = buffer.toString()

        v.findViewById<Button>(R.id.btnCopyLog).setOnClickListener {
            val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip: ClipData = ClipData.newPlainText("log", tvLog.text)
            clipboard.setPrimaryClip(clip)

        }


        return v;
    }

    public fun appendLog (logLine : String) {
        if (this::tvLog.isInitialized)
        {
            tvLog?.append(logLine)
            tvLog?.append("\n")
        }
        else
            buffer.append(logLine)

    }

    public fun resetLog () {
        if (this::tvLog.isInitialized)
            tvLog?.text = ""
    }
}