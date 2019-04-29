package org.wit.mathstutorappv2.activities

/*
This page gives functionality to the activity_mta_stats.xml
Page: displays the users statistics aided with pie charts to
show a more visual representation of their progress.
The charts implementation design belongs to frendyxzc (github -- link below)
 */
/*
REFERENCE: https://github.com/frendyxzc/Charts
 */

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.view.Menu
import android.view.MenuItem
import com.github.abel533.echarts.code.Trigger
import com.github.abel533.echarts.data.PieData
import com.github.abel533.echarts.json.GsonOption
import com.github.abel533.echarts.series.Pie
import kotlinx.android.synthetic.main.activity_mta_stats.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.statss
import vip.frendy.chart.EChartWebView

class MTAStatsActivity: FragmentActivity(), EChartWebView.DataSource {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mta_stats)

        app = application as MainApp

        app.stats.getStats()        //gets existing statistics from Json

        /*
        Sets activity_mta_stats.xml values to the appropriate value from the StatsModel.kt and json
         */
        totalSessions.setText(statss.sessionsTaken.toString())
        passedSessionsNo.setText(statss.sessionsPassed.toString())
        failedSessionsNo.setText(statss.sessionsFailed.toString())

        totalQuestions.setText(statss.totalQuestionsAnswered.toString())
        totalQuestionsCorrectNo.setText(statss.totalAnsweredCorrect.toString())
        totalQuestionsWrongNo.setText(statss.totalAnsweredWrong.toString())

        /*
            if no challenges have been completed or stats are reset,
            charts will not appear due to null value
         */
        if(statss.sessionsTaken.equals(0)){
            toast("No charts to load! \nComplete at least 1 challenge.")
        }


        chartView.setType(1)
        chartView.setDataSource(this)

        /*
        Reset button resets the statistics page and sets all values to 0
         */
        btnReset.setOnClickListener{
            app.stats.deleteStats(statss)
            toast("Your statistics have been reset.")
            finish()
        }
    }



    override fun markChartOptions(): GsonOption {
        longToast("Loading Page...\nCharts Loading...") //to hide original foreign "Loading Page..." toast which was not removable
        return getPieChartOptions()
    }

    override fun markChartOptions01(): GsonOption {
        return getPieChartOptions01()
    }

    fun getPieChartOptions01(): GsonOption {
        val option = GsonOption()
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)")
        option.legend().data("Wrong","Correct"  )       //Legend for the charts

        val pie = getPieQuestionsAnswered().center("50%", "45%").radius("50%")
        pie.label().normal().show(true).formatter("{b}{c}({d}%)")
        option.series(pie)
        return option
    }

    fun getPieQuestionsAnswered(): Pie {
        return Pie().name("Questions Answered").data(
            //sets the two pie values to the values taken from the StatsModel and json
            PieData("Wrong", statss.totalAnsweredWrong),
            PieData("Correct", statss.totalAnsweredCorrect)


        )
    }

    fun getPieChartOptions(): GsonOption {
        val option = GsonOption()
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)")
        option.legend().data("Failed","Passed" );

        val pie = getPieSessions().center("50%", "45%").radius("50%")
        pie.label().normal().show(true).formatter("{b}{c}({d}%)")
        option.series(pie)
        return option
    }

    fun getPieSessions(): Pie {
        return Pie().name("Sessions Pass/Fail").data(
            PieData("Failed", statss.sessionsFailed),
            PieData("Passed", statss.sessionsPassed)

        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_views, menu)
        if (menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.item_back -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}