package org.wit.mathstutorappv2.activities

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.github.abel533.echarts.axis.CategoryAxis
import com.github.abel533.echarts.axis.ValueAxis
import com.github.abel533.echarts.code.Symbol
import com.github.abel533.echarts.code.Trigger
import com.github.abel533.echarts.data.LineData
import com.github.abel533.echarts.data.PieData
import com.github.abel533.echarts.json.GsonOption
import com.github.abel533.echarts.series.Line
import com.github.abel533.echarts.series.Pie
import kotlinx.android.synthetic.main.activity_mta.*
import kotlinx.android.synthetic.main.activity_mta_stats.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.longToast
import org.wit.mathstutorappv2.R
import org.wit.mathstutorappv2.main.MainApp
import org.wit.mathstutorappv2.models.StatsModel
import org.wit.mathstutorappv2.models.statss
import vip.frendy.chart.EChartView
import vip.frendy.chart.EChartWebView

class MTAStatsActivity: FragmentActivity(), EChartWebView.DataSource {


    lateinit var app: MainApp
    var stats = StatsModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mta_stats)



        totalSessions.setText(statss.sessionsTaken.toString())
        passedSessionsNo.setText(statss.sessionsPassed.toString())
        failedSessionsNo.setText(statss.sessionsFailed.toString())

        totalQuestions.setText(statss.totalQuestionsAnswered.toString())
        totalQuestionsCorrectNo.setText(statss.totalAnsweredCorrect.toString())
        totalQuestionsWrongNo.setText(statss.totalAnsweredWrong.toString())

        chartView.setType(1)
        chartView.setDataSource(this)
        app = application as MainApp
    }

    override fun markChartOptions(): GsonOption {
        longToast("Loading Page...") //to hide original foreign "Loading Page..." toast which was not removable
        return getPieChartOptions()
    }

    override fun markChartOptions01(): GsonOption {
        return getPieChartOptions01()
    }

    fun getPieChartOptions01(): GsonOption {
        val option = GsonOption()
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)")
        option.legend().data("Wrong","Correct"  );

        val pie = getPie01().center("50%", "45%").radius("50%")
        pie.label().normal().show(true).formatter("{b}{c}({d}%)")
        option.series(pie)
        return option
    }

    fun getPie01(): Pie {
        return Pie().name("Questions Answered").data(
            PieData("Wrong", statss.totalAnsweredWrong),
            PieData("Correct", statss.totalAnsweredCorrect)


        )
    }

    fun getPieChartOptions(): GsonOption {
        val option = GsonOption()
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b} : {c} ({d}%)")
        option.legend().data("Failed","Passed" );

        val pie = getPie().center("50%", "45%").radius("50%")
        pie.label().normal().show(true).formatter("{b}{c}({d}%)")
        option.series(pie)
        return option
    }

    fun getPie(): Pie {
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