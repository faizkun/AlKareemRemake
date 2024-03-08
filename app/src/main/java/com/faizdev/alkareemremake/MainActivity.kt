package com.faizdev.alkareemremake

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.faizdev.alkareemremake.screen.read.GlobalViewModel
import com.faizdev.alkareemremake.ui.theme.AlKareemRemakeTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {

            AlKareemRemakeTheme {
               DestinationsNavHost(
                   navGraph = NavGraphs.root,
                   dependenciesContainerBuilder = {
                       dependency(viewModel<GlobalViewModel>(this@MainActivity))
                   }
               )
            }

        }
    }
}


data class TabItem(
    val title: String,
//    val unselectedIcon: ImageVector,
//    val selectedIcon: ImageVector,
)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AlKareemRemakeTheme {
        Greeting("Android")
    }
}