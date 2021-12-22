package com.android.wordtranslator.view.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import com.android.wordtranslator.R
import com.android.wordtranslator.view.main.MainFragment.Companion.INTENT_PUT_EXTRA_NAME
import com.android.wordtranslator.view.main.MainFragment.Companion.INTENT_PUT_EXTRA_VALUE

class AppWidget : AppWidgetProvider() {

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)

        val word: String? = intent?.getStringExtra(INTENT_PUT_EXTRA_NAME)
        val translate: String? = intent?.getStringExtra(INTENT_PUT_EXTRA_VALUE)

        RemoteViews(
            context?.packageName,
            R.layout.widget
        ).apply {
            setTextViewText(R.id.word, word)
            setTextViewText(R.id.translate, translate)
            context?.let {
                AppWidgetManager.getInstance(it).updateAppWidget(
                    ComponentName(it, AppWidget::class.java), this@apply
                )
            }
        }
    }
}