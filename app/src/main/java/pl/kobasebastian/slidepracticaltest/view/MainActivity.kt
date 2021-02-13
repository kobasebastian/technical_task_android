package pl.kobasebastian.slidepracticaltest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_user_dialog.view.*
import kotlinx.android.synthetic.main.user_item.view.*
import pl.kobasebastian.slidepracticaltest.R
import pl.kobasebastian.slidepracticaltest.util.FormValidator
import pl.kobasebastian.slidepracticaltest.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    var dialogShowing = false
    private lateinit var dialog: AlertDialog
    private lateinit var dialogView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.usersResponse.observe(this) {
            users.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                users.adapter = UsersAdapter(it.data.reversed())
            }
        }

        viewModel.getUsers()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add -> {
                displayAddUserDialog()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayAddUserDialog(nameInputString: String = "", emailInputString: String = "") {
        dialogView = LayoutInflater.from(this).inflate(R.layout.add_user_dialog, null)
        dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setTitle(getString(R.string.dialog_title))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.submit_dialog_text), null)
            .setNeutralButton(R.string.cancel_dialog_text, null)
            .show()

        dialogView.name_input.setText(nameInputString)
        dialogView.email_input.setText(emailInputString)

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            dialogView.name.let {
                if(FormValidator.validate(dialogView.name_input, dialogView.email_input)) {
                    viewModel.createUser(dialogView.name_input.text.toString(), dialogView.email_input.text.toString())
                    dialogShowing = false
                    dialog.dismiss()
                }
            }
        }

        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener {
            dialogShowing = false
            dialog.dismiss()
        }
        dialogShowing = true
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (dialogShowing) {
            outState.putBoolean("dialogShowing", true)
            outState.putString("inputNameString", dialogView.name_input.text.toString())
            outState.putString("inputEmailString", dialogView.email_input.text.toString())
        } else {
            outState.putBoolean("dialogShowing", false)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if(savedInstanceState.getBoolean("dialogShowing", false)) {
            displayAddUserDialog(savedInstanceState.getString("inputNameString", ""), savedInstanceState.getString("inputEmailString", ""))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if(dialogShowing) {
            dialog.dismiss()
        }
    }
}